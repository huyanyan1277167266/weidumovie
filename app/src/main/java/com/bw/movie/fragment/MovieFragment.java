package com.bw.movie.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
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
import com.bw.movie.utiles.SpUtile;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:21:55
 *@Description:
 * */public class MovieFragment extends BaseFragment implements BannerContract.BannerView {
    @BindView(R.id.xb)
    XBanner xb;
    @BindView(R.id.ryrlv)
    RecyclerView ryrlv;
    @BindView(R.id.syrlv)
    RecyclerView syrlv;
    @BindView(R.id.rmrlv)
    RecyclerView rmrlv;

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

    }


    @Override
    protected void getData() {
        //生成dao对象
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), "release");
        //创建表
        resultBeanDao = daoSession.getResultBeanDao();


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
}
