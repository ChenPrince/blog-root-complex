package com.zerodg.zdtylin.wxPush;

import com.zjiecode.wxpusher.client.WxPusher;
import com.zjiecode.wxpusher.client.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class WxNoticeUtil {

    private static String wxPusherUid;

    @Value("${customer.notice.wxpusheruid}")
    private String wxPusherUidTemp;

    //注入静态环境变量
    @PostConstruct
    public void init() {
        wxPusherUid = wxPusherUidTemp;
    }

    /**
     * 关注微信公众号【开发者服务】，推送微信通知
     * @param title
     * @param noticeContent
     */
    public static void sendNotice(String title,String noticeContent){
        Result result = WxPusher.send(title, noticeContent,"", wxPusherUid);
        if (result.isSuccess()) {
            log.info("微信消息通知发送成功：" + result.getMsg());
        } else {
            log.error("微信消息通知发送失败：" + result.getMsg());
        }
    }
}
