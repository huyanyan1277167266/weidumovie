package com.bw.movie.presenter;


import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.contract.ComingContract;
import com.bw.movie.model.ComingIModel;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:23:29
 *@Description:
 * */public class ComingIPresenter extends BasePresenter implements ComingContract.ComingPresenter {


    private ComingIModel mModel;

    public ComingIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new ComingIModel();
    }

    @Override
    public void getComing(int page, int count) {
        mModel.getComing(page, count, new ComingContract.ComingModel.ComingICallBack() {
            @Override
            public void onComingSuccess(ComingBean comingBean) {
                IBaseView view = getView();
                if (view instanceof ComingContract.ComingView){
                    ((ComingContract.ComingView) view).onComingSuccess(comingBean);
                }
            }

            @Override
            public void onComingError(String str) {
                IBaseView view = getView();
                if (view instanceof ComingContract.ComingView){
                    ((ComingContract.ComingView) view).onComingError(str);
                }
            }
        });
    }
}
