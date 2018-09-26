package com.ancaiyun.controller;

import com.ancaiyun.service.LogoutService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @RequestMapping(value = "v1/logout",method = RequestMethod.GET)
    public Result userLogout(){
        return logoutService.userLogout();
    }

}
