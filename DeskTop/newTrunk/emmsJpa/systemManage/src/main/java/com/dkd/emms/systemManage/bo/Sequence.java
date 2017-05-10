package com.dkd.emms.systemManage.bo;

public class Sequence {
	/**
	 * 实体名
	 */
	private String entityType;
	/**
	 * 流水依据
	 */
	private  String flowJudgeValue;
	/**
	 * 流水长度
	 */
	private  int length;
	/**
	 * 流水码
	 */
	private String flowNo;
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getFlowJudgeValue() {
		return flowJudgeValue;
	}
	public void setFlowJudgeValue(String flowJudgeValue) {
		this.flowJudgeValue = flowJudgeValue;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
