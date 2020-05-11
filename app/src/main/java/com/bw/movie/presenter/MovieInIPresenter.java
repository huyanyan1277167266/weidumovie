package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.contract.MovieInContract;
import com.bw.movie.model.MovieInIMdel;

/*
 *@Auther:cln
 *@Date: 2020/5/11
 *@Time:2:36
 *@Description:
 * */public class MovieInIPresenter extends BasePresenter implements MovieInContract.MovieInIPresenter {


    private MovieInIMdel mMdel;

    public MovieInIPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mMdel = new MovieInIMdel();

    }

    @Override
    public void getMovieIn(int movieid) {
        mMdel.getMovieIn(movieid, new MovieInContract.MovieInIModel.IModelCallBack() {
            @Override
            public void onSuccess(MovieDataBean movieDataBean) {
                IBaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onSuccess(movieDataBean);
                }
            }

            @Override
            public void onError(String str) {
                IBaseView view = getView();
                if (view instanceof MovieInContract.IView){
                   ((MovieInContract.IView) view).onError(str);
                }
            }
        });
    }
}
