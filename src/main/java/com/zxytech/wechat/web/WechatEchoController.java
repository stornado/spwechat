package com.zxytech.wechat.web;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.zxytech.wechat.domain.WechatMp;
import com.zxytech.wechat.domain.WechatMpService;
import com.zxytech.wechat.domain.message.MessageHandler;
import com.zxytech.wechat.domain.message.ReceivedMessage;
import com.zxytech.wechat.domain.message.ReceivedMessageRepository;
import com.zxytech.wechat.domain.message.ReplyMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author xwxia
 * @date 2018/2/26 15:21
 */
@RestController
public class WechatEchoController {
    private static final Logger logger = LoggerFactory.getLogger(WechatEchoController.class);

    /**
     * 三次重试后，依旧没有及时回复任何内容，系统自动在粉丝会话界面出现错误提示“该公众号暂时无法提供服务，请稍后再试”。
     * 如果回复success，微信后台可以确定开发者收到了粉丝消息，没有任何异常提示。因此请大家注意回复success的问题。
     */
    private static final String NO_ECHO_CONTENT = "success";

    private WechatMpService wechatMpService;
    private ReceivedMessageRepository receivedMessageRepository;
    private ReplyMessageRepository replyMessageRepository;

    @Autowired
    public WechatEchoController(WechatMpService wechatMpService,
                                ReceivedMessageRepository receivedMessageRepository,
                                ReplyMessageRepository replyMessageRepository) {
        this.wechatMpService = wechatMpService;
        this.receivedMessageRepository = receivedMessageRepository;
        this.replyMessageRepository = replyMessageRepository;
    }


    @GetMapping("/mp/{wechatId}")
    public String authorize(@PathVariable("wechatId") String wechatId,
                            @RequestParam("signature") String signature,
                            @RequestParam("timestamp") String timestamp,
                            @RequestParam("nonce") String nonce,
                            @RequestParam("echostr") String echostr) throws AesException {
        logger.debug("wechatId = [" + wechatId + "], signature = [" + signature + "], timestamp = [" + timestamp + "], nonce = [" + nonce + "], echostr = [" + echostr + "]");
        WechatMp wechatMp = wechatMpService.get(wechatId);
        logger.debug(wechatMp.toString());
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(wechatMp.getToken(), wechatMp.getEncodingAesKey(), wechatMp.getAppId());
        return wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echostr);
    }

    @PostMapping(value = "/mp/{wechatId}", consumes = MediaType.TEXT_XML_VALUE)
    public String echo(@PathVariable("wechatId") String wechatId,
                       @RequestParam("signature") String signature,
                       @RequestParam("encrypt_type") String encryptType,
                       @RequestParam("msg_signature") String msgSignature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestBody String postData) throws AesException {
        logger.debug("wechatId = [" + wechatId + "], signature = [" + signature + "], encryptType = [" + encryptType + "], msgSignature = [" + msgSignature + "], timestamp = [" + timestamp + "], nonce = [" + nonce + "], postData = [" + postData + "]");
        WechatMp wechatMp = wechatMpService.get(wechatId);
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(wechatMp.getToken(), wechatMp.getEncodingAesKey(), wechatMp.getAppId());
        String xmlContent = wxBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, postData);
        try {
            ReceivedMessage message = MessageHandler.convertToMessage(xmlContent);
            receivedMessageRepository.save(message);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return NO_ECHO_CONTENT;
    }


    @ExceptionHandler(AesException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleWechatMessageCryptException() {
        return "Something unexpected happened";
    }
}
