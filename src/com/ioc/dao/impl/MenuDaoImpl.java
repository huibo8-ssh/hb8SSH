package com.ioc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ioc.dao.MenuDao;
import com.ioc.entity.Menu;

@Repository
public class MenuDaoImpl implements MenuDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 通过登录用户的id去查询该用户拥有的菜单权限集合
	 * @param uid
	 * @return
	 */
	@Override
	public List<Menu> loadMenusByUid(int uid) {
		String sql = "select distinct m.* from tb_userrole ur, tb_rolemenu rm, tb_menu m " + 
				"where ur.userid=? and ur.roleid=rm.roleid and rm.menuid=m.mid " + 
				"and m.isHomePage=1";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		return session.createSQLQuery(sql).addEntity(Menu.class).setInteger(0, uid).list();
//		return hibernateTemplate.execute(new HibernateCallback<List<Menu>>() {
//			@Override
//			public List<Menu> doInHibernate(Session session) throws HibernateException, SQLException {
//				return session.createSQLQuery(sql).addEntity(Menu.class).setInteger(0, uid).list();
//			}
//		});
	}

}
