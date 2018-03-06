package com.zxytech.wechat.domain.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xwxia
 * @date 2018/3/6 11:10
 */
@Repository
public interface ManagerRepository extends MongoRepository<Manager, String> {
    Manager findByUsernameAndActiveTrue(String username);
}
