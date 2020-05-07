package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ReleaseBean;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:23:03
 *@Description:
 * */public interface ReleaseContract {
     interface ReleaseView extends IBaseView{
         void onReleaseSuccess(ReleaseBean releaseBean);
         void onReleaseError(String str);
     }
     interface ReleasePresenter{
         void getRelease(int page,int count);
     }
     interface ReleaseModel{
         void getRelease(int page,int count,ReleaseICallBack iCallBack);
         interface ReleaseICallBack{
             void onReleaseSuccess(ReleaseBean releaseBean);
             void onReleaseError(String str);
         }

     }
}
