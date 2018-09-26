package com.ancaiyun.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductSubCategory implements Serializable {

	/**
	 * 
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
	 * 类目名称
	 */
	private String pcName;
	/**
	 * 类目等级
	 */
	private Boolean pcLevel;
	/**
	 * 排序
	 */
	private Integer pcSequence;
	/**
	 * 父
	 */
	private String parentId;
	/**
	 * 商品大类
	 */
	private String pcCategoryId;

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

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName == null ? null : pcName.trim();
	}

	public Boolean getPcLevel() {
		return pcLevel;
	}

	public void setPcLevel(Boolean pcLevel) {
		this.pcLevel = pcLevel;
	}

	public Integer getPcSequence() {
		return pcSequence;
	}

	public void setPcSequence(Integer pcSequence) {
		this.pcSequence = pcSequence;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getPcCategoryId() {
		return pcCategoryId;
	}

	public void setPcCategoryId(String pcCategoryId) {
		this.pcCategoryId = pcCategoryId == null ? null : pcCategoryId.trim();
	}

}
