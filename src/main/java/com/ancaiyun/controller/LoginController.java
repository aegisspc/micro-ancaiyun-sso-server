package com.ancaiyun.controller;

import com.ancaiyun.service.LoginService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    /**
     * 登陆
     * @param username 登陆名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "v1/login",method = RequestMethod.GET)
    public Result userLogin(String username, String password,String loginPostion){
        return loginService.userLogin(username,password,loginPostion);
    }

}
