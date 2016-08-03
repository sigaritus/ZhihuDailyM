package com.sigaritus.swu.zhihudailym.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mayinda on 2016/8/3.
 */
public class ZhihuHistoryStoryResult {
    public @SerializedName("stories")
    List<ZhihuStory> HistoryStoryList;
}
