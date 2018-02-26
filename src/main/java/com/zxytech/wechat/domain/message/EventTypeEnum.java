package com.zxytech.wechat.domain.message;

/**
 * @author xwxia
 * @date 2018/2/26 10:27
 */
public enum EventTypeEnum {
    // 关注事件、未关注时扫描带参数二维码事件
    SUBSCRIBE("subscribe"),
    // 取消关注事件
    UNSUBSCRIBE("unsubscribe"),
    // 已关注时扫描带参数二维码事件
    SCAN("SCAN"),
    // 上报地理位置事件
    LOCATION("LOCATION"),
    // 自定义菜单点击菜单拉取消息事件推送
    CLICK("CLICK"),
    // 点击菜单跳转链接时间推送
    VIEW("VIEW");


    private String type;

    EventTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
