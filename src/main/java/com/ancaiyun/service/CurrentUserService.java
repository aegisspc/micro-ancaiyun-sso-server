package com.ancaiyun.service;

import com.ancaiyun.util.Result;

public interface CurrentUserService {

    /**
     * 获取当前用户相关信息
     * @param accessToken
     * @return
     */
    Result getUserInfo(String accessToken);

}
