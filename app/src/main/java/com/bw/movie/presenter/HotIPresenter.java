package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.HotMovieBean;
import com.bw.movie.contract.HotContract;
import com.bw.movie.model.HotIModel;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:44
 *@Description:
 * */public class HotIPresenter extends BasePresenter implements HotContract.HotPresenter {

    private HotIModel mModel;

    public HotIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new HotIModel();
    }

    @Override
    public void getHot(int page, int count) {
    mModel.getHot(page, count, new HotContract.HotModel.HotICallBack() {
        @Override
        public void HotSuccess(HotMovieBean hotMovieBean) {
            IBaseView view = getView();
            if (view instanceof HotContract.HotView){
                ((HotContract.HotView) view).HotSuccess(hotMovieBean);
            }
        }

        @Override
        public void HotError(String str) {
            IBaseView view = getView();
            if (view instanceof HotContract.HotView){
               ((HotContract.HotView) view).HotError(str);
            }
        }
    });
    }
}
