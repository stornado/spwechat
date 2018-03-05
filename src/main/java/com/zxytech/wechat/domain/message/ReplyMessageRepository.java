package com.zxytech.wechat.domain.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xwxia
 * @date 2018/2/26 14:55
 */
@Repository
public interface ReplyMessageRepository extends MongoRepository<ReplyMessage, String>, MessageQuery<ReplyMessage> {
}
