package com.bw.movie.model;

import com.bw.movie.bean.ComingBean;
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
    public void getComing(int userId, String sessionId, int page, int count, ComingICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doComing(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComingBean comingBean) {
                        if (iCallBack!=null){
                            iCallBack.onComingSuccess(comingBean);
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
