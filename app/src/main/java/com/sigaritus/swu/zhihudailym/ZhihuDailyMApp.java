package com.sigaritus.swu.zhihudailym;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ZhihuDailyMApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Iconify.with(new FontAwesomeModule());

    }

    public static Context getContext() {
        return mContext;
    }
}
