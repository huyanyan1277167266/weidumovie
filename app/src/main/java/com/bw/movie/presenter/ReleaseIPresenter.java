package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.ReleaseContract;
import com.bw.movie.model.ReleaseIModel;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:23:11
 *@Description:
 * */public class ReleaseIPresenter extends BasePresenter implements ReleaseContract.ReleasePresenter {

    private ReleaseIModel mModel;

    public ReleaseIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new ReleaseIModel();
    }

    @Override
    public void getRelease(int page, int count) {
        mModel.getRelease(page, count, new ReleaseContract.ReleaseModel.ReleaseICallBack() {
            @Override
            public void onReleaseSuccess(ReleaseBean releaseBean) {
                IBaseView view = getView();
                if (view instanceof ReleaseContract.ReleaseView){
                    ((ReleaseContract.ReleaseView) view).onReleaseSuccess(releaseBean);
                }
            }

            @Override
            public void onReleaseError(String str) {
                IBaseView view = getView();
                if (view instanceof ReleaseContract.ReleaseView){
                    ((ReleaseContract.ReleaseView) view).onReleaseError(str);
                }
            }
        });
    }
}
