package com.ancaiyun.serviceImpl;

import com.ancaiyun.entity.Member;
import com.ancaiyun.entity.MemberBankAccount;
import com.ancaiyun.mapper.MemberBankAccountMapper;
import com.ancaiyun.mapper.MemberMapper;
import com.ancaiyun.service.MemberService;
import com.ancaiyun.util.Constants;
import com.ancaiyun.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired(required = false)
    private RestTemplate restTemplate;
    @Autowired
    private MemberBankAccountMapper memberBankAccountMapper;

    @Override
    public Result addMemberNumber() {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try {
            List<Member> members = memberMapper.selectAll();
            //给所有会员添加会员编号
            for (Member member:members){
                String tradeStatus = member.getTradeStatus();
                Result userResult = restTemplate.getForEntity("http://micro-ancaiyun-zuul/finance/v1/create-member-number?tradeStatus=" + tradeStatus+"&method=GET",Result.class).getBody();
                if ("0".equals(userResult.getCode())){
                    String memberNumber = (String) userResult.getData();
                    System.out.println(memberNumber);
                    member.setMemberNumber(memberNumber);
                    memberMapper.updateByPrimaryKeySelective(member);
                    code = Constants.SUCCESS;
                    msg = "成功";
                }else {
                    code = "-3";
                    msg = "添加会员编号失败";
                }
            }
        } catch (Exception e) {
            code = Constants.ERROR;
            msg = "系统繁忙";
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    @Override
    public Result getMemberBankAccount(String memberNumber) {
        Result result = new Result();
        String code = Constants.FAIL;
        String msg = "初始化";
        try{
            if(StringUtils.isBlank(memberNumber)){
                result.setCode("-3");
                result.setMsg("会员编号不能为空");
                return result;
            }
            Member member = memberMapper.selectByMemberNumber(memberNumber);
            if (null == member){
                result.setCode("-4");
                result.setMsg("会员编号不存在");
                return result;
            }
            if (StringUtils.isBlank(member.getMemberBankAccountId())){
                result.setCode("-5");
                result.setMsg("该会员没有银行账户相关编号");
                return result;
            }
            MemberBankAccount memberBankAccount = memberBankAccountMapper.selectByPrimaryKey(member.getMemberBankAccountId());
            result.setData(memberBankAccount);
            code = Constants.SUCCESS;
            msg = "成功";

        }catch (Exception e){
            code = Constants.ERROR;
            msg = "系统繁忙";
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

