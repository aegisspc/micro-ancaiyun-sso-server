package com.ancaiyun.serviceImpl;

import com.ancaiyun.entity.Member;
import com.ancaiyun.service.CurrentUserService;
import com.ancaiyun.service.SmsService;
import com.ancaiyun.util.CommonUtil;
import com.ancaiyun.util.Constants;
import com.ancaiyun.util.Result;
import com.ancaiyun.util.SMSSendUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SmsServiceImpl implements SmsService {

    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired(required = false)
    private HttpServletResponse response;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getCaptcha(String phone, String Ticket, String Randstr) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            //获取手机号
            if (StringUtils.isBlank(phone)) {
                String accessToken = request.getHeader("accessToken");
                Result userResult = currentUserService.getUserInfo(accessToken);
                if ("0".equals(userResult.getCode())) {
                    Map<String, Object> maps = (Map<String, Object>) userResult.getData();
                    Member member = (Member) maps.get("member");
                    phone = member.getMobile();
                } else {
                    code = "-6";
                    msg = "未登录";
                    result.setCode(code);
                    result.setMsg(msg);
                    return result;
                }
            }

            //验证码发送
            if (StringUtils.isNotBlank(Ticket) && StringUtils.isNotBlank(Randstr)) {
                String userIp = CommonUtil.getIpAddr(request);
                String codeResult = CommonUtil.sendGet("https://ssl.captcha.qq.com/ticket/verify",
                        "aid=2094236595&AppSecretKey=0okRBxX1AVt4AGpo-Qb_R8Q**&Ticket=" + Ticket + "&Randstr=" + Randstr + "&UserIP=" + userIp);
                System.out.println(codeResult.toString());
                boolean res = codeResult.contains("OK");
                if (res == true) {
                    Random random = new Random();
                    String phoneCode = "";
                    for (int i = 0; i < 6; i++) {
                        int tempCode = random.nextInt(10);
                        phoneCode += tempCode;
                    }
                    if (phoneCode.length() == 6) {
                        SMSSendUtil.send(phone, phoneCode, "30");
                        HttpSession session = request.getSession();
                        session.setAttribute("phoneCode", phoneCode);
                        session.setAttribute("phone", phone);
                        stringRedisTemplate.opsForValue().set(phone, phoneCode, 5, TimeUnit.MINUTES);
                        session.setMaxInactiveInterval(30 * 60);
//                        result.setData(phoneCode);
                        code = Constants.SUCCESS;
                        msg = "成功";
                    } else {
                        code = "-3";
                        msg = "发送验证码失败";
                    }
                } else {
                    code = "-4";
                    msg = "人机验证码不能不正确";
                }
            } else {
                code = "-5";
                msg = "人机验证码为空";
            }
        } catch (Exception e) {
            msg = "后台繁忙";
            code = Constants.ERROR;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    @Override
    public Result getPhoneCode(String captcha) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            if (StringUtils.isNotBlank(captcha)){
                String accessToken = request.getHeader("accessToken");
                Result userResult = currentUserService.getUserInfo(accessToken);
                if ("0".equals(userResult.getCode())) {
                    Map<String, Object> maps = (Map<String, Object>) userResult.getData();
                    Member member = (Member) maps.get("member");
                    String phone = member.getMobile();
                    String userId = member.getUserId();
                    String verifyCode = stringRedisTemplate.opsForValue().get(userId);
                    if (verifyCode.equals(captcha.toLowerCase())){
                        Random random = new Random();
                        String phoneCode = "";
                        for (int i = 0; i < 6; i++) {
                            int tempCode = random.nextInt(10);
                            phoneCode += tempCode;
                        }
                        if (phoneCode.length() == 6) {
                            SMSSendUtil.send(phone, phoneCode, "30");
                            stringRedisTemplate.opsForValue().set(phone, phoneCode, 5, TimeUnit.MINUTES);
//                            result.setData(phoneCode);
                            code = Constants.SUCCESS;
                            msg = "成功";
                        } else {
                            code = "-3";
                            msg = "发送验证码失败";
                        }
                        result.setCode(code);
                        result.setMsg(msg);
                        return result;
                    }else {
                        code = "-4";
                        msg = "验证码不正确";
                        result.setCode(code);
                        result.setMsg(msg);
                        return result;
                    }
                }else {
                    code = "-5";
                    msg = "用户未登录";
                }
            }else {
                code = "-6";
                msg = "验证码不能为空";
            }
        } catch (Exception e) {
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
