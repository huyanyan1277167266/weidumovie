package com.bw.movie.fragment;

import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:21:55
 *@Description:
 * */public class MyFragment extends BaseFragment {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    }
}
