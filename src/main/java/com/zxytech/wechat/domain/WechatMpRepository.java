package com.zxytech.wechat.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author xwxia
 * @date 2018/2/26 15:40
 */
public interface WechatMpRepository extends MongoRepository<WechatMp, String> {
}
