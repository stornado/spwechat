package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xwxia
 * @date 2018/2/26 14:52
 */
@Repository
public interface ReceivedMessageRepository extends MongoRepository<ReceivedMessage, String>, MessageQuery<ReceivedMessage> {
}
