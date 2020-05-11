package com.bw.movie.contract;


import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.RegisterBean;


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
        void onGuanSuccess(RegisterBean bean);
        void onGuanError(String str);

        void onQuGuanSuccess(RegisterBean bean);
        void onQuGuanError(String str);
    }

    interface MovieInIPresenter{
        void getMovieIn(int movieid);

        void getGuanData(int movieId);
        void getQuGuanData(int movieid);
    }

    interface MovieInIModel{
        void getMovieIn(int movieid, IModelCallBack icallBack);
        interface IModelCallBack{
            void onSuccess(MovieDataBean movieDataBean);
            void onError(String str);
        }

        void getGuanData(int movieId,IModelGuanCallBack callBack);
        interface IModelGuanCallBack{
            void onGuanSuccess(RegisterBean bean);
            void onGuanError(String str);
        }
        void getQuGuanData(int movieid,IModelQuGuanCallBack callBack);
        interface IModelQuGuanCallBack{
            void onQuGuanSuccess(RegisterBean bean);
            void onQuGuanError(String str);
        }
    }
}
