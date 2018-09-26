package com.ancaiyun.controller;

import com.ancaiyun.util.CurrentUserUtil;
import com.ancaiyun.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RandomCodeController {
    @Autowired(required = false)
    private HttpServletResponse response;
    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String codeLength="4";

    private String width="82";

    private String height="38";

    @RequestMapping(value = "v1/create-verify-code",method = RequestMethod.GET)
    public void handleRequest() throws Exception {
        String accessToken = request.getHeader("accessToken");
        String userId = CurrentUserUtil.parseJWT(accessToken);
        // 设置
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        String verifyCode = VerifyCodeUtils.generateVerifyCode(Integer.parseInt(codeLength));
//        request.getSession().setAttribute("validateCode", verifyCode);
        stringRedisTemplate.opsForValue().set(userId,verifyCode.toLowerCase());

        VerifyCodeUtils.outputImage(Integer.parseInt(width), Integer.parseInt(height), response.getOutputStream(), verifyCode);
    }
}
