package com.zxytech.wechat.domain.message;

/**
 * @author xwxia
 * @date 2018/2/26 10:24
 */
public enum MessageTypeEnum {
    // 文本消息
    TEXT("text"),
    // 图片消息
    IMAGE("image"),
    // 语音消息
    VOICE("voice"),
    // 视频消息
    VIDEO("video"),
    // 小视频消息
    SHORT_VIDEO("shortvideo"),
    // 地理位置消息
    LOCATION("location"),
    // 链接消息
    LINK("link"),
    // 事件推送
    EVENT("event"),
    // 回复音乐消息
    MUSIC("music"),
    // 回复图文消息
    NEWS("news");


    private String type;

    MessageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
