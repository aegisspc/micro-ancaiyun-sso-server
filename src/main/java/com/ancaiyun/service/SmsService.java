package com.ancaiyun.service;

import com.ancaiyun.util.Result;

public interface SmsService {

    /**
     * 获取图形验证码，短信验证码
     * @param phone
     * @param
     * @return
     */
    Result getCaptcha(String phone, String Ticket,String Randstr );

    /**
     * 获取短信验证码
     * @return
     */
    Result getPhoneCode(String captcha);
}
