package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.presenter.MovieInIPresenter;

public class MovieInfoActivity extends BaseActivity implements MovieInContract.IView {


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

    }

    @Override
    public void onSuccess(MovieDataBean movieDataBean) {

    }

    @Override
    public void onError(String str) {

    }
}
