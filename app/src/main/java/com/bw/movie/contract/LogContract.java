package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LogBean;
import com.bw.movie.bean.WXCodeBean;

/*
 *@Auther:cln
 *@Date: 2020/4/22
 *@Time:23:49
 *@Description:
 * */public interface LogContract {
     interface LogView extends IBaseView{
         void onLogSuccess(LogBean logBean);
         void onLogError(String str);

     }
     interface LogPresenter{
         void getLog(String email,String pwd);

     }
     interface LogModel{
         void getLog(String email,String pwd,LogICallBack iCallBack);
         interface LogICallBack {
             void onLogSuccess(LogBean logBean);
             void onLogError(String str);
         }


     }
}
