package com.ancaiyun.mapper;

import com.ancaiyun.entity.ProductCategory;
import com.ancaiyun.entity.ProductSubCategory;
import com.ancaiyun.entity.ProductSubCategoryRelevance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 物资查询
 * @author Catch_22
 * @package com.ancaiyun.mapper 
 * @date 2018年8月2日 上午10:53:39
 */
@Mapper
@Repository
public interface MaterialMapper {
	
	/**
	 * 条件查询招标物资二类/三类
	 * @param product
	 * @return
	 */
	List<ProductSubCategoryRelevance> selectBidSecondBySelection(ProductSubCategoryRelevance product);
	
	/**
	 * 添加招标物资二类
	 * @param relevance
	 * @return
	 */
	int insert(ProductSubCategoryRelevance relevance);
	
	/**
	 * 删除招标物资二类三类
	 * @param id
	 * @return
	 */
	int delete(String id);
	
	/**
	 * 条件查询物资二类/三类
	 * @param product
	 * @return
	 */
	List<ProductSubCategory> selectSecondBySelection(ProductSubCategory product);
	
	/**
	 * id查询物资二类三类
	 * @param id
	 * @return
	 */
	ProductSubCategory selectSecondById(String id);
	
	/**
	 * id查询物资一类
	 * @param id
	 * @return
	 */
	ProductCategory selectFirstById(String id);
	
	/**
	 * 查询所有物资一类
	 * @return
	 */
	List<ProductCategory> selectFirstAll();

}
