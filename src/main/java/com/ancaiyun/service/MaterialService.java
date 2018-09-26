package com.ancaiyun.service;


import com.ancaiyun.util.Result;

public interface MaterialService {
	
	/**
	 * 查询大类列表
	 * @return
	 */
	Result categoryListPage();
	
	/**
	 * 条件查询二类列表
	 * @param pcCategoryId
	 * @return
	 */
	Result subCategoryListPage(String pcCategoryId);

}
