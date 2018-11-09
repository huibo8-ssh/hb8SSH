package com.ioc.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ioc.dao.EmployeeDao;
import com.ioc.entity.Dept;
import com.ioc.entity.Employee;
import com.ioc.entity.EmployeeVo;
import com.ioc.entity.query.EmployeeQueryVo;
import com.ioc.utils.Page;

@Repository
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
	@Autowired
	private HibernateTemplate ht;

	/**
	 * 加载员工列表-分页、多条件组合
	 * @param eqv
	 * @return
	 */
	@Override
	public Page<EmployeeVo> loadEmployeeByPage(EmployeeQueryVo eqv) {
		//StringBuilder hql = new StringBuilder("from EmployeeVo e where 1=1");
		DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeVo.class);
		if(eqv.getSearchEmpName() != null && !"".equals(eqv.getSearchEmpName())) {
			//hql.append(" and e.empName like '%").append(eqv.getSearchEmpName()).append("%'");
			criteria.add(Restrictions.like("empName", "%"+eqv.getSearchEmpName()+"%"));
		}
		if(eqv.getSearchPhone() != null && !"".equals(eqv.getSearchPhone())) {
			//hql.append(" and e.phone like '%").append(eqv.getSearchPhone()).append("%'");
			criteria.add(Restrictions.like("phone", "%"+eqv.getSearchPhone()+"%"));
		}
		if(eqv.getSearchDeptid() != null && eqv.getSearchDeptid() > 0) {
			//hql.append(" and e.deptid=").append(eqv.getSearchDeptid());
			criteria.add(Restrictions.eq("deptid", eqv.getSearchDeptid()));
		}
		if(eqv.getSearchGender() != null && eqv.getSearchGender() > 0) {
			//hql.append(" and e.gender=").append(eqv.getSearchGender());
			criteria.add(Restrictions.eq("gender", eqv.getSearchGender()));
		}
		if(eqv.getSearchAge1() != null && eqv.getSearchAge1() > 0) {
			//hql.append(" and e.age>=").append(eqv.getSearchAge1());
			criteria.add(Restrictions.ge("age", eqv.getSearchAge1()));
		}
		if(eqv.getSearchAge2() != null && eqv.getSearchAge2() > 0) {
			//hql.append(" and e.age<=").append(eqv.getSearchAge2());
			criteria.add(Restrictions.le("age", eqv.getSearchAge2()));
		}
		if(eqv.getSearchEntryDate1() != null && !"".equals(eqv.getSearchEntryDate1())) {
			//hql.append(" and e.entryDate>='").append(eqv.getSearchEntryDate1()).append("'");
			criteria.add(Restrictions.ge("entryDate", eqv.getSearchEntryDate1()));
		}
		if(eqv.getSearchEntryDate2() != null && !"".equals(eqv.getSearchEntryDate2())) {
			//hql.append(" and e.entryDate<='").append(eqv.getSearchEntryDate2()).append("'");
			criteria.add(Restrictions.le("entryDate", eqv.getSearchEntryDate2()));
		}
		criteria.addOrder(Order.desc("empid"));
		//hql.append(" order by e.empid desc");
		
		return this.queryByPage(eqv.getPageNo(), eqv.getPageSize(), criteria);
	}

	/**
	 * 通过员工id加载一个员工对象
	 * @param empid
	 * @return
	 */
	@Override
	public Employee loadEmployeeById(int empid) {
		return ht.get(Employee.class, empid);
	}

	/**
	 * 加载所有部门
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Dept> loadAllDept() {
		return ht.find("from Dept");
	}

}
