package com.bw.movie.utiles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.bw.movie.base.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:cln
 *@Date: 2020/4/17
 *@Time:20:22
 *@Description:
 * */public class NetUtile {
     String BASE_URL="http://mobile.bwstudent.com/movieApi/";
    private Apis mApis;

    private NetUtile(){
         initNetUtile();
     }
     private static class SingleInstance{
       private static NetUtile INSTANCE=new NetUtile();
    }

    public static NetUtile getInstance() {
        return SingleInstance.INSTANCE;
    }
    //网络请求
    public boolean isWork(Context context){
       ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    private void initNetUtile() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
               .addInterceptor(new initInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit.Builder retrofitbuilder = new Retrofit.Builder();
        Retrofit retrofit = retrofitbuilder.baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApis = retrofit.create(Apis.class);

    }

    public Apis getmApis() {
        return mApis;
    }

    public class initInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String userId = SpUtile.getString(App.getAppContext(), SpUtile.USERINFO_NAME, SpUtile.USERINFO_KEY_USER_ID);
            String sessionId = SpUtile.getString(App.getAppContext(), SpUtile.USERINFO_NAME, SpUtile.USERINFO_KEY_SESSION_ID);
            if (TextUtils.isEmpty(userId)||TextUtils.isEmpty(sessionId)){
                return chain.proceed(request);
            }else{
                Request ak = request.newBuilder()
                        .addHeader("ak","0110010010000")
                        .addHeader("Content-Type","application/x-www-form-urlencoded")
                        .build();
                return chain.proceed(ak);
            }
        }
    }

}
