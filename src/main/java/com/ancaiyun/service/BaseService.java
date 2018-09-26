package com.ancaiyun.service;

import com.ancaiyun.util.Result;

import java.util.Map;


/**
 * 公共查询接口
 * @author Catch22
 * @date 2018年6月6日
 */
public interface BaseService {

    /**
     * 条件查询
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    Result listPage(Integer pageNum, Integer pageSize, Map<String, Object> params);

    /**
     * id查询
     * @param id
     * @return
     */
    Result selectByPrimaryKey(String id);

}
