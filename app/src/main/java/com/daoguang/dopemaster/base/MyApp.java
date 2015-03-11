package com.daoguang.dopemaster.base;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by joker on 2015/3/11.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, Config.APP_ID,Config.APP_KEY);

    }
}
