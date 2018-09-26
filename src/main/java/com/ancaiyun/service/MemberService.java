package com.ancaiyun.service;

import com.ancaiyun.util.Result;

public interface MemberService {

    //添加用户编号
    Result addMemberNumber();

    /**
     * 根据会员编号获取账户银行编号
     * @return
     */
    Result getMemberBankAccount(String memberNumber);
}
