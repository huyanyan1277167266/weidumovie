package com.bw.movie.model;

import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.RegisterContract;
import com.bw.movie.utiles.NetUtile;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:cln
 *@Date: 2020/4/22
 *@Time:14:08
 *@Description:
 * */public class RegisterIModel implements RegisterContract.RegisterModel {
    @Override
    public void getRegister(String nickName, String pwd, String email, String code, RegisterICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doRegister(nickName,pwd,email,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (iCallBack!=null){
                            iCallBack.onRegisterSuccess(registerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onRegisterError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getEmail(String email, EmailICallBack iCallBack) {
        NetUtile.getInstance().getmApis().doEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmailBean emailBean) {
                        if (iCallBack!=null){
                            iCallBack.onEmailSuccess(emailBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iCallBack!=null){
                            iCallBack.onEmailError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
