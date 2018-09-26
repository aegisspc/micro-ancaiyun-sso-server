package com.ancaiyun.controller;

import com.ancaiyun.service.CurrentUserService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentUserController {

    @Autowired
    private CurrentUserService currentUserService;

    /**
     * 获取当前用户相关信息（user，member，extraData）
     * @param accessToken
     * @return
     */
    @RequestMapping(value = "v1/user-info",method = RequestMethod.GET)
    public Result getUserId(String accessToken){
        return currentUserService.getUserInfo(accessToken);
    }
}
