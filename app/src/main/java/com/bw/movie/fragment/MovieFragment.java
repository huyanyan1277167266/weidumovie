package com.bw.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;

import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.MovieListActivity;
import com.bw.movie.adapter.HotAdapter;
import com.bw.movie.adapter.RyMovieAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.adapter.ComingAdapter;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.ResultBean;
import com.bw.movie.contract.BannerContract;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.ResultBeanDao;
import com.bw.movie.presenter.BannerIPresenter;
import com.bw.movie.utiles.NetUtile;

import com.stx.xhb.xbanner.XBanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:21:55
 *@Description:
 * */public class MovieFragment extends BaseFragment implements BannerContract.BannerView,LocationSource,AMapLocationListener{
    @BindView(R.id.xb)
    XBanner xb;
    @BindView(R.id.ryrlv)
    RecyclerView ryrlv;
    @BindView(R.id.syrlv)
    RecyclerView syrlv;
    @BindView(R.id.rmrlv)
    RecyclerView rmrlv;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.rygd)
    TextView rygd;
    @BindView(R.id.sygd)
    TextView sygd;
    @BindView(R.id.rmgd)
    TextView rmgd;
    //地图变量
    private AMap aMap;
    private MapView mapView;

    private AMapLocationClient mLocationClient=null;
    public AMapLocationClientOption mLocationOption=null;
    //声明对象，定位监听
    private LocationSource.OnLocationChangedListener mListener=null;
    //定义一个表示，判断定位信息
    private boolean isposition=true;
    ArrayList<Integer> list = new ArrayList<>();
    private String citys;
    private List<BannerBean.ResultBean> result;
    private List<HotBean.ResultBean> result1;
    private ResultBeanDao resultBeanDao;



    @Override
    protected BasePresenter initPresenter() {
        return new BannerIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.movie_fragment;
    }

    @Override
    protected void initView(View view) {
        mapView = view.findViewById(R.id.map);
        //实现地图生命周期管理
        if (aMap==null){
            aMap=mapView.getMap();
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource((LocationSource) this);
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);
        }
        location();
    }




    @Override
    protected void getData() {
        //生成dao对象
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), "release");
        //创建表
        resultBeanDao = daoSession.getResultBeanDao();
        if (citys != null){
            city.setText(citys+"");
        }
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("name", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        String sessionId = sharedPreferences.getString("sessionId", "");
            //有网请求数据
            if (NetUtile.getInstance().isWork(getContext())){
                BasePresenter basePresenter = getmPresenter();
                if (basePresenter!=null&&basePresenter instanceof BannerIPresenter){
                    ((BannerIPresenter) basePresenter).getBanner();
                    ((BannerIPresenter) basePresenter).getRelease(1,5);
                    ((BannerIPresenter) basePresenter).getComing(userId,sessionId,1,5);
                    ((BannerIPresenter) basePresenter).getHot(1,5);

                }
            }else{//无网查询数据库
                Toast.makeText(getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
                //查新数据库
                List<ResultBean> list = resultBeanDao.queryBuilder().list();
                //创建一个新的bean类
                ReleaseBean releaseBean = new ReleaseBean();
                //将查询出来的数据存入bean新建的这个bean类中
                releaseBean.setResult(list);
                //调用成功的方法 显示列表
                onReleaseSuccess(releaseBean);
            }



    }

    @Override
    public void onBannerSuccess(BannerBean bannerBean) {
        result = bannerBean.getResult();
        //设置数据
        xb.setBannerData(result);
        //设置图片
        xb.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerBean.ResultBean bean= (BannerBean.ResultBean) model;
                Glide.with(getActivity()).load(bean.getImageUrl()).into((ImageView)view);

            }
        });
    }

    @Override
    public void onBannerError(String str) {

    }

    @Override
    public void onReleaseSuccess(ReleaseBean releaseBean) {
        List<ResultBean> result = releaseBean.getResult();

        //存到数据库
        for (int i=0;i<result.size();i++){
            resultBeanDao.insertOrReplace(result.get(i));
        }

        //创建布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        ryrlv.setLayoutManager(linearLayoutManager);
        //创建适配器
        RyMovieAdapter ryMovieAdapter = new RyMovieAdapter(getActivity(),result);
        ryrlv.setAdapter(ryMovieAdapter);
    }

    @Override
    public void onReleaseError(String str) {

    }

    @Override
    public void onComingSuccess(ComingBean comingBean) {
        List<ComingBean.ResultBean> cominglist = comingBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        syrlv.setLayoutManager(linearLayoutManager);
        //创建适配器
        ComingAdapter comingAdapter = new ComingAdapter(getContext(), cominglist);
        syrlv.setAdapter(comingAdapter);
    }

    @Override
    public void onComingError(String str) {

    }

    @Override
    public void HotSuccess(HotBean hotBean) {
        Log.i("a",""+hotBean.getMessage());
        result1 = hotBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rmrlv.setLayoutManager(linearLayoutManager);
       // 创建适配器
        HotAdapter hotAdapter = new HotAdapter(getContext(), result1);
        rmrlv.setAdapter(hotAdapter);

    }

    @Override
    public void HotError(String str) {
        Log.i("a",""+str);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isposition) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    //  aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    citys = aMapLocation.getCity();
                    city.setText(citys+"");
                    isposition = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener=onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener=null;
    }
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener((AMapLocationListener) this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    @OnClick(R.id.search)
    public void onClickted(View view){
        Intent intent = new Intent(getContext(), MovieListActivity.class);
        startActivity(intent);
    }
    @OnClick({R.id.rygd,R.id.sygd,R.id.rmgd})
    public void setOnClicks(View view){
        switch (view.getId()){
            case R.id.rygd:
                Intent intent = new Intent(getContext(), MovieListActivity.class);
                intent.putExtra("a","0");
                startActivity(intent);
                break;
            case R.id.sygd:
                Intent intent1 = new Intent(getContext(), MovieListActivity.class);
                intent1.putExtra("a","1");
                startActivity(intent1);
                break;
            case R.id.rmgd:
                Intent intent2 = new Intent(getContext(), MovieListActivity.class);
                intent2.putExtra("a","2");
                startActivity(intent2);
                break;
                default:
        }
    }

}
