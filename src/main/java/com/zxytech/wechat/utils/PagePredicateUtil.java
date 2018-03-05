package com.zxytech.wechat.utils;

/**
 * @author xwxia
 */
final public class PagePredicateUtil {
    public static int getCorrectPage(Integer page) {
        if (null == page || page < 1 || page > Short.MAX_VALUE) {
            page = 1;
        }
        return page;
    }

    public static int getCorrectSize(Integer size) {
        if (null == size || size < 1 || size > Byte.MAX_VALUE) {
            size = 10;
        }
        return size;
    }
}
