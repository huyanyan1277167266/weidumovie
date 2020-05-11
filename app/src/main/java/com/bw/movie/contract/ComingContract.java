package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.bean.ComingSoonBean;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:23:15
 *@Description:
 * */public interface ComingContract {
     interface ComingView extends IBaseView{
         void onComingSuccess(ComingSoonBean comingSoonBean);
         void onComingError(String str);
     }
     interface ComingPresenter{
         void getComing(int page,int count);
     }
     interface ComingModel{
         void getComing(int page,int count,ComingICallBack iCallBack);
         interface ComingICallBack{
             void onComingSuccess(ComingSoonBean comingSoonBean);
             void onComingError(String str);
         }
     }
}
