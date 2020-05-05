package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.BannerContract;
import com.bw.movie.utiles.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:23:42
 *@Description:
 * */public class BannerIModel implements BannerContract.BannerModel {
    @Override
    public void getBanner(BannerICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        if (iCallBack!=null){
                            iCallBack.onBannerSuccess(bannerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onBannerError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

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

    @Override
    public void getHot(int page, int count, HotICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doHot(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        Log.i("bbb","成功");
                        if (iCallBack!=null){
                            iCallBack.HotSuccess(hotBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("bbb",e.toString());
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
