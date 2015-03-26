package com.daoguang.dopemaster.support.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/3/14.
 */
public class GetContext extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
