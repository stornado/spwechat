package com.zxytech.wechat.domain.message;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author xwxia
 * @date 2018/2/27 13:53
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class EncryptMessage {

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "ToUserName")
    private String toUserName;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "Encrypt")
    private String encrypt;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "MsgSignature")
    protected String msgSignature;

    @XmlElement(name = "TimeStamp")
    private Long timestamp;

    @XmlJavaTypeAdapter(XmlCDataAdapter.StringFieldAdapter.class)
    @XmlElement(name = "Nonce")
    protected String nonce;

    public EncryptMessage() {
    }

    public EncryptMessage(String encrypt, String msgSignature, Long timestamp, String nonce) {
        this.encrypt = encrypt;
        this.msgSignature = msgSignature;
        this.timestamp = timestamp;
        this.nonce = nonce;
    }

    @XmlTransient
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlTransient
    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    @XmlTransient
    public String getMsgSignature() {
        return msgSignature;
    }

    public void setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
    }

    @XmlTransient
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return "EncryptMessage{" +
                "toUserName='" + toUserName + '\'' +
                ", encrypt='" + encrypt + '\'' +
                ", msgSignature='" + msgSignature + '\'' +
                ", timestamp=" + timestamp +
                ", nonce='" + nonce + '\'' +
                '}';
    }
}
