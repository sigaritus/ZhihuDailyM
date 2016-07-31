package com.sigaritus.swu.zhihudailym.network.api;

import com.sigaritus.swu.zhihudailym.bean.ThemeDetailResult;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotResult;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuLatestResult;
import com.sigaritus.swu.zhihudailym.bean.ZhihuStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuThemes;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/12.
 */
public interface ZhihuApi {

    String BASE_URL = "http://news-at.zhihu.com/api/4/";
    /*
    start image from zhihu
     */
    String START_IMG="start-image/1080*1776";

    String VERSION = "version/android/2.3.0";

    String LATEST ="news/latest";
    String THEMES ="themes";
    /*
    若果需要查询 11 月 18 日的消息，before 后的数字应为 20131119
    知乎日报的生日为 2013 年 5 月 19 日，若 before 后数字小于 20130520 ，只会接收到空消息
     */
    String HISTORY ="news/before/20131119";
    String HOT ="news/hot";

    String LONG_COMMNETS ="story/%1$s/long-comments";

    String SHORT_COMMENTS ="story/%1$s/short-comments";

    @GET("news/latest")
    Observable<ZhihuLatestResult> getLatestStory();

    @GET("news/hot")
    Observable<ZhihuHotResult> getHotStory();

    @GET("news/{id}")
    Observable<ZhihuDetailStory> getDetailStory(@Path("id") String id);

    @GET("themes")
    Observable<ZhihuThemes> getThemes();

    @GET("theme/{id}")
    Observable<ThemeDetailResult> getThemeDetail(@Path("id") String id);
}
