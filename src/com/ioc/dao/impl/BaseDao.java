package com.ioc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ioc.utils.Page;

@Repository
public class BaseDao {
	@Autowired
	private HibernateTemplate ht;
	
	/**
	 * 分页查询
	 * @param hql	完整的HQL语句 可带? 不带DetachedCriteria参数的
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> queryByPage(final String hql, final int pageNo, final int pageSize, final Object... params){
		long total = ht.find(hql).size();
		List<T> rows = ht.executeFind(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for(int i=0;i<params.length;i++) {
					query.setParameter(i, params[i]);
				}		
				return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
			}
		});
		return new Page<T>(pageNo, pageSize, total, rows);
	}
	
	/**
	 * 分页查询-支持DetachedCriteria动态参数
	 * @param pageNo
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> queryByPage(int pageNo, int pageSize, DetachedCriteria criteria){
		long total = ht.findByCriteria(criteria).size();
		List<T> rows = ht.findByCriteria(criteria, (pageNo-1)*pageSize, pageSize);
		return new Page<T>(pageNo, pageSize, total, rows);
	}
	
	/**
	 * 执行普通的SQL查询语句
	 * @param sql
	 * @param cls
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(final String sql, final Class<T> cls, final Object... params){
		return ht.execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				for(int i=0;i<params.length;i++) {
					sqlQuery.setParameter(i, params[i]);
				}
				sqlQuery = (SQLQuery)sqlQuery;
				return sqlQuery.addEntity(cls).list();
			}
		});
	}
	
	/**
	 * 执行普通的SQL增删改语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public int find(final String sql, final Object... params){
		return ht.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				for(int i=0;i<params.length;i++) {
					sqlQuery.setParameter(i, params[i]);
				}
				sqlQuery = (SQLQuery)sqlQuery;
				return sqlQuery.executeUpdate();
			}
		});
	}
	
}
