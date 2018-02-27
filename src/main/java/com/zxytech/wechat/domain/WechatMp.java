package com.zxytech.wechat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/**
 * @author xwxia
 * @date 2018/2/26 15:23
 */
@Document(collection = "wechat_mp")
public class WechatMp {
    @Id
    private String id;

    @Field("app_id")
    private String appId;
    @Field("app_secret")
    private String appSecret;

    private String token;
    @Field("aes_key")
    private String encodingAesKey;

    public WechatMp() {
    }

    public WechatMp(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public WechatMp(String appId, String appSecret, String token, String encodingAesKey) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.token = token;
        this.encodingAesKey = encodingAesKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    @Override
    public String toString() {
        return "WechatMp{" +
                "id='" + id + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", token='" + token + '\'' +
                ", encodingAesKey='" + encodingAesKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WechatMp)) {
            return false;
        }
        WechatMp wechatMp = (WechatMp) o;
        return Objects.equals(getAppId(), wechatMp.getAppId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAppId());
    }
}
