package com.bw.movie.model;

import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.utiles.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/5/11
 *@Time:2:32
 *@Description:
 * */public class MovieInIMdel implements MovieInContract.MovieInIModel {


    @Override
    public void getMovieIn(int movieid, IModelCallBack icallBack) {
        NetUtile.getInstance().getmApis().domovieInfo(movieid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDataBean movieDataBean) {
                        if (icallBack!=null){
                            icallBack.onSuccess(movieDataBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (icallBack!=null){
                            icallBack.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getGuanData(int movieId, IModelGuanCallBack callBack) {
        NetUtile.getInstance().getmApis().doGuan(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean bean) {
                       if (callBack!=null){
                           callBack.onGuanSuccess(bean);
                       }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack!=null){
                            callBack.onGuanError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getQuGuanData(int movieid, IModelQuGuanCallBack callBack) {
       NetUtile.getInstance().getmApis().doQuGuan(movieid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean bean) {
                        if (callBack!=null){
                            callBack.onQuGuanSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                       if (callBack!=null){
                           callBack.onQuGuanError(e.getMessage());
                       }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
