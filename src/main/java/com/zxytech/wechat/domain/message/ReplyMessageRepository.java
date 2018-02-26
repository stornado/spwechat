package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xwxia
 * @date 2018/2/26 14:55
 */
@Repository
public interface ReplyMessageRepository extends MongoRepository<ReplyMessage, String>, MessageQuery<ReplyMessage> {

    List<ReplyMessage> findByToUserNameOrderByCreateTimeDesc(String toUserName);

    /**
     * 根据用户查指定时间前的数据
     *
     * @param toUserName
     * @param createTime
     * @return
     */
    List<ReplyMessage> findByToUserNameAndCreateTimeIsLessThanOrderByCreateTimeDesc(String toUserName, Long createTime);

    List<ReplyMessage> findByToUserNameAndFromUserNameOrderByCreateTimeDesc(String toUserName, String fromUserName);

    List<ReplyMessage> findByToUserNameAndFromUserNameAndCreateTimeIsLessThanOrderByCreateTimeDesc(String toUserName, String fromUserName, Long createTime);
}
