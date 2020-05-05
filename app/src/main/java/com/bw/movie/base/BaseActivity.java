package com.bw.movie.base;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    P mPresenter;
    private Unbinder bind;
    Dialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        StatusBarUtil.setTransparent(this);

        bind = ButterKnife.bind(this);
        mPresenter=initPresenter();
        initView();
        getData();
    }
//    public void showDialog(){
//
//            if (mLoadingDialog == null) {
//                mLoadingDialog = new Dialog(this);
//                mLoadingDialog.setCancelable(false);
//                View v = View.inflate(this, R.layout.dialog_loading, null);
//                ImageView iv = v.findViewById(R.id.iv_loading);
//                Glide.with(this).asGif().load(R.mipmap.loading).into(iv);
//
//                mLoadingDialog.addContentView(v,
//                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));
//            }
//
//            mLoadingDialog.show();
//
//    }
    public P getmPresenter() {
        return mPresenter;
    }

    protected abstract P initPresenter();
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.datachView();
            mPresenter=null;
        }
        bind.unbind();
    }
}
