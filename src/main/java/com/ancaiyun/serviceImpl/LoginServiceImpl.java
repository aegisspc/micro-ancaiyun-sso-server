package com.ancaiyun.serviceImpl;

import com.ancaiyun.entity.Member;
import com.ancaiyun.entity.User;
import com.ancaiyun.mapper.*;
import com.ancaiyun.service.LoginService;
import com.ancaiyun.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberExtraDataMapper memberExtraDataMapper;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private UserUserRoleMapper userUserRoleMapper;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;

    private static final String ADMINSTATUS = "1";

    @Override
    public Result userLogin(String username, String userPassword, String loginPostion) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            if (StringUtils.isBlank(username)) {
                code = "-3";
                msg = "用户名不能为空";
            } else if (StringUtils.isBlank(userPassword)) {
                code = "-4";
                msg = "密码不能为空";
            } else {
                User user = userMapper.selectByUserName(username);
                if (null == user) {
                    code = "-5";
                    msg = "用户名不存在";
                } else if (!userPassword.equals(user.getPassword())) {
                    code = "-6";
                    msg = "密码输入错误";
                } else if (null != user.getStatus() && ADMINSTATUS.equals(user.getStatus())) {
                    code = "-7";
                    msg = "账户已被禁用";
                } else {
                    String accessToken = JwtToken.createAccessToken(user);
//                    accessToken = accessToken+"_"+user.getId();
                    redisTemplate.opsForValue().set(accessToken, user, 30, TimeUnit.DAYS);

                    user.setLastLoginIp(CommonUtil.getIpAddr(request));
                    user.setLastLoginTime(new Date());
                    userMapper.updateByPrimaryKeySelective(user);
                    ModelMap modelMap = new ModelMap();
                    modelMap.addAttribute("accessToken", accessToken);

                    Member member = memberMapper.selectByUserName(username);
                    if (StringUtils.isNotBlank(user.getParentId())){
                        member = memberMapper.selectByUserId(user.getParentId());
                    }
                    modelMap.addAttribute("memberId",member.getId());
                    result.setData(modelMap);
                    //判断是否登陆金融板块
                    if (StringUtils.isNotBlank(loginPostion)) {
                        //判断是否是主账号
                        if(StringUtils.isNotBlank(user.getParentId())){
                            result.setCode("-5");
                            result.setMsg("请更换主账号登陆");
                            return result;
                        }
                        modelMap.addAttribute("loginPostion", "financial");
                        //判断用户是否填写企业相关资质信息
                        if (StringUtils.isBlank(member.getExtraDataId())) {
                            code = "-3";
                            msg = "跳转企业资质信息填写";
                            result.setCode(code);
                            result.setMsg(msg);
                            return result;
                        }
                        String memberNumber = member.getMemberNumber();
                        Result userResult = restTemplate.getForEntity("http://micro-ancaiyun-zuul/finance/v1/bank-card-infos?memberNumber=" + memberNumber + "&method=GET", Result.class).getBody();
                        //判断用户是否绑定银行卡信息
                        if ("0".equals(userResult.getCode())) {
                            //如果用户没有银行卡信息，进入绑定银行卡信息
                            if (null == userResult.getData()) {
                                code = "-4";
                                msg = "跳转添加银行卡信息";

//                                String tradeStatus = member.getTradeStatus();
//                                //赋予用户公共权限
//                                UserRole commonRole = userRoleMapper.selectByRoleName("common");
//                                UserUserRole common = new UserUserRole();
//                                common.setId(UUID.randomUUID().toString().replace("-",""));
//                                common.setCreateTime(new Date());
//                                common.setDelFlag("0");
//                                common.setUserId(user.getId());
//                                common.setRoleId(commonRole.getId());
//                                userUserRoleMapper.insertUserUserRole(common);
//                                if ("1".equals(tradeStatus)){
//                                    //赋予供应商角色
//                                    UserRole sellerRole = userRoleMapper.selectByRoleName("buyer");
//                                    UserUserRole seller = new UserUserRole();
//                                    seller.setId(UUID.randomUUID().toString().replace("-",""));
//                                    seller.setCreateTime(new Date());
//                                    seller.setDelFlag("0");
//                                    seller.setUserId(user.getId());
//                                    seller.setRoleId(sellerRole.getId());
//                                    userUserRoleMapper.insertUserUserRole(seller);
//
//                                    user.setUserRole("SELLER");
//                                }
//                                if ("2".equals(tradeStatus)){
//                                    //赋予采购商角色
//                                    UserRole buyerRole = userRoleMapper.selectByRoleName("buyer");
//                                    UserUserRole buyer = new UserUserRole();
//                                    buyer.setId(UUID.randomUUID().toString().replace("-",""));
//                                    buyer.setCreateTime(new Date());
//                                    buyer.setDelFlag("0");
//                                    buyer.setUserId(user.getId());
//                                    buyer.setRoleId(buyerRole.getId());
//                                    userUserRoleMapper.insertUserUserRole(buyer);
//
//                                    user.setUserRole("BUYER");
//                                }
//                                if ("3".equals(tradeStatus)){
//                                    //赋予供应商角色
//                                    UserRole sellerRole = userRoleMapper.selectByRoleName("buyer");
//                                    UserUserRole seller = new UserUserRole();
//                                    seller.setId(UUID.randomUUID().toString().replace("-",""));
//                                    seller.setCreateTime(new Date());
//                                    seller.setDelFlag("0");
//                                    seller.setUserId(user.getId());
//                                    seller.setRoleId(sellerRole.getId());
//                                    userUserRoleMapper.insertUserUserRole(seller);
//
//                                    //赋予采购商角色
//                                    UserRole buyerRole = userRoleMapper.selectByRoleName("buyer");
//                                    UserUserRole buyer = new UserUserRole();
//                                    buyer.setId(UUID.randomUUID().toString().replace("-",""));
//                                    buyer.setCreateTime(new Date());
//                                    buyer.setDelFlag("0");
//                                    buyer.setUserId(user.getId());
//                                    buyer.setRoleId(buyerRole.getId());
//                                    userUserRoleMapper.insertUserUserRole(buyer);
//
//                                    user.setUserRole("SELLER_BUYER");
//                                }
                                result.setCode(code);
                                result.setMsg(msg);
                                return result;
                            }else {
                                Cookie cookie = new Cookie("accessToken", accessToken);
                                cookie.setMaxAge(30 * 24 * 60 * 60);
                                cookie.setPath("/");
                                response.addCookie(cookie);

                                code = Constants.SUCCESS;
                                msg = "成功";
                            }
                        } else {
                            code = "-5";
                            msg = "连接供应链金融服务失败";
                        }
                    } else {
                        //登陆安采云
                        Cookie cookie = new Cookie("accessToken", accessToken);
                        cookie.setMaxAge(30 * 24 * 60 * 60);
                        cookie.setPath("/");
                        response.addCookie(cookie);

                        result.setData(modelMap);
                        code = Constants.SUCCESS;
                        msg = "成功";
                    }
                }
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
