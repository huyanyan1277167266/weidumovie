package com.bw.movie.utiles;

import android.content.Context;
import android.content.SharedPreferences;

/*
 *@Auther:cln
 *@Date: 2020/4/17
 *@Time:20:32
 *@Description:
 * */public class SpUtile {
    public static final String USERINFO_NAME = "userinfo";
    public static final String USERINFO_KEY_USER_ID = "userId";
    public static final String USERINFO_KEY_SESSION_ID = "sessionId";
    public static final String USERINFO_KEY_GOOD_ID = "goodid";
    public static final String USERINFO_KEY_IS_REMEBER_PWD = "remeber_pwd";
    public static final String USERINFO_KEY_USER_PHONE = "phone";
    public static final String USERINFO_KEY_USER_PWD = "pwd";

    public static void putString(Context context, String name, String key, String value){
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key,value);
        edit.commit();
    }

    public static String getString(Context context,String name,String key){
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    public static void putGoodid(Context context,String name,String key,int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key,value);
        edit.commit();
    }

    public static int getGoodid(Context context,String name,String key){
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getInt(key,0);
    }

    public static void putBoolean(Context context,String name,String key,boolean value){
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key,value);
        edit.commit();
    }

    public static boolean getBoolean(Context context,String name,String key){
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }
}
