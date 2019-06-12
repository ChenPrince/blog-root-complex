package com.zerodg.zdutil.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: yj_root
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-05-31-09:29
 **/

public class MessageUtil {
    private static MessageSource messageSource;

    /**
     * 注入
     */
    private static void init() {
        if (messageSource == null) {
            synchronized (MessageUtil.class) {
                ApplicationContext applicationContextFactory = new ClassPathXmlApplicationContext("classpath:message_source.xml");
                messageSource = (MessageSource) applicationContextFactory.getBean("messageSource");
            }
        }
    }

    /**
     * 获取消息
     *
     * @param errorCode messageId
     * @param param     参数
     * @return
     */
    public static String getMessage(String errorCode, String... param) {
        init();
        try {
            return messageSource.getMessage(errorCode, param, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return errorCode;
        }
    }

    /**
     * 获取消息
     *
     * @param errorCode messageId
     * @return
     */
    public static String getMessage(String errorCode) {
        init();
        try {
            return messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return errorCode;
        }
    }
}

