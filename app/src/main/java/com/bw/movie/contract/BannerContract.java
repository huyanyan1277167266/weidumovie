package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:23:35
 *@Description:
 * */public interface BannerContract {
    interface BannerView extends IBaseView{
        void  onBannerSuccess(BannerBean bannerBean);
        void onBannerError(String str);

        void onReleaseSuccess(ReleaseBean releaseBean);
        void onReleaseError(String str);

        void onComingSuccess(ComingBean comingBean);
        void onComingError(String str);

        void HotSuccess(HotBean hotBean);
        void HotError(String str);

    }
    interface BannerPresenter{
        void getBanner();
        void getRelease(int page,int count);
        void getComing(int userId,String sessionId,int page,int count);
        void getHot(int page,int count);
}
    interface BannerModel{
        void getBanner(BannerICallBack iCallBack);
        interface BannerICallBack{
            void  onBannerSuccess(BannerBean bannerBean);
            void onBannerError(String str);
        }

        void getRelease(int page,int count,ReleaseICallBack iCallBack);
        interface ReleaseICallBack{
            void onReleaseSuccess(ReleaseBean releaseBean);
            void onReleaseError(String str);
        }
        void getComing(int userId,String sessionId,int page,int count,ComingICallBack iCallBack);
        interface ComingICallBack{
            void onComingSuccess(ComingBean comingBean);
            void onComingError(String str);
        }

        void getHot(int page,int count,HotICallBack iCallBack);
        interface HotICallBack{
            void HotSuccess(HotBean hotBean);
            void HotError(String str);
        }
    }
}
