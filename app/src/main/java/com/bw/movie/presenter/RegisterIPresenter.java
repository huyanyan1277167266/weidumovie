package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.RegisterContract;
import com.bw.movie.model.RegisterIModel;

/*
 *@Auther:cln
 *@Date: 2020/4/22
 *@Time:14:10
 *@Description:
 * */public class RegisterIPresenter extends BasePresenter implements RegisterContract.RegisterPresenter {

    private RegisterIModel mModel;

    public RegisterIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new RegisterIModel();
    }

    @Override
    public void getEmail(String email) {
        mModel.getEmail(email, new RegisterContract.RegisterModel.EmailICallBack() {
            @Override
            public void onEmailSuccess(EmailBean emailBean) {
                IBaseView view = getView();
                if (view instanceof RegisterContract.RegisterView){
                    ((RegisterContract.RegisterView) view).onEmailSuccess(emailBean);
                }
            }

            @Override
            public void onEmailError(String str) {
                IBaseView view = getView();
                if (view instanceof RegisterContract.RegisterView){
                   ((RegisterContract.RegisterView) view).onEmailError(str);
                }
            }
        });
    }

    @Override
    public void getRegister(String nickName, String pwd, String email, String code) {
        mModel.getRegister(nickName, pwd, email, code, new RegisterContract.RegisterModel.RegisterICallBack() {
            @Override
            public void onRegisterSuccess(RegisterBean registerBean) {
                IBaseView view = getView();
                if (view instanceof RegisterContract.RegisterView){
                    ((RegisterContract.RegisterView) view).onRegisterSuccess(registerBean);
                }
            }

            @Override
            public void onRegisterError(String str) {
                IBaseView view = getView();
                if (view instanceof RegisterContract.RegisterView){
                   ((RegisterContract.RegisterView) view).onRegisterError(str);
                }
            }
        });
    }
}
