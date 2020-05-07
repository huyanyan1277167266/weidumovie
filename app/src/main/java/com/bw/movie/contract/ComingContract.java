package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ComingBean;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:23:15
 *@Description:
 * */public interface ComingContract {
     interface ComingView extends IBaseView{
         void onComingSuccess(ComingBean comingBean);
         void onComingError(String str);
     }
     interface ComingPresenter{
         void getComing(int userId,String sessionId,int page,int count);
     }
     interface ComingModel{
         void getComing(int userId,String sessionId,int page,int count,ComingICallBack iCallBack);
         interface ComingICallBack{
             void onComingSuccess(ComingBean comingBean);
             void onComingError(String str);
         }
     }
}
