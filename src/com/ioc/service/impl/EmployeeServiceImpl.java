package com.ioc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioc.dao.EmployeeDao;
import com.ioc.entity.Dept;
import com.ioc.entity.Employee;
import com.ioc.entity.EmployeeVo;
import com.ioc.entity.query.EmployeeQueryVo;
import com.ioc.service.EmployeeService;
import com.ioc.utils.Page;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	/**
	 * 加载员工列表-分页、多条件组合
	 * @param eqv
	 * @return
	 */
	@Override
	public Page<EmployeeVo> loadEmployeeByPage(EmployeeQueryVo eqv) {
		return employeeDao.loadEmployeeByPage(eqv);
	}

	/**
	 * 通过员工id加载一个员工对象
	 * @param empid
	 * @return
	 */
	@Override
	public Employee loadEmployeeById(int empid) {
		return employeeDao.loadEmployeeById(empid);
	}

	/**
	 * 加载所有部门
	 * @return
	 */
	@Override
	public List<Dept> loadAllDept() {
		List<Dept> list = employeeDao.loadAllDept();
		list.add(0, new Dept(-1, "--请选择部门--"));
		return list;
	}


}
