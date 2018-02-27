package com.zxytech.wechat.domain.message;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author xwxia
 * @date 2018/2/26 10:24
 */
@XmlEnum
public enum MessageTypeEnum {
    // 文本消息
    @XmlEnumValue(value = "text")
    TEXT("text"),

    // 图片消息
    @XmlEnumValue(value = "image")
    IMAGE("image"),

    // 语音消息
    @XmlEnumValue(value = "voice")
    VOICE("voice"),

    // 视频消息
    @XmlEnumValue(value = "video")
    VIDEO("video"),

    // 小视频消息
    @XmlEnumValue(value = "shortvideo")
    SHORT_VIDEO("shortvideo"),

    // 地理位置消息
    @XmlEnumValue(value = "location")
    LOCATION("location"),

    // 链接消息
    @XmlEnumValue(value = "link")
    LINK("link"),

    // 事件推送
    @XmlEnumValue(value = "event")
    EVENT("event"),

    // 回复音乐消息
    @XmlEnumValue(value = "music")
    MUSIC("music"),

    // 回复图文消息
    @XmlEnumValue(value = "news")
    NEWS("news");


    private String type;

    MessageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @XmlCDATA
    public void setType(String type) {
        this.type = type;
    }
}
