package com.ioc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioc.dao.MenuDao;
import com.ioc.entity.Menu;
import com.ioc.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDao menuDao;

	/**
	 * 通过登录用户的id去查询该用户拥有的菜单权限集合
	 * @param uid
	 * @return
	 */
	@Override
	public List<Menu> loadMenusByUid(int uid) {
		return menuDao.loadMenusByUid(uid);
	}

	

}
