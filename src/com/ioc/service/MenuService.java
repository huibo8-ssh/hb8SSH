package com.ioc.service;

import java.util.List;

import com.ioc.entity.Menu;

public interface MenuService {
	
	/**
	 * 通过登录用户的id去查询该用户拥有的菜单权限集合
	 * @param uid
	 * @return
	 */
	List<Menu> loadMenusByUid(int uid);

}
