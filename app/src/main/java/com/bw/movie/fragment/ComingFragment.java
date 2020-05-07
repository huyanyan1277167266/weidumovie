package com.bw.movie.fragment;

import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.contract.ComingContract;
import com.bw.movie.presenter.ComingIPresenter;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:34
 *@Description:
 * */public class ComingFragment extends BaseFragment implements ComingContract.ComingView {
    @Override
    protected BasePresenter initPresenter() {
        return new ComingIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.coming_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onComingSuccess(ComingBean comingBean) {

    }

    @Override
    public void onComingError(String str) {

    }
}
