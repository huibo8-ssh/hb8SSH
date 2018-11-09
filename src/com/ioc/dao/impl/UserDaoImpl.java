package com.ioc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ioc.dao.UserDao;
import com.ioc.entity.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 通过用户名加载一个用户对象 若用户名不存在则返回null
	 * @param userName
	 * @return
	 */
	@Override
	public User loadUserByUserName(String userName) {
		String hql = "from User where userName=?";
		List<User> list = hibernateTemplate.find(hql, userName);
		if(list != null && list.size() > 0) 
			return list.get(0);
		return null;
	}
	
}
