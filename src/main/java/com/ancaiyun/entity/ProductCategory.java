package com.ancaiyun.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductCategory implements Serializable {

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
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 排序
	 */
	private Integer typeSequence;
	/**
	 * 排序
	 */
	private Integer sortNum;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeSequence() {
		return typeSequence;
	}

	public void setTypeSequence(Integer typeSequence) {
		this.typeSequence = typeSequence;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
