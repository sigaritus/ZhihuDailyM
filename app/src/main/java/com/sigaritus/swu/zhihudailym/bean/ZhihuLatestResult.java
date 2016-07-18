package com.sigaritus.swu.zhihudailym.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class ZhihuLatestResult {
    public String date;
    public @SerializedName("stories")
    List<ZhihuStory> stories;
    public @SerializedName("top_stories")
    List<ZhihuTopStory> topStories;
}
