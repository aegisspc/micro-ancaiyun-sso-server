package com.ancaiyun.serviceImpl;

import com.ancaiyun.entity.Member;
import com.ancaiyun.entity.MemberBankAccount;
import com.ancaiyun.entity.MemberExtraData;
import com.ancaiyun.entity.User;
import com.ancaiyun.mapper.MemberBankAccountMapper;
import com.ancaiyun.mapper.MemberExtraDataMapper;
import com.ancaiyun.mapper.MemberMapper;
import com.ancaiyun.mapper.UserMapper;
import com.ancaiyun.service.CurrentUserService;
import com.ancaiyun.util.Constants;
import com.ancaiyun.util.CurrentUserUtil;
import com.ancaiyun.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;


@Service
@Transactional
public class CurrentUserServiceImpl implements CurrentUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberExtraDataMapper memberExtraDataMapper;
    @Autowired
    private MemberBankAccountMapper memberBankAccountMapper;
    @Override
    public Result getUserInfo(String accessToken) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try{
            if(StringUtils.isBlank(accessToken)){
                result.setCode("-3");
                result.setMsg("accessToken不能为空");
                return result;
            }

            String userId = CurrentUserUtil.parseJWT(accessToken);
            ModelMap modelMap = new ModelMap();
            User user = userMapper.selectByPrimaryKey(userId);

            //判断账号是否为主账号，若不是主账号则获取主账号会员信息
            Member member = new Member();
            if (StringUtils.isNotBlank(user.getParentId())){
                member = memberMapper.selectByUserId(user.getParentId());
                modelMap.addAttribute("member",member);
            }else {
                member = memberMapper.selectByUserName(user.getUsername());
                modelMap.addAttribute("member",member);
            }

            modelMap.addAttribute("user",user);
            if (StringUtils.isNotBlank(member.getExtraDataId())){
                MemberExtraData memberExtraData = memberExtraDataMapper.selectByPrimaryKey(member.getExtraDataId());
                modelMap.addAttribute("memberExtraData",memberExtraData);
            }else {
                modelMap.addAttribute("memberExtraData",null);
            }

            if(StringUtils.isNotBlank(member.getMemberBankAccountId())){
                MemberBankAccount memberBankAccount = memberBankAccountMapper.selectByPrimaryKey(member.getMemberBankAccountId());
                modelMap.addAttribute("memberBankAccount",memberBankAccount);
            }else {
                modelMap.addAttribute("memberBankAccount",null);
            }
            code = Constants.SUCCESS;
            msg = "成功";
            result.setData(modelMap);
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
