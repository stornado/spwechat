package com.zxytech.wechat.domain.message;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author xwxia
 * @date 2018/2/26 10:24
 */
@XmlEnum
public enum MessageTypeEnum {
    @XmlEnumValue(value = "text")
    TEXT("text", "文本消息"),

    @XmlEnumValue(value = "image")
    IMAGE("image", "图片消息"),

    @XmlEnumValue(value = "voice")
    VOICE("voice", "语音消息"),

    @XmlEnumValue(value = "video")
    VIDEO("video", "视频消息"),

    @XmlEnumValue(value = "shortvideo")
    SHORT_VIDEO("shortvideo", "小视频消息"),

    @XmlEnumValue(value = "location")
    LOCATION("location", "地理位置消息"),

    @XmlEnumValue(value = "link")
    LINK("link", "链接消息"),

    @XmlEnumValue(value = "event")
    EVENT("event", "事件推送"),

    // 回复
    @XmlEnumValue(value = "music")
    MUSIC("music", "音乐消息"),

    // 回复
    @XmlEnumValue(value = "news")
    NEWS("news", "图文消息");


    private String type;
    private String displayName;

    MessageTypeEnum(String type, String displayName) {
        this.type = type;
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
