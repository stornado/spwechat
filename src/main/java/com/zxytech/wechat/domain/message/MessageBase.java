package com.zxytech.wechat.domain.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author xwxia
 * @date 2018/2/26 10:19
 */
public abstract class MessageBase {
    @Id
    protected String id;

    @Field("to")
    protected String toUserName;
    @Field("from")
    protected String fromUserName;
    @Field("createAt")
    protected Long createTime;
    @Field("msg_type")
    protected MessageTypeEnum messageType;

    public MessageBase() {
    }

    public MessageBase(String toUserName, String fromUserName, Long createTime) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
    }

    public MessageBase(String toUserName, String fromUserName, Long createTime, MessageTypeEnum messageType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.messageType = messageType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "MessageBase{" +
                "id='" + id + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", messageType=" + messageType +
                '}';
    }
}
