package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LogBean;
import com.bw.movie.contract.LogContract;
import com.bw.movie.model.LogIModel;

/*
 *@Auther:cln
 *@Date: 2020/4/22
 *@Time:23:54
 *@Description:
 * */public class LogIPresenter extends BasePresenter implements LogContract.LogPresenter {

    private LogIModel mModel;

    public LogIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new LogIModel();
    }

    @Override
    public void getLog(String email, String pwd) {
        mModel.getLog(email, pwd, new LogContract.LogModel.LogICallBack() {
            @Override
            public void onLogSuccess(LogBean logBean) {
                IBaseView view = getView();
                if (view instanceof LogContract.LogView) {
                    ((LogContract.LogView) view).onLogSuccess(logBean);
                }
            }

            @Override
            public void onLogError(String str) {
                IBaseView view = getView();
                if (view instanceof LogContract.LogView) {
                    ((LogContract.LogView) view).onLogError(str);
                }
            }


        });
    }


}
