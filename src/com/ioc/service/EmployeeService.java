package com.ioc.service;


import java.util.List;

import com.ioc.entity.Dept;
import com.ioc.entity.Employee;
import com.ioc.entity.EmployeeVo;
import com.ioc.entity.query.EmployeeQueryVo;
import com.ioc.utils.Page;

public interface EmployeeService {
	
	/**
	 * 加载员工列表-分页、多条件组合
	 * @param eqv
	 * @return
	 */
	Page<EmployeeVo> loadEmployeeByPage(EmployeeQueryVo eqv);

	/**
	 * 通过员工id加载一个员工对象
	 * @param empid
	 * @return
	 */
	Employee loadEmployeeById(int empid);
	
	/**
	 * 加载所有部门
	 * @return
	 */
	List<Dept> loadAllDept();
}
