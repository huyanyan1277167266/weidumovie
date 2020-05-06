package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;



import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.MovieFragment;
import com.bw.movie.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

import butterknife.BindView;

public class MoviewActivity extends BaseActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moview;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        tab.setSelectedTabIndicatorHeight(0);
       integers.clear();
       integers.add(R.mipmap.yingpian);
       integers.add(R.mipmap.cinema);
       integers.add(R.mipmap.my);
        //添加头部标签
        tabs.add("电影");
        tabs.add("影院");
        tabs.add("我的");
        tab.addTab(tab.newTab().setText(""));
        tab.addTab(tab.newTab().setText(""));
        tab.addTab(tab.newTab().setText(""));

        //实例化对象
        MovieFragment movieFragment = new MovieFragment();
        CinemaFragment cinemaFragment = new CinemaFragment();
        MyFragment myFragment = new MyFragment();
        list.add(movieFragment);
        list.add(cinemaFragment);
        list.add(myFragment);
        //创建适配器
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        //设置适配器
        vp.setAdapter(fragmentAdapter);
        //关联头部标签与viewPager
        tab.setupWithViewPager(vp);
        setTabStyle();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint({"WrongConstant","ResourceAsColor"})
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab!=null){
                    View view = tab.getCustomView();
                    view.setBackgroundColor(Color.WHITE);
                    //设置颜色
                    ((TextView) view.findViewById(R.id.name)).setVisibility(0);
                    ((TextView)view.findViewById(R.id.name)).setTextColor(R.color.colorAccent);
                    tab.setCustomView(view);
                }
            }
            @SuppressLint({"WrongConstant","ResourceAsColor"})
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab!=null){
                    View view = tab.getCustomView();
                    view.setBackgroundColor(R.color.colorPrimaryDark);
                    ((TextView)view.findViewById(R.id.name)).setVisibility(8);
                    tab.setCustomView(view);
                }
            }
            @SuppressLint({"WrongConstant","ResourceAsColor"})
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    public class FragmentAdapter extends FragmentPagerAdapter{
        public FragmentAdapter(@NonNull FragmentManager fm) {
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
    private void setTabStyle(){
        //循环头部标签的数量
      for (int i=0;i<tabs.size();i++){
          TabLayout.Tab tabAt = tab.getTabAt(i);
          if (tabAt!=null){
              View view = LayoutInflater.from(this).inflate(R.layout.tablayout_style, null);
              ((TextView)view.findViewById(R.id.name)).setText(tabs.get(i));
              ((ImageView)view.findViewById(R.id.iv)).setImageResource(integers.get(i));
              if (i==0){
                  ((TextView)view.findViewById(R.id.name)).setTextColor(getResources().getColor(R.color.colorAccent));
                  ((ImageView)view.findViewById(R.id.iv)).setImageResource(integers.get(i));
              }else{

              }
              tabAt.setCustomView(view);
          }
      }
    }
}
