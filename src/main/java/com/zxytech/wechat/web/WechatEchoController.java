package com.zxytech.wechat.web;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.zxytech.wechat.domain.WechatMp;
import com.zxytech.wechat.domain.WechatMpService;
import com.zxytech.wechat.domain.message.*;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

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
        logger.debug("wechatId = [" + wechatId
                + "], signature = [" + signature
                + "], timestamp = [" + timestamp
                + "], nonce = [" + nonce
                + "], echostr = [" + echostr + "]");

        WechatMp wechatMp = wechatMpService.get(wechatId);
        logger.debug(wechatMp.toString());
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(wechatMp.getToken(),
                wechatMp.getEncodingAesKey(),
                wechatMp.getAppId());
        return wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echostr);
    }

    @PostMapping(value = "/mp/{wechatId}",
            consumes = MediaType.TEXT_XML_VALUE,
            produces = MediaType.TEXT_XML_VALUE)
    public String echo(@PathVariable("wechatId") String wechatId,
                       @RequestParam("signature") String signature,
                       @RequestParam("encrypt_type") String encryptType,
                       @RequestParam("msg_signature") String msgSignature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestBody EncryptMessage encryptMessage) throws AesException, JAXBException {
        logger.debug("wechatId = [" + wechatId
                + "], signature = [" + signature
                + "], encryptType = [" + encryptType
                + "], msgSignature = [" + msgSignature
                + "], timestamp = [" + timestamp
                + "], nonce = [" + nonce
                + "], encryptMessage = [" + encryptMessage + "]");


        WechatMp wechatMp = wechatMpService.get(wechatId);
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(wechatMp.getToken(),
                wechatMp.getEncodingAesKey(),
                wechatMp.getAppId());
        String xmlContent = wxBizMsgCrypt.decrypt(encryptMessage.getEncrypt());

        ReplyMessage.MessageBuilder messageBuilder = new ReplyMessage.MessageBuilder(encryptMessage.getToUserName(),
                System.currentTimeMillis());
        ReplyMessage replyMessage;
        try {
            ReceivedMessage receivedMessage = MessageHandler.convertToMessage(xmlContent);
            receivedMessage = receivedMessageRepository.save(receivedMessage);
            logger.debug(receivedMessage.toString());
            messageBuilder = new ReplyMessage.MessageBuilder(receivedMessage.getFromUserName(),
                    wechatMp.getAppId(),
                    System.currentTimeMillis());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        List<NewsArticle> articles = new ArrayList<>();
        articles.add(new NewsArticle("title1", "desc11", "pic111", "url1111"));
        articles.add(new NewsArticle("标题2", "desc22", "pic222", "url2222"));
        articles.add(new NewsArticle("title3", "desc33", "pic333", "url3333"));
        replyMessage = messageBuilder.prepareNewsMessage(articles);
        replyMessage = replyMessageRepository.save(replyMessage);

        String xml = objectToXml(replyMessage);
        logger.debug(xml);
        String encrypt = wxBizMsgCrypt.encrypt(wxBizMsgCrypt.getRandomStr(), xml);
        Long createAt = Long.valueOf(String.valueOf(System.currentTimeMillis()));
        String randomStr = wxBizMsgCrypt.getRandomStr();
        String mSignature = SHA1.getSHA1(wechatMp.getToken(), String.valueOf(createAt), randomStr, encrypt);
        EncryptMessage reply = new EncryptMessage(encrypt, mSignature, createAt, randomStr);
        logger.debug(reply.toString());
        return objectToXml(reply);
    }


    @ExceptionHandler(value = {AesException.class, JAXBException.class})
    @ResponseStatus(value = HttpStatus.OK, reason = "Something unexpected happened")
    public String handleWechatException() {
        return NO_ECHO_CONTENT;
    }

    private static String objectToXml(Object object) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
        // jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // 去掉生成xml的默认报文头
        // jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        // 转换所有的适配字符，包括xml实体字符&lt;和&gt;，xml实体字符在好多处理xml
        jaxbMarshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
                (CharacterEscapeHandler) (ch, start, length, isAttVal, writer) -> writer.write(ch, start, length));
        jaxbMarshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }
}
