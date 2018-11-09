package com.ioc.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.ioc.service.EmployeeService;
import com.ioc.service.MenuService;
import com.ioc.service.UserService;

/**
 * 
 * @author Administrator
 *	SpringAOP(Aspect Oriented Programming)实现原理：
 *	面向切面编程，相当于在某个接口的实现类的对象的某个方法调用前后添加一些业务逻辑，这些业务逻辑相对于方法调用者来说是隐蔽的，
 *	底层使用动态代理方式来实现，（InvocationHandler Proxy）必须有一个接口，该接口必须要有一个实现类，生成代理对象就是
 *	去代理该接口的实现类的对象。该代理对象（必须要持有一个该接口的实现类的对象作为属性）与该接口的实现类都实现了相同的接口
 *	（类中方法完全相同，方法内部调用该属性对象的同名方法，前后加一些业务逻辑），AOP比较常用于事务管理、日志管理等功能。
 *	AOP是动态代理的一种非常常见的应用。
 *  利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率
 */
//@Controller
//@RequestMapping("/user")
public class DispatcherController implements org.springframework.web.servlet.mvc.Controller{
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private EmployeeService employeeService;
//	@Autowired
//	private MenuService menuService;
	
	public DispatcherController() {
		System.out.println("UserController创建对象了。。。。。。。。。");
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		System.out.println("请求来了。。。。");
//		String uri = req.getRequestURI();
//		String con = uri.split("/")[2];
//		
//		String packageName = this.getClass().getPackage().getName();
//		con = packageName +"."+ con.substring(0, 1).toUpperCase() + con.substring(1) + "Controller";
//		String path = req.getServletContext().getRealPath("/WEB-INF/classes/com/ioc/resource/application.xml");
//		ApplicationContext context = new FileSystemXmlApplicationContext(path);
//		Class<?> cls = Class.forName(con);
//		Object obj = context.getBean(cls);
//		
//		uri = uri.split("/")[3];
//		uri = uri.substring(0, uri.indexOf("."));
//		System.out.println(uri);
//		//反射调用当前对象的其他方法
//		Method m = cls.getMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
//		ModelAndView mv = m.invoke(obj, req, resp);
		return null;
	}
	
	

//	@RequestMapping(value="/test.do")
//	public void test() {
//		userService.test(100);
//		userService.bbb("张三丰");
//		employeeService.loadAllEmployee();
//		menuService.loadAllMenus();
//	}


	


	
	
}
