package com.bw.movie.fragment;

import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.HotMovieBean;
import com.bw.movie.contract.HotContract;
import com.bw.movie.presenter.HotIPresenter;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:34
 *@Description:
 * */public class HotFragment extends BaseFragment implements HotContract.HotView {
    @Override
    protected BasePresenter initPresenter() {
        return new HotIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.hot_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    }

    @Override
    public void HotSuccess(HotMovieBean hotMovieBean) {

    }

    @Override
    public void HotError(String str) {

    }
}
