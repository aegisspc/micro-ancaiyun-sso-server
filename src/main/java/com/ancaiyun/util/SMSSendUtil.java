package com.ancaiyun.util;

import com.ancaiyun.sms.AppConfig;
import com.ancaiyun.sms.ConfigLoader;
import com.ancaiyun.sms.MESSAGEXsend;
import org.apache.commons.lang.StringUtils;

public class SMSSendUtil {
    // 发送验证码
    public static void send(String phone, String code, String time) {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code) || StringUtils.isBlank(time)) {
            return;
        }

        AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
        MESSAGEXsend submail = new MESSAGEXsend(config);
        submail.addTo(phone);
        submail.setProject("Y8YUd3");
        submail.addVar("code", code);
        submail.addVar("time", time);
        submail.xsend();
    }
}
