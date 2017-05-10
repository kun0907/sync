package com.dkd.emms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 系统实体的基类
 * @author WANGQ
 *
 */
public class BaseEntity {
	/**
	 * 录入人姓名
	 */
	private String createUserName;
	
	/**
	 * 录入人Id
	 */
	private String createUserId;
	
	/**
	 * 录入时间
	 */
	private Date createTime ;
	
	/**
	 * 当前页
	 */
	private String pageNow;
	/**
	 * 是够有权限标志
	 */
	private Boolean isAuthority=false;
	public String getPageNow() {
		return pageNow;
	}

	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsAuthority() {
		return isAuthority;
	}

	public void setIsAuthority(Boolean isAuthority) {
		this.isAuthority = isAuthority;
	}
}
