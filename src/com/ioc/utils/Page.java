package com.ioc.utils;

import java.io.Serializable;
import java.util.List;
/**
 * 分页工具类
 * @author Administrator
 * @param <T>
 */
public class Page<T> implements Serializable{
	private static final long serialVersionUID = -908675080356722002L;

	//当前页码 默认展示第一页
	private int pageNo = 1;

	//每页显示行数 默认10行
	private int pageSize = 10;
	
	//共多少行数据
	private long total;
	
	//当前页要展示的数据集合
	private List<T> rows;
	
	//总页数
	private long pageCount;

	public Page(int pageNo, int pageSize, long total, List<T> rows) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
		pageCount = total%pageSize==0 ? total/pageSize : total/pageSize+1;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
