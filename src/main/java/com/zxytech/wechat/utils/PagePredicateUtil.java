package com.zxytech.wechat.utils;

/**
 * @author xwxia
 */
final public class PagePredicateUtil {
    private static final int FIRST_PAGE_START = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    public static int getCorrectPage(Integer page) {
        if (null == page || page < FIRST_PAGE_START || page > Short.MAX_VALUE) {
            page = FIRST_PAGE_START;
        }
        return page;
    }

    public static int getCorrectSize(Integer size) {
        if (null == size || size < 1 || size > Byte.MAX_VALUE) {
            size = DEFAULT_PAGE_SIZE;
        }
        return size;
    }
}
