package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.BannerContract;
import com.bw.movie.model.BannerIModel;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:23:43
 *@Description:
 * */public class BannerIPresenter extends BasePresenter implements BannerContract.BannerPresenter {

    private BannerIModel mModel;

    public BannerIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new BannerIModel();
    }

    @Override
    public void getBanner() {
        mModel.getBanner(new BannerContract.BannerModel.BannerICallBack() {
            @Override
            public void onBannerSuccess(BannerBean bannerBean) {
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                    ((BannerContract.BannerView) view).onBannerSuccess(bannerBean);
                }
            }

            @Override
            public void onBannerError(String str) {
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                    ((BannerContract.BannerView) view).onBannerError(str);
                }
            }
        });
    }

    @Override
    public void getRelease(int page, int count) {
        mModel.getRelease(page, count, new BannerContract.BannerModel.ReleaseICallBack() {
            @Override
            public void onReleaseSuccess(ReleaseBean releaseBean) {
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                    ((BannerContract.BannerView) view).onReleaseSuccess(releaseBean);
                }
            }

            @Override
            public void onReleaseError(String str) {
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                   ((BannerContract.BannerView) view).onReleaseError(str);
                }
            }
        });
    }

    @Override
    public void getComing(int userId, String sessionId, int page, int count) {
        mModel.getComing(userId, sessionId, page, count, new BannerContract.BannerModel.ComingICallBack() {
            @Override
            public void onComingSuccess(ComingBean comingBean) {
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                    ((BannerContract.BannerView) view).onComingSuccess(comingBean);
                }
            }

            @Override
            public void onComingError(String str) {
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                  ((BannerContract.BannerView) view).onComingError(str);
                }
            }
        });
    }

    @Override
    public void getHot(int page, int count) {
        mModel.getHot(page, count, new BannerContract.BannerModel.HotICallBack() {
            @Override
            public void HotSuccess(HotBean hotBean) {
                Log.i("bbb","p成功");
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                    ((BannerContract.BannerView) view).HotSuccess(hotBean);
                }
            }

            @Override
            public void HotError(String str) {
                Log.i("bbb","p失败");
                IBaseView view = getView();
                if (view instanceof BannerContract.BannerView){
                    ((BannerContract.BannerView) view).HotError(str);
                }
            }
        });
    }
}
