package com.ancaiyun.controller;

import com.ancaiyun.service.MaterialService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaterialController {
	
	@Autowired
	private MaterialService materialService;
	
	/**
	 * 查询所有物资一类
	 * @return
	 */
	@RequestMapping(value = "v1/product-categorys/selection", method = RequestMethod.GET)
	public Result getAllCategory() {
		return materialService.categoryListPage();
	}
	
	/**
	 * 一类id所有物资二类
	 * @return
	 */
	@RequestMapping(value = "v1/product-sub-categorys/selection", method = RequestMethod.GET)
	public Result getSubCategoryById(String pcCategoryId) {
		return materialService.subCategoryListPage(pcCategoryId);
	}
}
