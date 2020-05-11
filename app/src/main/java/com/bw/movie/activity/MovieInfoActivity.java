package com.bw.movie.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ResultBean_movieinfo;
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.fragment.AnnounceFraggment;
import com.bw.movie.fragment.IntroduceFraggment;
import com.bw.movie.fragment.PhotoFraggment;
import com.bw.movie.fragment.ReviewFraggment;
import com.bw.movie.presenter.MovieInIPresenter;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MovieInfoActivity extends BaseActivity implements MovieInContract.IView {

@BindView(R.id.iv)
    ImageView iv;
@BindView(R.id.tvpf)
    TextView tvpf;
@BindView(R.id.tvpl)
TextView tvpl;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.di)
    TextView di;
    @BindView(R.id.heart)
    ImageView heart;
    @BindView(R.id.yiguan)
    TextView yiguan;
    @BindView(R.id.names)
    TextView names;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.bu_write1)
    Button bu1;
    @BindView(R.id.bu_write2)
    Button bu2;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    private String movieid;
    private ResultBean_movieinfo result;

    @Override
    protected BasePresenter initPresenter() {
        return new MovieInIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        movieid = intent.getStringExtra("movieid");

        BasePresenter basePresenter = getmPresenter();
        if (basePresenter!=null&&basePresenter instanceof MovieInIPresenter){
            ((MovieInIPresenter) basePresenter).getMovieIn(Integer.valueOf(movieid));
        }
        tabs.add("介绍");
        tabs.add("预告");
        tabs.add("剧照");
        tabs.add("影评");
        tab.setTag(tab.newTab().setText(tabs.get(0)));
        tab.setTag(tab.newTab().setText(tabs.get(1)));
        tab.setTag(tab.newTab().setText(tabs.get(2)));
        tab.setTag(tab.newTab().setText(tabs.get(3)));
        //实例化对象
        IntroduceFraggment introduceFraggment = new IntroduceFraggment();
        AnnounceFraggment announceFraggment = new AnnounceFraggment();
        PhotoFraggment photoFraggment = new PhotoFraggment();
        ReviewFraggment reviewFraggment = new ReviewFraggment();
        list.add(introduceFraggment);
        list.add(announceFraggment);
        list.add(photoFraggment);
        list.add(reviewFraggment);
        //创建适配器
        MyAdapterInfo myAdapterInfo = new MyAdapterInfo(getSupportFragmentManager());
        vp.setAdapter(myAdapterInfo);
        tab.setupWithViewPager(vp);
        vp.setOffscreenPageLimit(4);

    }
    public class MyAdapterInfo extends FragmentPagerAdapter{
        public MyAdapterInfo(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
    @SuppressLint("SimpleDateFormat")
    @Override
    public void onSuccess(MovieDataBean movieDataBean) {
        result = movieDataBean.getResult();
        if (result.getWhetherFollow()==1){
            heart.setImageResource(R.mipmap.emptyheart);
            yiguan.setText("已关注");
        }else{
            heart.setImageResource(R.mipmap.wei);
            yiguan.setText("未关注");
        }
        Glide.with(this).load(result.getImageUrl()).into(iv);
        tvpf.setText("评分："+ result.getScore()+"分");
        tvpl.setText("评论："+ result.getCommentNum()+"万");
        name.setText(result.getName());
        time.setText(result.getMovieType()+""+ result.getDuration());
        long releaseTime = result.getReleaseTime();
//        String data = new SimpleDateFormat("yy--MM--dd").format(
//                java.util.Date(releaseTime);

        names.setText(result.getName());
        EventBus.getDefault().postSticky(result);
    }

    @Override
    public void onError(String str) {

    }

    @Override
    public void onGuanSuccess(RegisterBean bean) {

    }

    @Override
    public void onGuanError(String str) {

    }

    @Override
    public void onQuGuanSuccess(RegisterBean bean) {

    }

    @Override
    public void onQuGuanError(String str) {

    }

    @OnClick(R.id.heart)
    public void onClick(View view){
        int whetherFollow = result.getWhetherFollow();
        if (whetherFollow == 2){
            Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
            heart.setImageResource(R.mipmap.emptyheart);
            yiguan.setText("已关注");
            BasePresenter basePresenter = getmPresenter();
            if (basePresenter instanceof MovieInIPresenter){
               ((MovieInIPresenter) basePresenter).getGuanData(Integer.valueOf(movieid));
            }

        }
        if (whetherFollow == 1){
            heart.setImageResource(R.mipmap.wei);
            yiguan.setText("未关注");
            BasePresenter basePresenter = getmPresenter();
            if (basePresenter instanceof MovieInIPresenter){
               ((MovieInIPresenter) basePresenter).getQuGuanData(Integer.valueOf(movieid));
            }
        }
    }

    @OnClick({R.id.bu_write1,R.id.bu_write2})
    public void getd(View view){
        switch (view.getId()){
            case R.id.bu_write1:
                String s = name.getText().toString();
                Intent intent = new Intent(this, WritePingLunActivity.class);
                intent.putExtra("id",movieid+"");
                intent.putExtra("name",s+"");
                startActivity(intent);
                break;
            case R.id.bu_write2:
                String s1 = name.getText().toString();
                Intent intent1 = new Intent(this, WritePingLunActivity.class);
                intent1.putExtra("id",movieid+"");
                intent1.putExtra("name",s1+"");
                startActivity(intent1);
                break;
        }
    }

}
