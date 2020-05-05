package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;

/*
 *@Auther:cln
 *@Date: 2020/4/22
 *@Time:14:05
 *@Description:
 * */public interface RegisterContract {
     interface RegisterView extends IBaseView{
         void onEmailSuccess(EmailBean emailBean);
         void onEmailError(String str);

         void onRegisterSuccess(RegisterBean registerBean);
         void onRegisterError(String str);
     }
     interface RegisterPresenter{
         void getEmail(String email);

         void getRegister(String nickName,String pwd,String email,String code);
     }
     interface RegisterModel{
         void getRegister(String nickName,String pwd,String email,String code,RegisterICallBack iCallBack);
         interface RegisterICallBack{
             void onRegisterSuccess(RegisterBean registerBean);
             void onRegisterError(String str);
         }

         void getEmail(String email,EmailICallBack iCallBack);
         interface EmailICallBack{
             void onEmailSuccess(EmailBean emailBean);
             void onEmailError(String str);
         }
     }
}
