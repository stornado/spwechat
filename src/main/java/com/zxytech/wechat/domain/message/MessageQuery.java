package com.zxytech.wechat.domain.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author xwxia
 * @date 2018/2/26 14:53
 */
public interface MessageQuery<T extends BaseMessage> {
    Page<T> findByToUserName(String toUserName, Pageable pageable);

    Page<T> findByFromUserName(String fromUserName, Pageable pageable);

    Page<T> findByMessageType(MessageTypeEnum messageTypeEnum, Pageable pageable);

    Page<T> findByToUserNameAndFromUserName(String toUserName, String fromUserName, Pageable pageable);

    Page<T> findByToUserNameAndMessageType(String toUserName, MessageTypeEnum messageTypeEnum, Pageable pageable);

    Page<T> findByFromUserNameAndMessageType(String fromUserName, MessageTypeEnum messageTypeEnum, Pageable pageable);

    Page<T> findByToUserNameAndFromUserNameAndMessageType(String toUserName, String fromUserName, MessageTypeEnum messageTypeEnum, Pageable pageable);
}
