package com.ancaiyun.mapper;

import com.ancaiyun.entity.MemberExtraData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberExtraDataMapper {

    /**
     * 查询企业资质详情
     * @param record
     * @return
     */
    MemberExtraData selectByPrimaryKey(String record);

    /**
     * 修改企业资质信息
     * @param obj
     * @return
     */
    int updateByPrimaryKeySelective(MemberExtraData obj);

    /**
     * 保存企业资质相关信息
     * @param memberExtraData
     * @return
     */
    int insertSelective(MemberExtraData memberExtraData);
}
