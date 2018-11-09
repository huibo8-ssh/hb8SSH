package com.ioc.entity;

import java.io.Serializable;

public class MenuVo implements Serializable{
	private static final long serialVersionUID = 1102007632396696565L;
	
	private int mid;
	
	private String menuName;
	
	private int level = 1;
	
	//菜单被点击后要访问的url
	private String url;
	
	//当前菜单的父级菜单的ID值，若当前菜单是最顶级的菜单，则此字段填入-1
	private int parentid = -1;
	
	//是否在首页上左侧树形菜单中显示 默认1表示显示
	private int isHomePage;
	
	private String parentName;

	
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getIsHomePage() {
		return isHomePage;
	}

	public void setIsHomePage(int isHomePage) {
		this.isHomePage = isHomePage;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
