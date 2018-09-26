package com.ancaiyun.controller;

import com.ancaiyun.service.AuthenticateService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticateService authenticateService;
    /**
     * 身份+权限认证
     * @param accessToken token
     * @param requestUrl 请求url
     * @return
     */
    @RequestMapping(value = "v1/authenticate",method = RequestMethod.GET)
    public Result userAuthenticate(String accessToken, String requestUrl, String method){
        return authenticateService.userAuthenticate(accessToken, requestUrl, method);
    }
}
