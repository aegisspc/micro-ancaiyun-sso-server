package com.ancaiyun.serviceImpl;

import com.ancaiyun.entity.ProductCategory;
import com.ancaiyun.entity.ProductSubCategory;
import com.ancaiyun.mapper.MaterialMapper;
import com.ancaiyun.service.MaterialService;
import com.ancaiyun.util.Constants;
import com.ancaiyun.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialMapper materialMapper;

	@Override
	public Result categoryListPage() {
		Result result = new Result();
		String code = Constants.FAIL;
		String msg = "初始化";
		try {
			List<ProductCategory> maps = materialMapper.selectFirstAll();
			result.setData(maps);
			code = Constants.SUCCESS;
			msg = "成功";
		} catch (Exception e) {
			code = Constants.ERROR;
			msg = "系统繁忙";
			e.printStackTrace();
		}
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	@Override
	public Result subCategoryListPage(String pcCategoryId) {
		Result result = new Result();
		String code = Constants.FAIL;
		String msg = "初始化";
		try {
			if (StringUtils.isNotBlank(pcCategoryId)) {
				ProductSubCategory sub = new ProductSubCategory();
				sub.setPcCategoryId(pcCategoryId);
				List<ProductSubCategory> maps = materialMapper.selectSecondBySelection(sub);
				result.setData(maps);
				code = Constants.SUCCESS;
				msg = "成功";
			}else{
				code = "-5";
				msg = "物资一类id不能为空";
			}
		} catch (Exception e) {
			code = Constants.ERROR;
			msg = "系统繁忙";
			e.printStackTrace();
		}
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

}
