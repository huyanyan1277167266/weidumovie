package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class MovieListActivity extends BaseActivity {
@BindView(R.id.fh)
    ImageView fh;
@BindView(R.id.et)
    EditText et;
@BindView(R.id.tablist)
TabLayout tablist;
@BindView(R.id.vplist)
    ViewPager vplist;
    ArrayList<String> tabs = new ArrayList<>();
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
        tabs.add("正在热映");
        tabs.add("即将上映");
        tabs.add("热门电影");
        tablist.setTag(tablist.newTab().setText(tabs.get(0)));
        tablist.setTag(tablist.newTab().setText(tabs.get(1)));
        tablist.setTag(tablist.newTab().setText(tabs.get(2)));
        //实例化对象

    }
}
