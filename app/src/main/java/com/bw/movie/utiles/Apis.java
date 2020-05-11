package com.bw.movie.utiles;

import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.HotMovieBean;
import com.bw.movie.bean.MovieDataBean;
import com.bw.movie.bean.ReleaseMovieBean;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ComingBean;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.LogBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.WXCodeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
 *@Auther:cln
 *@Date: 2020/4/17
 *@Time:20:22
 *@Description:
 * */public interface Apis {
     //邮箱
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<EmailBean>doEmail(@Field("email") String email);
    //注册
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<RegisterBean>doRegister(@Field("nickName") String nickName,@Field("pwd") String pwd,@Field("email") String email,@Field("code") String code);
    //登录
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LogBean>doLog(@Field("email") String email,@Field("pwd") String pwd);
    //微信登录
    @POST("user/v1/weChatBindingLogin")
    @FormUrlEncoded
    Observable<WXCodeBean>doWechat(@Field("code")String code);
    //baner轮播
    @GET("tool/v2/banner")
    Observable<BannerBean>doBanner();
    //正在上映电影列表
    @GET("movie/v2/findReleaseMovieList")
    Observable<ReleaseBean>doRelease(@Query("page")int page,@Query("count")int count);
    //即将上映电影列表
    @GET("movie/v2/findComingSoonMovieList")
    Observable<ComingBean>doComing(@Header("userId")int userId,@Header("sessionId")String sessionId
    ,@Query("page")int page,@Query("count")int count);
    //热门电影列表
    @GET("movie/v2/findHotMovieList")
    Observable<HotBean>doHot(@Query("page")int page,@Query("count")int count);
    //正在上映电影列表
    @GET("movie/v2/findReleaseMovieList")
    Observable<ReleaseMovieBean>doReleaseMovie(@Query("page")int page,@Query("count")int count);
    //即将上映电影列表  @Header("userId")int userId,@Header("sessionId")String sessionId,
    @GET("movie/v2/findComingSoonMovieList")
    Observable<ComingSoonBean>doComing(@Query("page")int page,@Query("count")int count);
    //热门电影列表
    @GET("movie/v2/findHotMovieList")
    Observable<HotMovieBean>doHotMovie(@Query("page")int page,@Query("count")int count);
    //查询电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<MovieDataBean> domovieInfo(@Query("movieId")int movieId);
}
