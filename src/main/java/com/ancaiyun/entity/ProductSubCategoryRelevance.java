package com.ancaiyun.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ProductSubCategoryRelevance implements Serializable {

	/**
	 * 招投标物资类型
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 删除状态
	 */
	private String delFlag;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 招标id
	 */
	private String relatedId;
	/**
	 * 物资二类ID
	 */
	private String productSubCategoryId;
	/**
	 * 物资二类名称
	 */
	private String productSubCategoryName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	public String getProductSubCategoryId() {
		return productSubCategoryId;
	}

	public void setProductSubCategoryId(String productSubCategoryId) {
		this.productSubCategoryId = productSubCategoryId;
	}

	public String getProductSubCategoryName() {
		return productSubCategoryName;
	}

	public void setProductSubCategoryName(String productSubCategoryName) {
		this.productSubCategoryName = productSubCategoryName;
	}
	
	public void preInsert(){
		this.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		this.setCreateTime(new Date());
		this.setDelFlag("0");
	}
}
