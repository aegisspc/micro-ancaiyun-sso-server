package com.ancaiyun.serviceImpl;

import com.ancaiyun.service.LogoutService;
import com.ancaiyun.util.Constants;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Transactional
@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired(required = false)
    private HttpServletRequest request;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Result userLogout(){
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try{
            String accessToken = request.getHeader("accessToken");
            if (!redisTemplate.hasKey(accessToken)){
                code = "-3";
                msg = "用户未登录";
            }else {
                redisTemplate.delete(accessToken);
                code = Constants.SUCCESS;
                msg = "成功";
            }
        }catch (Exception e){
            msg = "后台繁忙";
            code = Constants.ERROR;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
