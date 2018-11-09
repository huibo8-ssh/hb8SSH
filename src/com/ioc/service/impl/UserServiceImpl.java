package com.ioc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ioc.dao.UserDao;
import com.ioc.entity.User;
import com.ioc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 通过用户名加载一个用户对象 若用户名不存在则返回null
	 * @param userName
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public User loadUserByUserName(String userName) {
		return userDao.loadUserByUserName(userName);
	}

	/**
	 * 删除多个用户
	 * @param uids 是用户id以逗号隔开
	 */
	@Override
	@Transactional(readOnly=false)
	public void deleteUsers(String uids) {
		
	}

	
	

}
