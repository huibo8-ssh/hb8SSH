package com.ioc.service;

import com.ioc.entity.User;

public interface UserService {
	
	/**
	 * 通过用户名加载一个用户对象 若用户名不存在则返回null
	 * @param userName
	 * @return
	 */
	User loadUserByUserName(String userName);
	

	/**
	 * 删除多个用户
	 * @param uids 是用户id以逗号隔开
	 */
	void deleteUsers(String uids);
	
}
