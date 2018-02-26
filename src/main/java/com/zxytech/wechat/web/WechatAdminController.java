package com.zxytech.wechat.web;

import com.zxytech.wechat.domain.WechatMp;
import com.zxytech.wechat.domain.WechatMpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xwxia
 * @date 2018/2/26 15:29
 */
@Controller
@RequestMapping("/admin/wechat")
public class WechatAdminController {
    private WechatMpRepository wechatMpRepository;

    @Autowired
    public WechatAdminController(WechatMpRepository wechatMpRepository) {
        this.wechatMpRepository = wechatMpRepository;
    }

    @GetMapping("")
    public String wechatAdminPage(Model model) {
        model.addAttribute("wechats", wechatMpRepository.findAll());
        return "pages/admin/wechat_list";
    }

    @PostMapping("/")
    public String addWechat(@RequestParam("appid") String appId,
                            @RequestParam("appsecret") String appSecret,
                            @RequestParam("encodingaeskey") String encodingAesKey,
                            @RequestParam("token") String token) {
        WechatMp wechatMp = new WechatMp(appId, appSecret);
        wechatMp.setToken(token);
        wechatMp.setEncodingAesKey(encodingAesKey);
        wechatMpRepository.save(wechatMp);

        return "redirect:/admin/wechat";
    }
}
