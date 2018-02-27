package com.zxytech.wechat.domain.message;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author xwxia
 * @date 2018/2/26 10:27
 */
@XmlEnum
public enum EventTypeEnum {
    // 关注事件、未关注时扫描带参数二维码事件
    @XmlEnumValue(value = "subscribe")
    SUBSCRIBE("subscribe"),

    // 取消关注事件
    @XmlEnumValue(value = "unsubscribe")
    UNSUBSCRIBE("unsubscribe"),

    // 已关注时扫描带参数二维码事件
    @XmlEnumValue(value = "SCAN")
    SCAN("SCAN"),

    // 上报地理位置事件
    @XmlEnumValue(value = "LOCATION")
    LOCATION("LOCATION"),

    // 自定义菜单点击菜单拉取消息事件推送
    @XmlEnumValue(value = "CLICK")
    CLICK("CLICK"),

    // 点击菜单跳转链接时间推送
    @XmlEnumValue(value = "VIEW")
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
