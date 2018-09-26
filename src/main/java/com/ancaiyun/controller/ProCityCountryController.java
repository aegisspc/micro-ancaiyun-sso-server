package com.ancaiyun.controller;

import com.ancaiyun.service.CitysService;
import com.ancaiyun.service.CountrysService;
import com.ancaiyun.service.ProvincesService;
import com.ancaiyun.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Catch22
 * @date 2018年6月21日
 */
@RestController
public class ProCityCountryController {

	@Autowired
	private ProvincesService proService;

	@Autowired
	private CitysService cityService;

	@Autowired
	private CountrysService countryService;

	/**
	 * 条件查询省份列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "v1/auth/provinces/selection", method = RequestMethod.GET)
	public Result getAllProvinces(@RequestParam Map<String, Object> params) {
		return proService.listPage(null, null, params);
	}

	/**
	 * id查询省份详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "v1/auth/provinces/detail", method = RequestMethod.GET)
	public Result getProvincesByPrimaryKey(@RequestParam String id) {
		return proService.selectByPrimaryKey(id);
	}

	/**
	 * 条件查询城市列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "v1/auth/citys/selection", method = RequestMethod.GET)
	public Result getAllCitys(@RequestParam Map<String, Object> params) {
		return cityService.listPage(null, null, params);
	}

	/**
	 * id查询城市详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "v1/auth/citys/detail", method = RequestMethod.GET)
	public Result getCitysByPrimaryKey(@RequestParam String id) {
		return cityService.selectByPrimaryKey(id);
	}

	/**
	 * 条件查询区列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "v1/auth/countrys/selection", method = RequestMethod.GET)
	public Result getAllCountrys(@RequestParam Map<String, Object> params) {
		return countryService.listPage(null, null, params);
	}

	/**
	 * id查询区详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "v1/auth/countrys/detail", method = RequestMethod.GET)
	public Result getCountrysByPrimaryKey(@RequestParam String id) {
		return countryService.selectByPrimaryKey(id);
	}
}
