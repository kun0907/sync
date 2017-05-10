package com.dkd.emms.web.util.page;

import java.util.List;

/**
 * 分页实体
 * @author wangqian
 *
 */
public class PageBean<T> {
	/**
	 * 结果总条数
	 */
	private int total;
	/**
	 * 查询结果集
	 */
	private List<T> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
