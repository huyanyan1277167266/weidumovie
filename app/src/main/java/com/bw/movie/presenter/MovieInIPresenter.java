package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegisterBean;
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

    @Override
    public void getGuanData(int movieId) {
     mMdel.getGuanData(movieId, new MovieInContract.MovieInIModel.IModelGuanCallBack() {
         @Override
         public void onGuanSuccess(RegisterBean bean) {
             IBaseView view = getView();
             if (view instanceof MovieInContract.IView){
                 ((MovieInContract.IView) view).onGuanSuccess(bean);
             }
         }

         @Override
         public void onGuanError(String str) {
             IBaseView view = getView();
             if (view instanceof MovieInContract.IView){
                 ((MovieInContract.IView) view).onGuanError(str);
             }
         }
     });
    }

    @Override
    public void getQuGuanData(int movieid) {
        mMdel.getQuGuanData(movieid, new MovieInContract.MovieInIModel.IModelQuGuanCallBack() {
            @Override
            public void onQuGuanSuccess(RegisterBean bean) {
                IBaseView view = getView();
                if (view instanceof MovieInContract.IView){
                    ((MovieInContract.IView) view).onQuGuanSuccess(bean);
                }
            }

            @Override
            public void onQuGuanError(String str) {
                IBaseView view = getView();
                if (view instanceof MovieInContract.IView){
                   ((MovieInContract.IView) view).onQuGuanError(str);
                }
            }
        });
    }
}
