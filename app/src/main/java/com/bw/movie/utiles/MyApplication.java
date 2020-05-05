package com.bw.movie.utiles;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/*
 *@Auther:cln
 *@Date: 2020/4/25
 *@Time:0:04
 *@Description:
 * */public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DiskCacheConfig config=DiskCacheConfig.newBuilder(this)
                .setMaxCacheSize(10*1024*1024)
                .setBaseDirectoryName("image")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();
        ImagePipelineConfig build=ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(config)
                .build();
        Fresco.initialize(this,build);
    }
}
