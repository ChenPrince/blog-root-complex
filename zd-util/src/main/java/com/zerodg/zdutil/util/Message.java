package com.zerodg.zdutil.util;

/**
 * @program: yj_root
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-05-31-09:28
 **/


public class Message {

    /**
     * code
     */
    private String code;

    /**
     * args
     */
    private String[] args;

    public Message(String code) {
        this.code = code;
    }

    public Message(String code, String... args) {
        this.code = code;
        this.args = args;
    }

    @Override
    public String toString() {
        return MessageUtil.getMessage(this.code, this.args);
    }

    /**
     * 取得code
     * @return
     */
    public String getCode() {
        return this.code;
    }
}
