package com.bw.movie.model;

import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.ReleaseContract;
import com.bw.movie.utiles.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:23:09
 *@Description:
 * */public class ReleaseIModel implements ReleaseContract.ReleaseModel {
    @Override
    public void getRelease(int page, int count, ReleaseICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doRelease(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReleaseBean releaseBean) {
                        if (iCallBack!=null){
                            iCallBack.onReleaseSuccess(releaseBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onReleaseError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
