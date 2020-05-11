package com.bw.movie.fragment;

import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;

/*
 *@Auther:cln
 *@Date: 2020/5/11
 *@Time:17:27
 *@Description:  介绍页
 * */public class IntroduceFraggment extends BaseFragment {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.introduce_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    }
}
