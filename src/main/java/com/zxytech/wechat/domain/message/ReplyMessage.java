package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author xwxia
 * @date 2018/2/26 10:46
 */
@Document(collection = "reply_message")
public class ReplyMessage extends MessageBase {

    private String content;

    /**
     * 图片、语音、视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @Field("media_id")
    private String mediaId;
    private String title;
    private String description;

    @Field("music_url")
    private String musicUrl;
    @Field("hq_music_url")
    private String hqMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    @Field("thumb_id")
    private String thumbMediaId;

    @Field("article_count")
    private Byte articleCount;
    private List<NewsArticle> articles;
    @Field("pic_url")
    private String pictureUrl;
    private String url;

    public ReplyMessage() {
    }

    /**
     * 回复消息
     *
     * @param toUserName   接收方帐号（收到的OpenID）
     * @param fromUserName 开发者微信号
     * @param createTime   消息创建时间 （整型）
     */
    public ReplyMessage(String toUserName, String fromUserName, Long createTime) {
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public Byte getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Byte articleCount) {
        this.articleCount = articleCount;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ReplyMessage{" +
                "id='" + id + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", messageType=" + messageType +
                '}';
    }

    public static class MessageBuilder {
        private ReplyMessage message;

        /**
         * @param toUserName   接收方帐号（收到的OpenID）
         * @param fromUserName 开发者微信号
         * @param createTime   消息创建时间 （整型）
         */
        public MessageBuilder(String toUserName, String fromUserName, Long createTime) {
            this.message = new ReplyMessage(toUserName, fromUserName, createTime);
        }

        /**
         * 回复文本消息
         *
         * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
         * @return
         */
        public ReplyMessage prepareTextMessage(String content) {
            this.message.setMessageType(MessageTypeEnum.TEXT);
            this.message.setContent(content);
            return this.message;
        }

        /**
         * 回复图片消息
         *
         * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id。
         * @return
         */
        public ReplyMessage prepareImageMessage(String mediaId) {
            this.message.setMessageType(MessageTypeEnum.IMAGE);
            this.message.setMediaId(mediaId);
            return this.message;
        }

        /**
         * 回复语音消息
         *
         * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
         * @return
         */
        public ReplyMessage prepareVoiceMessage(String mediaId) {
            this.message.setMessageType(MessageTypeEnum.VOICE);
            this.message.setMediaId(mediaId);
            return this.message;
        }

        /**
         * 回复视频消息
         *
         * @param mediaId     通过素材管理中的接口上传多媒体文件，得到的id
         * @param title       视频消息的标题
         * @param description 视频消息的描述
         * @return
         */
        public ReplyMessage prepareVideoMessage(String mediaId, String title, String description) {
            this.message.setMessageType(MessageTypeEnum.VIDEO);
            this.message.setMediaId(mediaId);
            this.message.setTitle(title);
            this.message.setDescription(description);
            return this.message;
        }

        /**
         * 回复音乐消息
         *
         * @param title        音乐标题
         * @param description  音乐描述
         * @param musicUrl     音乐链接
         * @param hqMusicUrl   高质量音乐链接，WIFI环境优先使用该链接播放音乐
         * @param thumbMediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
         * @return
         */
        public ReplyMessage prepareMusicMessage(String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) {
            this.message.setMessageType(MessageTypeEnum.MUSIC);
            this.message.setTitle(title);
            this.message.setDescription(description);
            this.message.setMusicUrl(musicUrl);
            this.message.setHqMusicUrl(hqMusicUrl);
            this.message.setThumbMediaId(thumbMediaId);
            return this.message;
        }

        /**
         * 回复图文消息
         *
         * @param articles 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
         * @return
         */
        public ReplyMessage prepareNewsMessage(List<NewsArticle> articles) {
            this.message.setMessageType(MessageTypeEnum.NEWS);
            this.message.setArticleCount((byte) articles.size());
            this.message.setArticles(articles);
            // 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
            this.message.setTitle(articles.get(0).getTitle());
            this.message.setDescription(articles.get(0).getDescription());
            this.message.setPictureUrl(articles.get(0).getPictureUrl());
            this.message.setUrl(articles.get(0).getUrl());
            return this.message;
        }
    }
}
