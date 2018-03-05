package com.zxytech.wechat.domain.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import static com.zxytech.wechat.utils.StringUtil.isBlank;

/**
 * @author xwxia
 * @date 2018/3/5 14:05
 */
final public class MessageQueryBuilder<T extends BaseMessage> {
    private MessageQuery<T> query;
    private MongoRepository<T, String> repository;

    private String toUserName;
    private String fromUserName;
    private MessageTypeEnum messageTypeEnum;

    public MessageQueryBuilder(MongoRepository<T, String> repository, MessageQuery<T> query, String toUserName, String fromUserName, MessageTypeEnum messageTypeEnum) {
        this.repository = repository;
        this.query = query;
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.messageTypeEnum = messageTypeEnum;
    }

    public Page<T> find(Pageable pageable) {
        if (isBlank(toUserName) && isBlank(fromUserName) && (null == messageTypeEnum)) {
            return repository.findAll(pageable);
        }
        if (!isBlank(toUserName) && isBlank(fromUserName) && (null == messageTypeEnum)) {
            return query.findByToUserName(toUserName, pageable);
        }
        if (isBlank(toUserName) && !isBlank(fromUserName) && (null == messageTypeEnum)) {
            return query.findByFromUserName(fromUserName, pageable);
        }
        if (isBlank(toUserName) && isBlank(fromUserName) && !(null == messageTypeEnum)) {
            return query.findByMessageType(messageTypeEnum, pageable);
        }
        if (!isBlank(toUserName) && !isBlank(fromUserName) && (null == messageTypeEnum)) {
            return query.findByToUserNameAndFromUserName(toUserName, fromUserName, pageable);
        }
        if (!isBlank(toUserName) && isBlank(fromUserName) && !(null == messageTypeEnum)) {
            return query.findByToUserNameAndMessageType(toUserName, messageTypeEnum, pageable);
        }
        if (isBlank(toUserName) && !isBlank(fromUserName) && !(null == messageTypeEnum)) {
            return query.findByFromUserNameAndMessageType(fromUserName, messageTypeEnum, pageable);
        }
        if (!isBlank(toUserName) && !isBlank(fromUserName) && !(null == messageTypeEnum)) {
            return query.findByToUserNameAndFromUserNameAndMessageType(toUserName, fromUserName, messageTypeEnum, pageable);
        }
        return null;
    }
}
