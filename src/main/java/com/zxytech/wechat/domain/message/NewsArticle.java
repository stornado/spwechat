package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * @author xwxia
 * @date 2018/2/26 11:27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "item")
public class NewsArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "Title")
    private String title;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "Description")
    private String description;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "PicUrl")
    @Field("pic_url")
    private String pictureUrl;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "Url")
    private String url;

    public NewsArticle() {
    }

    public NewsArticle(String title, String description, String pictureUrl, String url) {
        this.title = title;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.url = url;
    }

    @XmlTransient
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @XmlTransient
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
