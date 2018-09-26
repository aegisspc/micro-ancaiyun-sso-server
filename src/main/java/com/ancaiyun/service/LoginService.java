package com.ancaiyun.service;

import com.ancaiyun.util.Result;


public interface LoginService {

    /**
     * 用户登陆
     * @param username
     * @param userPassword
     * @return
     */
    Result userLogin(String username, String userPassword,String loginPostion);

}
