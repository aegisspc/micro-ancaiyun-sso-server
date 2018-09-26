package com.ancaiyun.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.ancaiyun.entity.*;
import com.ancaiyun.mapper.*;
import com.ancaiyun.service.CurrentUserService;
import com.ancaiyun.service.LoginService;
import com.ancaiyun.service.RegisterService;
import com.ancaiyun.util.CommonUtil;
import com.ancaiyun.util.Constants;
import com.ancaiyun.util.PatternUtil;
import com.ancaiyun.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberExtraDataMapper memberExtraDataMapper;
    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired
    private LoginService loginService;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired(required = false)
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result checkPhone(String phone) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            Member member = memberMapper.selectByMobile(phone);
            if (null == member) {
                code = Constants.SUCCESS;
                msg = "成功";
            } else {
                code = "-3";
                msg = "手机号已被注册";
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
    public Result checkUsename(String username) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            User user = userMapper.selectByUserName(username);
            if (null == user) {
                code = Constants.SUCCESS;
                msg = "成功";
            } else {
                code = "-3";
                msg = "用户名已被注册";
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
    public Result registerFirst(Map<String, Object> params) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            String tradeStatus = (String) params.get("tradeStatus");
            String username = (String) params.get("username");
            String password = (String) params.get("password");
            String confirmPassword = (String) params.get("confirmPassword");
            String mobile = (String) params.get("mobile");
            String mobileCode = StringUtils.trim((String) params.get("mobileCode"));
            String phoneCode = stringRedisTemplate.opsForValue().get(mobile);
            if (StringUtils.isBlank(username)) {
                code = "-3";
                msg = "用户名不能为空";
            } else if (!PatternUtil.patternString(username, "username")) {
                code = "-4";
                msg = "用户名格式不正确";
            } else if (StringUtils.isBlank(password)) {
                code = "-5";
                msg = "密码不能为空";
            } else if(StringUtils.isBlank(mobile)){
                code = "-6";
                msg = "手机号不能为空";
            } else if (StringUtils.isBlank(confirmPassword) || !password.equals(confirmPassword)) {
                code = "-7";
                msg = "两次密码输入不一致";
            } else if (StringUtils.isBlank(tradeStatus)) {
                code = "-8";
                msg = "请选择身份";
            } else if (StringUtils.isBlank(phoneCode)) {
                code = "-9";
                msg = "手机号与验证手机不符";
            } else if (!mobileCode.equals(phoneCode)) {
                code = "-10";
                msg = "验证码不正确";
            } else {
                username = StringUtils.trim(CommonUtil.filterHTML(username));
                password = StringUtils.trim(password);
                String checkUsername = username.toLowerCase();
                User checkUser = userMapper.selectByUserName(checkUsername);
                if (null != checkUser) {
                    code = "-11";
                    msg = "用户名已经存在";
                } else {
                    User user = new User();
                    user.setId(UUID.randomUUID().toString().replace("-", ""));
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setCreateTime(new Date());
                    user.setDelFlag("0");
                    user.setRegisterIp(CommonUtil.getIpAddr(request));
                    user.setStatus("0");
                    userMapper.insertSelective(user);

                    Member member = new Member();
                    member.setId(UUID.randomUUID().toString().replace("-", ""));
                    member.setCreateTime(new Date());
                    member.setMobile(mobile);
                    member.setTradeStatus(tradeStatus);
                    member.setDelFlag("0");
                    member.setUserId(user.getId());
                    //生成会员编号
                    Result userResult = restTemplate.getForEntity("http://micro-ancaiyun-zuul/finance/v1/create-member-number?tradeStatus=" + tradeStatus + "&method=GET", Result.class).getBody();
                    if (null != userResult) {
                        if ("0".equals(userResult.getCode())) {
                            String memberNumber = (String) userResult.getData();
                            member.setMemberNumber(memberNumber);
                            memberMapper.insertSelective(member);
                            Result loginResult = loginService.userLogin(username, password, "financial");
                            System.out.println(loginResult.toString());
                            result.setData(loginResult.getData());
                            code = Constants.SUCCESS;
                            msg = "成功";
                        } else {
                            code = "-12";
                            msg = "赋予会员编号失败";
                        }
                    } else {
                        code = "-13";
                        msg = "网络连接失败";
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

    @Override
    public Result registerSecond(Map<String, Object> params, String[] catagorySecondIds) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            String accessToken = request.getHeader("accessToken");
            Result userResult = currentUserService.getUserInfo(accessToken);
            if ("0".equals(userResult.getCode())) {
                Map<String, Object> maps = (Map<String, Object>) userResult.getData();
//                Map<String, Object> memberMap = (Map<String, Object>) maps.get("member");
//                Member member = JSONObject.parseObject(JSONObject.toJSONString(memberMap), Member.class);
                Member member = (Member) maps.get("member");
                if (StringUtils.isBlank((String) params.get("companyName"))) {
                    code = "-4";
                    msg = "公司名称不能为空";
                } else if (StringUtils.isBlank((String) params.get("companyLogoId"))) {
                    code = "-5";
                    msg = "请上传公司logo";
                } else if (StringUtils.isBlank((String) params.get("companyEmployeeNumber"))) {
                    code = "-6";
                    msg = "请选择公司人数";
                } else if (StringUtils.isBlank((String) params.get("companyRegisteredCapital"))) {
                    code = "-7";
                    msg = "请输入注册资金";
                } else if (StringUtils.isBlank((String) params.get("companyBusinessScope"))) {
                    code = "-8";
                    msg = "请选择经营范围";
                } else if (StringUtils.isBlank((String) params.get("companyEnterpriseNature"))) {
                    code = "-9";
                    msg = "请选择企业类型";
                } else if (StringUtils.isBlank((String) params.get("companyTaxpayerType"))) {
                    code = "-10";
                    msg = "请选择纳税人类型";
                } else if (StringUtils.isBlank((String) params.get("companyAnnualTurnover"))) {
                    code = "-11";
                    msg = "请选择年营业额";
                } else if (StringUtils.isBlank((String) params.get("companyLegalPersonName"))) {
                    code = "-12";
                    msg = "法人姓名不能为空";
                } else if (StringUtils.isBlank((String) params.get("companyUnifiedSocialCreditCode"))) {
                    code = "-13";
                    msg = "统一社会信用代码不能为空";
                } else if (StringUtils.isBlank((String) params.get("registeredAddress"))) {
                    code = "-14";
                    msg = "注册地址不能为空";
                } else if (StringUtils.isBlank((String) params.get("registeredAddress"))) {
                    code = "-15";
                    msg = "注册地址";
                } else if (StringUtils.isBlank((String) params.get("companyBusinessLicenseElectronicVersion"))) {
                    code = "-16";
                    msg = "请上传电子营业执照副本";
                } else if (StringUtils.isBlank((String) params.get("contactName"))) {
                    code = "-17";
                    msg = "联系人姓名不能为空";
                } else if (StringUtils.isBlank((String) params.get("contactPhone"))) {
                    code = "-18";
                    msg = "联系人电话不能为空";
                } else if (StringUtils.isBlank((String) params.get("contactPosition"))) {
                    code = "-19";
                    msg = "联系人职务不能为空";
                } else if (StringUtils.isBlank((String) params.get("companyTelephone"))) {
                    code = "-20";
                    msg = "公司电话不能为空";
                } else if (StringUtils.isBlank((String) params.get("companyProvinceCityCountry"))) {
                    code = "-21";
                    msg = "请选择公司所在地";
                } else if (StringUtils.isBlank((String) params.get("companyAddress"))) {
                    code = "-22";
                    msg = "公司详细地址不能为空";
                } else if (null == catagorySecondIds || catagorySecondIds.length <= 0) {
                    code = "-23";
                    msg = "二类物资类型不能为空";
                } else {
                    MemberExtraData memberExtraData = JSONObject.parseObject(JSONObject.toJSONString(params), MemberExtraData.class);
                    memberExtraData.setId(UUID.randomUUID().toString().replace("-", ""));
                    memberExtraData.setCreateTime(new Date());
                    memberExtraData.setDelFlag("0");
                    memberExtraDataMapper.insertSelective(memberExtraData);

                    member.setExtraDataId(memberExtraData.getId());
                    //审核状态（暂时默认审核通过）
                    member.setExtraDataStatus("1");
                    memberMapper.updateByPrimaryKeySelective(member);

                    //保存物资类型
                    for (String catagorySecondId : catagorySecondIds) {
                        ProductSubCategory productSubCategory = materialMapper.selectSecondById(catagorySecondId);
                        if (productSubCategory != null) {
                            ProductSubCategoryRelevance productSubCategoryRelevance = new ProductSubCategoryRelevance();
                            productSubCategoryRelevance.preInsert();
                            productSubCategoryRelevance.setProductSubCategoryId(productSubCategory.getId());
                            productSubCategoryRelevance
                                    .setProductSubCategoryName(productSubCategory.getPcName());
                            productSubCategoryRelevance.setRelatedId(member.getId());
                            materialMapper.insert(productSubCategoryRelevance);
                        }
                    }
                    code = Constants.SUCCESS;
                    msg = "成功";
                }
            } else {
                code = "-3";
                msg = "未登录";
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
    public Result retrievePassword(Map<String, Object> params) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            String username = (String) params.get("username");
            String mobile = (String) params.get("mobile");
            String password = (String) params.get("password");
            String comfirmPassword = (String) params.get("comfirmPassword");
            String mobileCode = (String) params.get("mobileCode");
            String phoneCode = stringRedisTemplate.opsForValue().get(mobile);
            if (StringUtils.isBlank(username)) {
                code = "-3";
                msg = "用户名不能为空";
            }else if (StringUtils.isBlank(mobileCode)){
                code = "-4";
                msg = "验证码不能为空";
            }else if (StringUtils.isBlank(phoneCode)) {
                code = "-5";
                msg = "填写手机号与验证手机号不一致";
            } else if (StringUtils.isBlank(password)) {
                code = "-6";
                msg = "密码不能为空";
            } else if (!password.equals(comfirmPassword)) {
                code = "-7";
                msg = "两次密码不一致";
            } else if (!mobileCode.equals(phoneCode)) {
                code = "-8";
                msg = "验证码不正确";
            } else {
                Member member = memberMapper.selectByUserName(username);
                if (!member.getMobile().equals(mobile)) {
                    code = "-8";
                    msg = "验证手机号与用户注册手机号不符";
                } else {
                    User user = userMapper.selectByUserName(username);
                    user.setPassword(password);
                    userMapper.updateByPrimaryKeySelective(user);
                    code = Constants.SUCCESS;
                    msg = "成功";
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
