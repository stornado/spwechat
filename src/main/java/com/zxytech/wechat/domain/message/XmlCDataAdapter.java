package com.zxytech.wechat.domain.message;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author xwxia
 * @date 2018/2/27 16:27
 */
public class XmlCDataAdapter {
    public static class StringFieldAdapter extends XmlAdapter<String, String> {
        @Override
        public String unmarshal(String v) throws Exception {
            return v;
        }

        @Override
        public String marshal(String v) throws Exception {
            return "<![CDATA[" + v + "]]>";
        }
    }

    public static class ByteFieldAdapter extends XmlAdapter<String, Byte> {
        @Override
        public Byte unmarshal(String v) throws Exception {
            return Byte.valueOf(v);
        }

        @Override
        public String marshal(Byte v) throws Exception {
            return "<![CDATA[" + v + "]]>";
        }


    }

    public static class LongFieldAdapter extends XmlAdapter<String, Long> {
        @Override
        public Long unmarshal(String v) throws Exception {
            return Long.valueOf(v);
        }

        @Override
        public String marshal(Long v) throws Exception {
            return "<![CDATA[" + v + "]]>";
        }


    }

    public static class MessageTypeEnumAdapter extends XmlAdapter<String, MessageTypeEnum> {
        @Override
        public MessageTypeEnum unmarshal(String v) throws Exception {
            for (MessageTypeEnum messageTypeEnum : MessageTypeEnum.values()) {
                if (messageTypeEnum.getType().equalsIgnoreCase(v)) {
                    return messageTypeEnum;
                }
            }
            return null;
        }

        @Override
        public String marshal(MessageTypeEnum v) throws Exception {
            return "<![CDATA[" + v.getType() + "]]>";
        }
    }
}
