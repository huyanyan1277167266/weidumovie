package com.bw.movie.model;


import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.contract.ComingContract;
import com.bw.movie.utiles.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:23:28
 *@Description:
 * */public class ComingIModel implements ComingContract.ComingModel {

    @Override
    public void getComing(int page, int count, ComingICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doComing(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComingSoonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComingSoonBean comingSoonBean) {
                        if (iCallBack!=null){
                            iCallBack.onComingSuccess(comingSoonBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onComingError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
