package com.ancaiyun.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public class CurrentUserUtil {

    /**
     * 解析token,获取userId
     * @param accessToken
     * @return
     */
    public static String parseJWT(String accessToken) {
        DecodedJWT jwt = JWT.decode(accessToken);
        Map<String,Claim> claims = jwt.getClaims();
        return claims.get("userId").asString();
    }
}
