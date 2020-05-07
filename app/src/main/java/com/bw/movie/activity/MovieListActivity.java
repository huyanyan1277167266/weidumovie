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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.ComingFragment;
import com.bw.movie.fragment.HotFragment;
import com.bw.movie.fragment.MovieFragment;
import com.bw.movie.fragment.ReleaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MovieListActivity extends BaseActivity {
//@BindView(R.id.fh)
//    ImageView fh;
@BindView(R.id.et)
    EditText et;
@BindView(R.id.tablist)
TabLayout tablist;
@BindView(R.id.vplist)
    ViewPager vplist;
@BindView(R.id.search)
        ImageView search;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String index = intent.getStringExtra("a");
        tabs.clear();
        list.clear();
        tabs.add("正在热映");
        tabs.add("即将上映");
        tabs.add("热门电影");
        tablist.setTag(tablist.newTab().setText(tabs.get(0)));
        tablist.setTag(tablist.newTab().setText(tabs.get(1)));
        tablist.setTag(tablist.newTab().setText(tabs.get(2)));
        //实例化对象
        ReleaseFragment releaseFragment = new ReleaseFragment();
        ComingFragment comingFragment = new ComingFragment();
        HotFragment hotFragment = new HotFragment();
        list.add(releaseFragment);
        list.add(comingFragment);
        list.add(hotFragment);
        //创建适配器
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vplist.setAdapter(myAdapter);
        tablist.setupWithViewPager(vplist);
        tablist.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (index!=null){
            Toast.makeText(this, ""+index, Toast.LENGTH_SHORT).show();
            vplist.setCurrentItem(Integer.valueOf(index));
        }
    }
    @SuppressLint("WrongConstant")
    @OnClick(R.id.search)
    public void onClick(View view){
                search.setVisibility(8);
                et.setVisibility(0);
                String str = et.getText().toString();
                Intent intent = new Intent(MovieListActivity.this, QueryByKeyActivity.class);
                intent.putExtra("Key",str);
                startActivity(intent);


    }
    public class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(@NonNull FragmentManager fm) {
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
