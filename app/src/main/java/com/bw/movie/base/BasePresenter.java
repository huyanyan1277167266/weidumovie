package com.bw.movie.base;

import java.lang.ref.WeakReference;

/*
 *@Auther:cln
 *@Date: 2020/4/17
 *@Time:20:20
 *@Description:
 * */public abstract class BasePresenter<V extends IBaseView> {

    private WeakReference<V> mWeakReference;

    public BasePresenter(V v) {
        mWeakReference = new WeakReference<>(v);
        initModel();
    }
    public V getView(){
        if (mWeakReference!=null){
            return mWeakReference.get();
        }
        return null;
    }
    protected abstract void initModel();
    public void datachView(){
        if (mWeakReference!=null){
            mWeakReference.clear();
            mWeakReference=null;
        }
    }
}
