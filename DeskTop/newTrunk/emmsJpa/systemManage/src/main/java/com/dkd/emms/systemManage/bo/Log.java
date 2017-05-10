package com.dkd.emms.systemManage.bo;

import java.io.Serializable;

/**
 * 日志
 * @author SY
 * 
 */

public class Log implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String log_id;//日志ID
	private String log_name;//日志名称
	private String log_context;//日志内容
	private String log_user;//操作人
	private String log_IP;//IP
	private String log_date;//日志时间
	private String log_type;//日志类型
	private String log_order;//日志序号
	
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getLog_name() {
		return log_name;
	}
	public void setLog_name(String log_name) {
		this.log_name = log_name;
	}
	public String getLog_context() {
		return log_context;
	}
	public void setLog_context(String log_context) {
		this.log_context = log_context;
	}
	public String getLog_user() {
		return log_user;
	}
	public void setLog_user(String log_user) {
		this.log_user = log_user;
	}
	public String getLog_IP() {
		return log_IP;
	}
	public void setLog_IP(String log_IP) {
		this.log_IP = log_IP;
	}
	public String getLog_date() {
		return log_date;
	}
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	public String getLog_order() {
		return log_order;
	}
	public void setLog_order(String log_order) {
		this.log_order = log_order;
	}
	
}
