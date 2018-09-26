package com.ancaiyun.serviceImpl;

import com.ancaiyun.entity.User;
import com.ancaiyun.service.AuthenticateService;
import com.ancaiyun.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Transactional
@Service
public class AuthenticateServiceImpl implements AuthenticateService{
    @Resource
    private RedisTemplate<String, Object> redisTemplate;



    @Override
    public Result userAuthenticate(String accessToken, String requestUrl, String method) {
        Result result = new Result();
        String code = "-1";
        String msg = "初始化";
        if(StringUtils.isBlank(accessToken)){
            code = "-2";
            msg = "非法请求";
        }else{
            Boolean flag = redisTemplate.hasKey(accessToken);
            if(!flag){
                code = "-3";
                msg = "已经过期";
            }else{
//                User user = (User) redisTemplate.opsForValue().get(accessToken);
                code = "0";
                msg = "认证成功";
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("requestUrl",requestUrl);
                result.setData(map);

            }
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
