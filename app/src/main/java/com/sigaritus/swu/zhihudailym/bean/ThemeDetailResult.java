package com.sigaritus.swu.zhihudailym.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/7/31.
 */
public class ThemeDetailResult {
    public @SerializedName("stories")
    List<ZhihuStory> storyList;
    public String description;
    public String background;
    public String name;


}
