package com.bw.movie.model;

import com.bw.movie.bean.HotMovieBean;
import com.bw.movie.contract.HotContract;
import com.bw.movie.utiles.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:41
 *@Description:
 * */public class HotIModel implements HotContract.HotModel {
    @Override
    public void getHot(int page, int count, HotICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doHotMovie(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotMovieBean hotMovieBean) {
                        if (iCallBack!=null){
                            iCallBack.HotSuccess(hotMovieBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.HotError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
