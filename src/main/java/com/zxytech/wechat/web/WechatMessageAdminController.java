package com.zxytech.wechat.web;

import com.zxytech.wechat.config.SecurityConfig;
import com.zxytech.wechat.domain.WechatMpRepository;
import com.zxytech.wechat.domain.message.*;
import com.zxytech.wechat.utils.PagePredicateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * @author xwxia
 * @date 2018/3/1 19:06
 */
@Controller
@RequestMapping("/admin/wechat/message")
@RolesAllowed({SecurityConfig.ROLE_USER, SecurityConfig.ROLE_ADMIN})
public class WechatMessageAdminController {
    private static final Logger logger = LoggerFactory.getLogger(WechatMessageAdminController.class);

    private ReceivedMessageRepository receivedMessageRepository;
    private ReplyMessageRepository replyMessageRepository;
    private WechatMpRepository wechatMpRepository;

    @Autowired
    public WechatMessageAdminController(ReceivedMessageRepository receivedMessageRepository, ReplyMessageRepository replyMessageRepository, WechatMpRepository wechatMpRepository) {
        this.receivedMessageRepository = receivedMessageRepository;
        this.replyMessageRepository = replyMessageRepository;
        this.wechatMpRepository = wechatMpRepository;
    }

    @GetMapping("/received")
    public String receivedMessagePage(@RequestParam(value = "appId", required = false) String appId,
                                      @RequestParam(value = "fromUserName", required = false) String fromUserName,
                                      @RequestParam(value = "messageType", required = false) MessageTypeEnum messageTypeEnum,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "limit", required = false) Integer limit,
                                      Model model) {
        logger.debug("appId = [" + appId
                + "], fromUserName = [" + fromUserName
                + "], messageTypeEnum = [" + messageTypeEnum
                + "], page = [" + page
                + "], limit = [" + limit + "]");

        page = PagePredicateUtil.getCorrectPage(page);
        limit = PagePredicateUtil.getCorrectSize(limit);

        Sort sortByCreateAtDesc = new Sort(Sort.Direction.DESC, "createAt");
        Pageable pageable = new PageRequest(page, limit, sortByCreateAtDesc);


        MessageQueryBuilder<ReceivedMessage> messageQueryBuilder = new MessageQueryBuilder<>(receivedMessageRepository,
                receivedMessageRepository,
                appId, fromUserName, messageTypeEnum);
        Page<ReceivedMessage> messages = messageQueryBuilder.find(pageable);

        model.addAttribute("wechatMps", wechatMpRepository.findAll(sortByCreateAtDesc));
        model.addAttribute("messages", messages);
        model.addAttribute("messageTypes", ReceivedMessage.MESSAGE_TYPE_ENUMS);

        model.addAttribute("appId", appId);
        model.addAttribute("fromUserName", fromUserName);
        model.addAttribute("messageType", messageTypeEnum);
        return "pages/admin/received_message_list";
    }

    @GetMapping(value = "/received/{messageId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ReceivedMessage receivedMessage(@PathVariable("messageId") String messageId) {
        return receivedMessageRepository.findOne(messageId);
    }

    @GetMapping("/reply")
    public String replyMessagePage(@RequestParam(value = "appId", required = false) String appId,
                                   @RequestParam(value = "toUserName", required = false) String toUserName,
                                   @RequestParam(value = "messageType", required = false) MessageTypeEnum messageTypeEnum,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "limit", required = false) Integer limit,
                                   Model model) {
        logger.debug("appId = [" + appId
                + "], toUserName = [" + toUserName
                + "], messageTypeEnum = [" + messageTypeEnum
                + "], page = [" + page
                + "], limit = [" + limit + "]");

        page = PagePredicateUtil.getCorrectPage(page);
        limit = PagePredicateUtil.getCorrectSize(limit);

        Sort sortByCreateAtDesc = new Sort(Sort.Direction.DESC, "createAt");
        Pageable pageable = new PageRequest(page, limit, sortByCreateAtDesc);

        MessageQueryBuilder<ReplyMessage> messageQueryBuilder = new MessageQueryBuilder<>(replyMessageRepository,
                replyMessageRepository,
                toUserName, appId, messageTypeEnum);
        Page<ReplyMessage> messages = messageQueryBuilder.find(pageable);

        model.addAttribute("wechatMps", wechatMpRepository.findAll(sortByCreateAtDesc));
        model.addAttribute("messages", messages);
        model.addAttribute("messageTypes", ReplyMessage.MESSAGE_TYPE_ENUMS);

        model.addAttribute("toUserName", toUserName);
        model.addAttribute("appId", appId);
        model.addAttribute("messageType", messageTypeEnum);
        return "pages/admin/reply_message_list";
    }

    @GetMapping(value = "/reply/{messageId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ReplyMessage replyMessage(@PathVariable("messageId") String messageId) {
        return replyMessageRepository.findOne(messageId);
    }
}
