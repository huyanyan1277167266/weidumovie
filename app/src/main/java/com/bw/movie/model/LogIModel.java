package com.bw.movie.model;

import com.bw.movie.bean.LogBean;
import com.bw.movie.contract.LogContract;
import com.bw.movie.utiles.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/4/22
 *@Time:23:52
 *@Description:
 * */public class LogIModel implements LogContract.LogModel {
    @Override
    public void getLog(String email, String pwd, LogICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doLog(email,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogBean logBean) {
                        if (iCallBack!=null){
                            iCallBack.onLogSuccess(logBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onLogError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
