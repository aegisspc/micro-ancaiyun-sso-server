package com.ancaiyun.controller;

import com.ancaiyun.service.SmsService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    /**
     * 图形验证,短信验证码
     * @param phone
     * @param
     * @return
     */
    @RequestMapping(value = "v1/captcha",method = RequestMethod.GET)
    public Result getCaptcha(String phone,String Ticket,String Randstr ){
        return smsService.getCaptcha(phone,Ticket,Randstr);
    }

    /**
     * 获取短信验证码
     * @return
     */
    @RequestMapping(value = "v1/send-phone-code",method = RequestMethod.GET)
    public Result getPhoneCode(String captcha){
        return smsService.getPhoneCode(captcha);
    }
}
