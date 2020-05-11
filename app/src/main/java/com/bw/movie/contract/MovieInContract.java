package com.bw.movie.contract;


import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.MovieDataBean;


/**
 * @ClassName MovieInContract
 * @Description TODO
 * @Author tys
 * @Date 2020/4/2223:15
 */
public interface MovieInContract {
    interface IView extends IBaseView {
        void onSuccess(MovieDataBean movieDataBean);
        void onError(String str);

    }

    interface MovieInIPresenter{
        void getMovieIn(int movieid);

    }

    interface MovieInIModel{
        void getMovieIn(int movieid, IModelCallBack icallBack);
        interface IModelCallBack{
            void onSuccess(MovieDataBean movieDataBean);
            void onError(String str);
        }

    }
}
