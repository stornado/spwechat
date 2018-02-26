package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author xwxia
 * @date 2018/2/26 11:27
 */
public class NewsArticle {
    private String title;
    private String description;
    @Field("pic_url")
    private String pictureUrl;
    private String url;

    public NewsArticle() {
    }

    public NewsArticle(String title, String description, String pictureUrl, String url) {
        this.title = title;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
