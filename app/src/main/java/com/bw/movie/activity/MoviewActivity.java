package com.bw.movie.activity;

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
       integers.clear();

        //添加头部标签
        tabs.add("电影");
        tabs.add("影院");
        tabs.add("我的");
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));

        
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
}
