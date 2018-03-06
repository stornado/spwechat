package com.zxytech.wechat.web;

import com.zxytech.wechat.domain.account.Manager;
import com.zxytech.wechat.domain.account.ManagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xwxia
 * @date 2018/3/6 11:00
 */
@Controller
public class SigninController {
    private static final Logger logger = LoggerFactory.getLogger(SigninController.class);

    private static final String REMEMBER_ME = "remember-me";

    private ManagerRepository managerRepository;

    @Autowired
    public SigninController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping("/signin")
    public String signin() {
        return "pages/admin/signin";
    }

    @PostMapping("/signin")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        @RequestParam(value = "remember", required = false) String remember) {
        logger.debug("username = [" + username + "], password = [" + password + "], remember = [" + remember + "]");
        if (null != remember && REMEMBER_ME.equalsIgnoreCase(remember)) {
            logger.debug(remember);
        }
        Manager manager = managerRepository.findByUsernameAndActiveTrue(username);
        if (null != manager) {
            manager.setLastLoginAt(System.currentTimeMillis());
            managerRepository.save(manager);
        }
        return "redirect:/admin/manager";
    }
}
