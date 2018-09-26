package com.ancaiyun.service;


import com.ancaiyun.util.Result;

public interface AuthenticateService {
    Result userAuthenticate(String adminToken, String requestUrl, String method);
}
