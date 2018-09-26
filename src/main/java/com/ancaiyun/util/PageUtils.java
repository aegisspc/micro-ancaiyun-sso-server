package com.ancaiyun.util;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @author: Catch-22
 * @create: 2018-07-26 09:57
 **/
public class PageUtils {

    public static void pageMethod(Map<String,Object> map) {
        Integer pageNum = 1;
        if(StringUtils.isNotBlank((String) map.get("pageNum"))) {
            pageNum = Integer.parseInt((String) map.get("pageNum"));
        }
        Integer pageSize = 10;
        if(StringUtils.isNotBlank((String) map.get("pageSize"))) {
            pageSize = Integer.parseInt((String) map.get("pageSize"));
        }
        PageHelperNew.startPage(pageNum, pageSize);
    }
}
