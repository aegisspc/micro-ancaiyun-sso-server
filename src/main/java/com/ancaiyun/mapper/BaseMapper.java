package com.ancaiyun.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Catch22
 * @date 2018年6月6日
 */
public interface BaseMapper {
    
    /**
     * 条件查询
     * @param params
     * @return
     */
    public List<Map<String,Object>> selectAllBySelection(Map<String, Object> params);
    
    /**
     * 根据id查看详情
     * @param id
     * @return
     */
    public Map<String,Object> selectByPrimaryKey(String id);

}
