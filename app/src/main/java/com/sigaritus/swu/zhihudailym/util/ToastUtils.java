package com.sigaritus.swu.zhihudailym.util;

import android.view.View;
import android.widget.Toast;

import com.sigaritus.swu.zhihudailym.ZhihuDailyMApp;

/**
 * Created by Administrator on 2015/10/26.
 */
public class ToastUtils {
    public static void showLong(String message){

        Toast.makeText(ZhihuDailyMApp.getContext(),message,Toast.LENGTH_LONG).show();
        
    }
    public static void showShort(String message){

        Toast.makeText(ZhihuDailyMApp.getContext(),message,Toast.LENGTH_SHORT).show();

    }

}
