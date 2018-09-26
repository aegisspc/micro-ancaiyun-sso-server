package com.ancaiyun.serviceImpl;

import com.ancaiyun.mapper.BaseMapper;
import com.ancaiyun.service.BaseService;
import com.ancaiyun.util.Constants;
import com.ancaiyun.util.PageHelperNew;
import com.ancaiyun.util.RandomCodeUtil;
import com.ancaiyun.util.Result;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 公共查询接口实现类
 * 
 * @author Catch22
 * @date 2018年6月6日
 */
public abstract class BaseServiceImpl<M extends BaseMapper> implements BaseService {

	@Autowired
	private M mapper;

	@Override
	public Result listPage(Integer pageNum, Integer pageSize, Map<String, Object> params) {
		Result result = new Result();
		String code = Constants.FAIL;
		String msg = "初始化";
		try {
			String time = null;
			// 处理时间搜索,将截止日期往后推一天
			if (params.containsKey("endTime")) {
				time = params.get("endTime").toString();
				if (StringUtils.isNotBlank(time)) {
					String endTime = RandomCodeUtil.dateToStr(RandomCodeUtil.dateAdd(RandomCodeUtil.convert(time),1));
					params.put("endTime", endTime);
				}
			}
			if (params.containsKey("delFlag")) {
				PageHelperNew.startPage(pageNum, pageSize);
			}
			List<Map<String, Object>> maps = mapper.selectAllBySelection(params);
			if (params.containsKey("delFlag")) {
				PageInfo<Map<String, Object>> page = new PageInfo<>(maps);
				result.setData(page);
			} else {
				result.setData(maps);
			}
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
	public Result selectByPrimaryKey(String id) {
		Result result = new Result();
		String code = Constants.FAIL;
		String msg = "初始化";
		try {
			if(StringUtils.isNotBlank(id)) {
				Map<String, Object> maps = mapper.selectByPrimaryKey(id);
				if (maps != null) {
					result.setData(maps);
					code = Constants.SUCCESS;
					msg = "成功";
				}
			}else {
				code = "-5";
				msg = "id不能为空";
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
