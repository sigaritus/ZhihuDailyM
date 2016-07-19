package com.sigaritus.swu.zhihudailym;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ZhihuDailyMApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }
}
