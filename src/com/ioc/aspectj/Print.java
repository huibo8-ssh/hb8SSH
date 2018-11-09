package com.ioc.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切面逻辑类
 * @author Administrator
 *
 */
@Aspect
//@Component("Print")
public class Print {
	
	
	/**
	 * 在指定方法执行之前执行此方法
	 */
	@Before("execution(* com.ioc.service.impl.*.*(..))")
	public void aaa() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	/**
	 * 在指定方法执行之后执行此方法
	 */
	@After("execution(* com.ioc.service.impl.*.*(..))")
	public void bbb() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

}
