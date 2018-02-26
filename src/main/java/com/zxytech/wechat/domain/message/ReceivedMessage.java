package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author xwxia
 * @date 2018/2/26 10:45
 */
@Document(collection = "recv_message")
public class ReceivedMessage extends MessageBase {

    private String content;
    @Field("msg_id")
    private String messageId;

    @Field("pic_url")
    private String pictureUrl;
    /**
     * 图片、语音、视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @Field("media_id")
    private String mediaId;

    /**
     * 语音格式：amr
     */
    private String format;
    /**
     * 语音识别结果，UTF8编码
     */
    private String recognition;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @Field("thumb_id")
    private String thumbMediaId;

    /**
     * 地理位置经度
     * <p>
     * 普通消息使用 Location_Y
     */
    private Float longitude;
    /**
     * 地理位置纬度
     * <p>
     * 普通消息使用 Location_X
     */
    private Float latitude;
    /**
     * 地图缩放大小
     */
    private Short scale;
    /**
     * 地理位置信息
     */
    private String label;

    private String title;
    private String description;
    private String url;

    @Field("event")
    private EventTypeEnum eventType;
    @Field("event_key")
    private String eventKey;
    private String ticket;

    /**
     * 地理位置精度
     */
    private Float precision;

    public ReceivedMessage() {
    }

