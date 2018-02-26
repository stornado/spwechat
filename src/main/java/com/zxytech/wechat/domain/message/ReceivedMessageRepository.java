package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xwxia
 * @date 2018/2/26 14:52
 */
@Repository
public interface ReceivedMessageRepository extends MongoRepository<ReceivedMessage, String>, MessageQuery<ReceivedMessage> {

    List<ReceivedMessage> findByEventTypeOrderByCreateTimeDesc(EventTypeEnum eventType);

    List<ReceivedMessage> findByFromUserNameOrderByCreateTimeDesc(String fromUserName);

    /**
     * 根据用户查指定时间前的数据
     *
     * @param fromUserName
     * @param createTime
     * @return
     */
    List<ReceivedMessage> findByFromUserNameAndCreateTimeIsLessThanOrderByCreateTimeDesc(String fromUserName, Long createTime);

    List<ReceivedMessage> findByFromUserNameAndToUserNameOrderByCreateTimeDesc(String toUserName, String fromUserName);

    List<ReceivedMessage> findByFromUserNameAndToUserNameAndCreateTimeIsLessThanOrderByCreateTimeDesc(String toUserName, String fromUserName, Long createTime);
}
