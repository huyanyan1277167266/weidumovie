package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.HotMovieBean;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:39
 *@Description:
 * */public interface HotContract {
     interface HotView extends IBaseView{
         void HotSuccess(HotMovieBean hotMovieBean);
         void HotError(String str);
     }
     interface HotPresenter{
         void getHot(int page,int count);
     }
     interface HotModel{
         void getHot(int page,int count,HotICallBack iCallBack);
         interface HotICallBack{
             void HotSuccess(HotMovieBean hotMovieBean);
             void HotError(String str);
         }
     }
}
