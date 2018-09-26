package com.ancaiyun.service;


import com.ancaiyun.util.Result;

import java.util.Map;

public interface RegisterService {

    /**
     * 校验手机号是否已被注册
     * @param phone
     * @return
     */
    Result checkPhone(String phone);

    /**
     * 校验用户名是否被注册
     * @param username
     * @return
     */
    Result checkUsename(String username);

    /**
     * 用户注册信息
     * @param params
     * @return
     */
    Result registerFirst(Map<String,Object> params);

    /**
     * 企业信息注册
     * @param params
     * @return
     */
    Result registerSecond(Map<String,Object> params,String[] catagorySecondIds);

    /**
     * 找回密码
     * @param params
     * @return
     */
    Result retrievePassword(Map<String,Object> params);
}
