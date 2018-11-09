package com.ioc.dao;

import com.ioc.entity.User;

public interface UserDao {
	
	/**
	 * 通过用户名加载一个用户对象 若用户名不存在则返回null
	 * @param userName
	 * @return
	 */
	User loadUserByUserName(String userName);

}
