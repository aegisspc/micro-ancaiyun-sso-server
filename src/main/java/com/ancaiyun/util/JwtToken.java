package com.ancaiyun.util;

import com.ancaiyun.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtToken {
    /**
     * 公共秘钥保存在服务器
     */
    private static String SECRET = "m13164138097@163.com";
    public static Map<String,Claim> verifyToken(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                                .build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw new RuntimeException("登陆凭证已经过期，请重新登陆");
        }
        return jwt.getClaims();
    }


    /**
     * 生成accessToken
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createAccessToken(User user) throws UnsupportedEncodingException {
        //签发时间
        Date iatDate = new Date();

        //过期时间，30分钟过去
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE,30);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg","HS256");
        map.put("typ","JWT");

        String accessToken = JWT.create()
                .withHeader(map)//头部
                .withClaim("userName",user.getUsername())
                .withClaim("userId",user.getId())
                .withIssuedAt(iatDate)//设置签发时间
                .withExpiresAt(expiresDate)//设置过期时间，过期时间要大于签发时间
                .sign(Algorithm.HMAC256(SECRET));
        return accessToken;
    }
}
