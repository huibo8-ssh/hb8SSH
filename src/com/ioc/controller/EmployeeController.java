package com.ioc.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ioc.entity.Dept;
import com.ioc.entity.Employee;
import com.ioc.entity.EmployeeVo;
import com.ioc.entity.query.EmployeeQueryVo;
import com.ioc.service.EmployeeService;
import com.ioc.utils.Page;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ResponseBody
	@RequestMapping(value="employeeList.do")
	public Page<EmployeeVo> employeeList(EmployeeQueryVo eqv) {
		Page<EmployeeVo> page = employeeService.loadEmployeeByPage(eqv);
		return page;
	}
	
	@ResponseBody
	@RequestMapping(value="loadAllDept.do")
	public List<Dept> loadAllDept(){
		return employeeService.loadAllDept();
	}
	
	@ResponseBody
	@RequestMapping(value="loadEmployeeById.do")
	public Employee loadEmployeeById(int empid) {
		return employeeService.loadEmployeeById(empid);
	}

}
