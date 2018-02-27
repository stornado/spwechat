package com.zxytech.wechat.domain.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * @author xwxia
 * @date 2018/2/26 10:19
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class MessageBase implements Serializable {

    @XmlTransient
    @Id
    protected String id;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "ToUserName")
    @Field("to")
    protected String toUserName;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "FromUserName")
    @Field("from")
    protected String fromUserName;

    @XmlElement(name = "CreateTime")
    @Field("createAt")
    protected Long createTime;

    @XmlJavaTypeAdapter(XmlCDataAdapter.MessageTypeEnumAdapter.class)
    @XmlElement(name = "MsgType")
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

    @XmlTransient
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlTransient
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @XmlTransient
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @XmlTransient
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
