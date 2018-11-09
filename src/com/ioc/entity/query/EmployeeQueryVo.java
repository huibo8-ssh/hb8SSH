package com.ioc.entity.query;

/**
 * 员工查询实体 接收员工搜索的条件参数
 * @author Administrator
 *
 */
public class EmployeeQueryVo{
	private Integer empid;
	private Integer pageNo;
	private Integer pageSize;
	private String searchEmpName;
	private String searchPhone;
	private Integer searchDeptid;
	private Integer searchGender;
	private Integer searchAge1;
	private Integer searchAge2;
	private String searchEntryDate1;
	private String searchEntryDate2;
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearchEmpName() {
		return searchEmpName;
	}
	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}
	public String getSearchPhone() {
		return searchPhone;
	}
	public void setSearchPhone(String searchPhone) {
		this.searchPhone = searchPhone;
	}
	public Integer getSearchDeptid() {
		return searchDeptid;
	}
	public void setSearchDeptid(Integer searchDeptid) {
		this.searchDeptid = searchDeptid;
	}
	public Integer getSearchGender() {
		return searchGender;
	}
	public void setSearchGender(Integer searchGender) {
		this.searchGender = searchGender;
	}
	public Integer getSearchAge1() {
		return searchAge1;
	}
	public void setSearchAge1(Integer searchAge1) {
		this.searchAge1 = searchAge1;
	}
	public Integer getSearchAge2() {
		return searchAge2;
	}
	public void setSearchAge2(Integer searchAge2) {
		this.searchAge2 = searchAge2;
	}
	public String getSearchEntryDate1() {
		return searchEntryDate1;
	}
	public void setSearchEntryDate1(String searchEntryDate1) {
		this.searchEntryDate1 = searchEntryDate1;
	}
	public String getSearchEntryDate2() {
		return searchEntryDate2;
	}
	public void setSearchEntryDate2(String searchEntryDate2) {
		this.searchEntryDate2 = searchEntryDate2;
	}
}
