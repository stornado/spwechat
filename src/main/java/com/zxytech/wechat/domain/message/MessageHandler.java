package com.zxytech.wechat.domain.message;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author xwxia
 * @date 2018/2/26 16:13
 */
final public class MessageHandler {

    /**
     * 根据明文XML生成对应消息
     *
     * @param xmlContent
     * @return
     */
    public static ReceivedMessage convertToMessage(String xmlContent) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(xmlContent);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        String toUserName = getFirstText(root, "ToUserName");
        String fromUserName = getFirstText(root, "FromUserName");
        String createTime = getFirstText(root, "CreateTime");
        String msgType = getFirstText(root, "MsgType");
        Long createAt = Long.valueOf(createTime);
        ReceivedMessage.MessageBuilder messageBuilder = new ReceivedMessage.MessageBuilder(toUserName, fromUserName, createAt);
        ReceivedMessage message = new ReceivedMessage(toUserName, fromUserName, createAt);
        MessageTypeEnum messageType = null;
        for (MessageTypeEnum messageTypeEnum : MessageTypeEnum.values()) {
            if (messageTypeEnum.getType().equalsIgnoreCase(msgType)) {
                messageType = messageTypeEnum;
            }
        }
        if (null != messageType) {
            String msgId = getFirstText(root, "MsgId");
            switch (messageType) {
                case TEXT:
                    String content = getFirstText(root, "Content");

                    message = messageBuilder.prepareTextMessage(content, msgId);
                    break;
                case IMAGE:
                    String picUrl = getFirstText(root, "PicUrl");
                    String picId = getFirstText(root, "MediaId");
                    message = messageBuilder.prepareImageMessage(picUrl, picId, msgId);
                    break;
                case VOICE:
                    String voiceId = getFirstText(root, "MediaId");
                    String format = getFirstText(root, "Format");
                    message = messageBuilder.prepareVoiceMessage(voiceId, format, msgId);
                    break;
                case VIDEO:
                    String videoId = getFirstText(root, "MediaId");
                    String thumbMediaId = getFirstText(root, "ThumbMediaId");
                    message = messageBuilder.prepareVideoMessage(videoId, thumbMediaId, msgId);
                    break;
                case SHORT_VIDEO:
                    String sVideoId = getFirstText(root, "MediaId");
                    String sThumbMediaId = getFirstText(root, "ThumbMediaId");
                    message = messageBuilder.prepareShortVideoMessage(sVideoId, sThumbMediaId, msgId);
                    break;
                case LOCATION:
                    String locationX = getFirstText(root, "Location_X");
                    String locationY = getFirstText(root, "Location_Y");
                    String scale = getFirstText(root, "Scale");
                    String label = getFirstText(root, "Label");
                    Float latitude = Float.valueOf(locationX);
                    Float longitude = Float.valueOf(locationY);
                    message = messageBuilder.prepareLocationMessage(latitude, longitude, Short.valueOf(scale), label, msgId);
                    break;
                case LINK:
                    String title = getFirstText(root, "Title");
                    String description = getFirstText(root, "Description");
                    String url = getFirstText(root, "Url");
                    message = messageBuilder.prepareLinkMessage(title, description, url, msgId);
                    break;
                case EVENT:
                    String event = getFirstText(root, "Event");
                    EventTypeEnum eventType = null;
                    for (EventTypeEnum eventTypeEnum : EventTypeEnum.values()) {
                        if (eventTypeEnum.getType().equalsIgnoreCase(event)) {
                            eventType = eventTypeEnum;
                        }
                    }
                    if (null != eventType) {
                        switch (eventType) {
                            case SUBSCRIBE:
                                message = messageBuilder.prepareSubscribeEvent();

                                String qrsceneId = getFirstText(root, "EventKey");
                                if (null != qrsceneId) {
                                    String ticket = getFirstText(root, "Ticket");
                                    message = messageBuilder.prepareSubscribeEvent(qrsceneId, ticket);
                                }
                                break;
                            case UNSUBSCRIBE:
                                message = messageBuilder.prepareUnsubscribeEvent();
                                break;
                            case SCAN:
                                String sceneId = getFirstText(root, "EventKey");
                                String ticket = getFirstText(root, "Ticket");
                                message = messageBuilder.prepareScanEvent(sceneId, ticket);
                                break;
                            case LOCATION:
                                String lat = getFirstText(root, "Latitude");
                                String lon = getFirstText(root, "Longitude");
                                String precision = getFirstText(root, "Precision");
                                message = messageBuilder.prepareLocationEvent(Float.valueOf(lat), Float.valueOf(lon), Float.valueOf(precision));
                                break;
                            case CLICK:
                                String key = getFirstText(root, "EventKey");
                                message = messageBuilder.prepareMenuClickEvent(key);
                                break;
                            case VIEW:
                                String mUrl = getFirstText(root, "EventKey");
                                message = messageBuilder.prepareMenuViewEvent(mUrl);
                                break;
                            default:
                                break;

                        }
                    }

                default:
                    break;
            }
        }
        return message;
    }

    private static String getFirstText(Element root, String tag) {
        NodeList nodeList = root.getElementsByTagName(tag);
        String result = null;
        if (null != nodeList && nodeList.getLength() > 0) {
            result = nodeList.item(0).getTextContent();
        }
        return result;
    }
}
