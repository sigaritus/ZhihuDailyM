package com.sigaritus.swu.zhihudailym.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mayinda on 2016/7/27.
 */
@Entity
public class ZhihuDetailStory {
    @Id
    private String id;
    @Property(nameInDb = "BODY")
    private String body;
    @Property(nameInDb = "IMAGE_SOURCE")
    private String image_source;
    @Property(nameInDb = "TITLE")
    private String title;
    @Property(nameInDb = "IMAGE")
    private String image;
    @Property(nameInDb = "SHARE_URL")
    private String share_url;

    @Generated(hash = 1472134751)
    public ZhihuDetailStory(String id, String body, String image_source,
            String title, String image, String share_url) {
        this.id = id;
        this.body = body;
        this.image_source = image_source;
        this.title = title;
        this.image = image;
        this.share_url = share_url;
    }

    @Generated(hash = 122774736)
    public ZhihuDetailStory() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
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

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}