package com.zxytech.wechat.utils;


final public class StringUtil {
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }
}
