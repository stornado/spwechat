package com.zxytech.wechat.domain.message;

import java.util.List;

/**
 * @author xwxia
 * @date 2018/2/26 14:53
 */
public interface MessageQuery<T extends MessageBase> {
    /**
     * 根据消息类型查询消息列表
     *
     * @param messageType
     * @return
     */
    List<T> findByMessageTypeOrderByCreateTimeDesc(MessageTypeEnum messageType);
}
