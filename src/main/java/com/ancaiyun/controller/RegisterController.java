package com.ancaiyun.controller;

import com.ancaiyun.service.RegisterService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 校验手机号是否被注册
     * @param phone
     * @return
     */
    @RequestMapping(value = "v1/check-phone",method = RequestMethod.GET)
    public Result checkPhone(String phone){
        return registerService.checkPhone(phone);
    }

    /**
     * 校验用户名是否被注册
     * @param username
     * @return
     */
    @RequestMapping(value = "v1/check-username",method = RequestMethod.GET)
    public Result checkUsername(String username){
        return registerService.checkUsename(username);
    }
    /**
     * 会员信息注册
     * @param params
     * @return
     */
    @RequestMapping(value = "v1/register-member",method = RequestMethod.POST)
    public Result registerMember(@RequestParam Map<String,Object> params){
        return registerService.registerFirst(params);
    }

    /**
     * 企业资质相关信息注册
     * @param params
     * @param categoryIds
     * @return
     */
    @RequestMapping(value = "v1/register-member-extra-data",method = RequestMethod.POST)
    public Result registerMemberExtraData(@RequestParam Map<String,Object> params,String[] categoryIds){
        return registerService.registerSecond(params,categoryIds);
    }

    /**
     * 找回密码
     * @param params
     * @return
     */
    @RequestMapping(value = "v1/retrieve-passwword")
    public Result retrievePassword(@RequestParam Map<String,Object> params){
        return registerService.retrievePassword(params);
    }
}
