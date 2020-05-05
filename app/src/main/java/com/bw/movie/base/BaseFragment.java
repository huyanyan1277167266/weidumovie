package com.bw.movie.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:21:55
 *@Description:
 * */public abstract class BaseFragment <P extends BasePresenter>extends Fragment implements IBaseView{
     P mPresenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getLayoutId(), null);
        bind = ButterKnife.bind(this, view);
        mPresenter=initPresenter();
        initView(view);
        getData();
        return view;
    }

    public P getmPresenter() {
        return mPresenter;
    }
    protected abstract P initPresenter();
    protected abstract int getLayoutId();
    protected abstract void initView(View view);
    protected abstract void getData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.datachView();
            mPresenter=null;
        }
        bind.unbind();
    }
}
