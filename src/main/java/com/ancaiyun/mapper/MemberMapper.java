package com.ancaiyun.mapper;

import com.ancaiyun.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {

    /**
     * 查询会员信息
     * @param record
     * @return
     */
    Member selectByPrimaryKey(String record);
    /**
     * 根据用户名查询会员信息
     * @param record
     * @return
     */
    Member selectByUserName(String record);

    /**
     * 根据电话号码查询会员信息
     * @param record
     * @return
     */
    Member selectByMobile(String record);

    /**
     * 根据userId查询会员信息
     * @param record
     * @return
     */
    Member selectByUserId(String record);

    /**
     * 更新会员信息
     * @param member
     * @return
     */
    int updateByPrimaryKeySelective(Member member);

    /**
     * 添加会员信息
     * @param member
     * @return
     */
    int insertSelective(Member member);

    /**
     * 查询所有会员信息
     * @return
     */
    List<Member> selectAll();

    /**
     * 根据编号查会员
     * @param memberNumber
     * @return
     */
    Member selectByMemberNumber(String memberNumber);
}
