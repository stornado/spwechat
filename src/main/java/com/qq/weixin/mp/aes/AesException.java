package com.qq.weixin.mp.aes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class AesException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(AesException.class);

    public final static int OK = 0;
    public final static int VALIDATE_SIGNATURE_ERROR = -40001;
    public final static int PARSE_XML_ERROR = -40002;
    public final static int COMPUTE_SIGNATURE_ERROR = -40003;
    public final static int ILLEGAL_AES_KEY = -40004;
    public final static int VALIDATE_APPID_ERROR = -40005;
    public final static int ENCRYPT_AES_ERROR = -40006;
    public final static int DECRYPT_AES_ERROR = -40007;
    public final static int ILLEGAL_BUFFER = -40008;
    public final static int ENCODE_BASE64_ERROR = -40009;
    public final static int DECODE_BASE64_ERROR = -40010;
    public final static int GEN_RETURN_XML_ERROR = -40011;

    private int code;

    private static String getMessage(int code) {
        logger.debug(String.valueOf(code));
        switch (code) {
            case VALIDATE_SIGNATURE_ERROR:
                return "签名验证错误";
            case PARSE_XML_ERROR:
                return "xml解析失败";
            case COMPUTE_SIGNATURE_ERROR:
                return "sha加密生成签名失败";
            case ILLEGAL_AES_KEY:
                return "SymmetricKey非法";
            case VALIDATE_APPID_ERROR:
                return "appid校验失败";
            case ENCRYPT_AES_ERROR:
                return "aes加密失败";
            case DECRYPT_AES_ERROR:
                return "aes解密失败";
            case ILLEGAL_BUFFER:
                return "解密后得到的buffer非法";
            case ENCODE_BASE64_ERROR:
                return "base64加密错误";
            case DECODE_BASE64_ERROR:
                return "base64解密错误";
            case GEN_RETURN_XML_ERROR:
                return "xml生成失败";
            default:
                // cannot be
                return null;
        }
    }

    public int getCode() {
        return code;
    }

    AesException(int code) {
        super(getMessage(code));
        this.code = code;
    }

}
