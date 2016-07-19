package com.sigaritus.swu.zhihudailym.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ZhihuHotResult {
    public @SerializedName("recent")
    List<ZhihuHotStory> recent;
}
