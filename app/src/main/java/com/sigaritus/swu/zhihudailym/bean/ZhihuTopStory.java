package com.sigaritus.swu.zhihudailym.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/7/18.
 */
@Entity( nameInDb = "ZHIHU_STORY")
public class ZhihuTopStory {

    @Id
    private int id;
    private String title;
    private String image;
    @Generated(hash = 1923475537)
    public ZhihuTopStory(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    @Generated(hash = 1819061576)
    public ZhihuTopStory() {
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
