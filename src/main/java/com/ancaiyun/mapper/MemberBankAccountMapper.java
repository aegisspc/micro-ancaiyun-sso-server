package com.ancaiyun.mapper;

import com.ancaiyun.entity.MemberBankAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberBankAccountMapper {

    MemberBankAccount selectByPrimaryKey(String record);

    /**
     * 给会员添加银行编号
     * @param memberBankAccount
     * @return
     */
    Integer insertSelective(MemberBankAccount memberBankAccount);
}