    /**
     * @param toUserName   开发者微信号
     * @param fromUserName 发送方帐号（一个OpenID）
     * @param createTime   消息创建时间 （整型）
     */
    public ReceivedMessage(String toUserName, String fromUserName, Long createTime) {
        super(toUserName, fromUserName, createTime);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Short getScale() {
        return scale;
    }

    public void setScale(Short scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Float getPrecision() {
        return precision;
    }

    public void setPrecision(Float precision) {
        this.precision = precision;
    }

    @Override
    public String toString() {
        return "ReceivedMessage{}" +
                "id='" + id + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", messageType=" + messageType +
                '}';
    }

    public static class MessageBuilder {
        private ReceivedMessage message;

        /**
         * @param toUserName   开发者微信号
         * @param fromUserName 发送方帐号（一个OpenID）
         * @param createTime   消息创建时间 （整型）
         */
        public MessageBuilder(String toUserName, String fromUserName, Long createTime) {
            this.message = new ReceivedMessage(toUserName, fromUserName, createTime);
        }


        /**
         * 文本消息
         *
         * @param content   文本消息内容
         * @param messageId 消息id，64位整型
         * @return
         */
        public ReceivedMessage prepareTextMessage(String content, String messageId) {
            this.message.setMessageType(MessageTypeEnum.TEXT);
            this.message.setContent(content);
            this.message.setMessageId(messageId);
            return this.message;
        }

        /**
         * 图片消息
         *
         * @param pictureUrl 图片链接（由系统生成）
         * @param mediaId    图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
         * @param messageId  消息id，64位整型
         * @return
         */
        public ReceivedMessage prepareImageMessage(String pictureUrl, String mediaId, String messageId) {
            this.message.setMessageType(MessageTypeEnum.IMAGE);
            this.message.setPictureUrl(pictureUrl);
            this.message.setMediaId(mediaId);
            this.message.setMessageId(messageId);
            return this.message;
        }

        /**
         * 语音消息
         *
         * @param mediaId   语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
         * @param format    语音格式，如amr，speex等
         * @param messageId 消息id，64位整型
         * @return
         */
        public ReceivedMessage prepareVoiceMessage(String mediaId, String format, String messageId) {
            this.message.setMessageType(MessageTypeEnum.VOICE);
            this.message.setMediaId(mediaId);
            this.message.setFormat(format);
            this.message.setMessageId(messageId);
            return this.message;
        }

        /**
         * 视频消息
         *
         * @param mediaId      视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
         * @param thumbMediaId 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
         * @param messageId    消息id，64位整型
         * @return
         */
        public ReceivedMessage prepareVideoMessage(String mediaId, String thumbMediaId, String messageId) {
            this.message.setMessageType(MessageTypeEnum.VIDEO);
            this.message.setMediaId(mediaId);
            this.message.setThumbMediaId(thumbMediaId);
            this.message.setMessageId(messageId);
            return this.message;
        }

        /**
         * 小视频消息
         *
         * @param mediaId      视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
         * @param thumbMediaId 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
         * @param messageId    消息id，64位整型
         * @return
         */
        public ReceivedMessage prepareShortVideoMessage(String mediaId, String thumbMediaId, String messageId) {
            this.message.setMessageType(MessageTypeEnum.SHORT_VIDEO);
            this.message.setMediaId(mediaId);
            this.message.setThumbMediaId(thumbMediaId);
            this.message.setMessageId(messageId);
            return this.message;
        }


        /**
         * 地理位置消息
         *
         * @param locationX 地理位置维度
         * @param locationY 地理位置经度
         * @param scale     地图缩放大小
         * @param label     地理位置信息
         * @param messageId 消息id，64位整型
         * @return
         */
        public ReceivedMessage prepareLocationMessage(Float locationX, Float locationY, Short scale, String label, String messageId) {
            this.message.setMessageType(MessageTypeEnum.LOCATION);
            this.message.setLatitude(locationX);
            this.message.setLongitude(locationY);
            this.message.setScale(scale);
            this.message.setLabel(label);
            this.message.setMessageId(messageId);
            return this.message;
        }

        /**
         * 链接消息
         *
         * @param title       消息标题
         * @param description 消息描述
         * @param url         消息链接
         * @param messageId   消息id，64位整型
         * @return
         */
        public ReceivedMessage prepareLinkMessage(String title, String description, String url, String messageId) {
            this.message.setMessageType(MessageTypeEnum.LINK);
            this.message.setTitle(title);
            this.message.setDescription(description);
            this.message.setUrl(url);
            this.message.setMessageId(messageId);
            return this.message;
        }

        /**
         * 关注关注事件
         *
         * @return
         */
        public ReceivedMessage prepareSubscribeEvent() {
            this.message.setMessageType(MessageTypeEnum.EVENT);
            this.message.setEventType(EventTypeEnum.SUBSCRIBE);
            return this.message;
        }

        /**
         * 取消关注事件
         *
         * @return
         */
        public ReceivedMessage prepareUnsubscribeEvent() {
            this.message.setMessageType(MessageTypeEnum.EVENT);
            this.message.setEventType(EventTypeEnum.UNSUBSCRIBE);
            return this.message;
        }

        /**
         * 扫描带参数二维码事件
         * 1. 用户未关注时，进行关注后的事件推送
         *
         * @param eventKey 事件KEY值，qrscene_为前缀，后面为二维码的参数值
         * @param ticket   二维码的ticket，可用来换取二维码图片
         * @return
         */
        public ReceivedMessage prepareSubscribeEvent(String eventKey, String ticket) {
            this.message.setMessageType(MessageTypeEnum.EVENT);
            this.message.setEventType(EventTypeEnum.SUBSCRIBE);
            this.message.setEventKey(eventKey);
            this.message.setTicket(ticket);
            return this.message;
        }

        /**
         * 扫描带参数二维码事件
         * 2. 用户已关注时的事件推送
         *
         * @param eventKey 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
         * @param ticket   二维码的ticket，可用来换取二维码图片
         * @return
         */
        public ReceivedMessage prepareScanEvent(String eventKey, String ticket) {
            this.message.setMessageType(MessageTypeEnum.EVENT);
            this.message.setEventType(EventTypeEnum.SCAN);
            this.message.setEventKey(eventKey);
            this.message.setTicket(ticket);
            return this.message;
        }

        /**
         * 上报地理位置事件
         *
         * @param latitude  地理位置纬度
         * @param longitude 地理位置经度
         * @param precision 地理位置精度
         * @return
         */
        public ReceivedMessage prepareLocationEvent(Float latitude, Float longitude, Float precision) {
            this.message.setMessageType(MessageTypeEnum.EVENT);
            this.message.setEventType(EventTypeEnum.LOCATION);
            this.message.setLatitude(latitude);
            this.message.setLongitude(longitude);
            this.message.setPrecision(precision);
            return this.message;
        }

        /**
         * 点击菜单拉取消息时的事件推送
         *
         * @param eventKey 事件KEY值，与自定义菜单接口中KEY值对应
         * @return
         */
        public ReceivedMessage prepareMenuClickEvent(String eventKey) {
            this.message.setMessageType(MessageTypeEnum.EVENT);
            this.message.setEventType(EventTypeEnum.CLICK);
            this.message.setEventKey(eventKey);
            return this.message;
        }

        /**
         * 点击菜单跳转链接时的事件推送
         *
         * @param eventKey 事件KEY值，设置的跳转URL
         * @return
         */
        public ReceivedMessage prepareMenuViewEvent(String eventKey) {
            this.message.setMessageType(MessageTypeEnum.EVENT);
            this.message.setEventType(EventTypeEnum.VIEW);
            this.message.setEventKey(eventKey);
            return this.message;
        }
    }
}
