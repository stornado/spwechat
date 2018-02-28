package com.zxytech.wechat.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author xwxia
 * @date 2018/2/26 15:41
 */
@Service
public class WechatMpService {
    private WechatMpRepository wechatMpRepository;

    @Autowired
    public WechatMpService(WechatMpRepository wechatMpRepository) {
        this.wechatMpRepository = wechatMpRepository;
    }

    @Cacheable(value = "wechat:mp", key = "#wechatId", unless = "#result.appId eq null")
    public WechatMp get(String wechatId) {
        WechatMp wechatMp = wechatMpRepository.findOne(wechatId);
        if (null == wechatMp) {
            wechatMp = new WechatMp();
        }
        return wechatMp;
    }
}
