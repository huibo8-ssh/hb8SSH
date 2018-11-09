package com.ioc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限菜单
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_menu")
public class Menu implements Serializable{
	private static final long serialVersionUID = 1566650010214401623L;

	@Id
	@Column(name="mid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int mid;
	
	@Column(name="menuName",length=30,nullable=false)
	private String menuName;
	
	@Column(name="level")
	private int level = 1;
	
	//菜单被点击后要访问的url
	@Column(name="url",length=50)
	private String url;
	
	//当前菜单的父级菜单的ID值，若当前菜单是最顶级的菜单，则此字段填入-1
	@Column(name="parentid")
	private int parentid = -1;
	
	//是否在首页上左侧树形菜单中显示 默认1表示显示
	@Column(name="isHomePage")
	private int isHomePage;

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

	public Menu(int mid, String menuName) {
		this.mid = mid;
		this.menuName = menuName;
	}

	public Menu() {}
	
	
	
}
