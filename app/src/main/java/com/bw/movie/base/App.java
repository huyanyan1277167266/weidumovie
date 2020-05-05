package com.bw.movie.base;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/*
 *@Auther:cln
 *@Date: 2020/4/17
 *@Time:20:38
 *@Description:
 * */public class App extends Application {
    private static final String APP_ID = "wxb3852e6a6b7d9516";
    private static IWXAPI api;
private static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mcontext=getApplicationContext();
        regToWx();
    }
    public static Context getAppContext(){
        return mcontext;
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }
    public static IWXAPI getWxApi(){
        return api;
    }
}
