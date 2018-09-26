package com.ancaiyun.controller;

import com.ancaiyun.service.MemberService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 给所有会员添加会员编号
     * @return
     */
    @RequestMapping(value = "v1/create-member-number-full",method = RequestMethod.PUT)
    public Result addMemberNumber(){
        return memberService.addMemberNumber();
    }

    /**
     *
     * @param memberNumber
     * @return
     */
    @RequestMapping(value = "v1/auth/member-bank-account",method = RequestMethod.GET)
    public Result getBankAccountByMember(String memberNumber){
        return memberService.getMemberBankAccount(memberNumber);
    }
}
