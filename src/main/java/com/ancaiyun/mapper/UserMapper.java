package com.ancaiyun.mapper;

import com.ancaiyun.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据id查用户
     * @param record
     * @return
     */
    User selectByPrimaryKey(String record);

    /**
     * 根据用户名查用户
     * @param record
     * @return
     */
    User selectByUserName(String record);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 添加账户信息
     * @param user
     * @return
     */
    int insertSelective(User user);
}
