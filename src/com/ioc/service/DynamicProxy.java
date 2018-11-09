package com.ioc.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.ioc.service.impl.UserServiceImpl;

/**
 * 
 * @author Administrator
 * 动态代理的实现方式：
 *	1、JDK
 *		1）写一个类实现InvocationHandler接口，并且持有一个被代理对象作为属性，实现InvocationHandler接口
 *		目的是为了接收调用代理对象的方法的实参列表
 *		2）重写InvocationHandler接口中的invoke方法（第一个参数是代理对象，第二个参数是代理对象调用的方法，
 *		第三个参数是调用方法时传入的实参列表）
 *		3）在重写的invoke方法中调用被代理对象的同名方法，前后可以任意添加其他业务逻辑代码（例如：开启事务和事务提交等）
 *		4）利用Proxy.newProxyInstanse方法获取代理对象，获取到的代理对象与被代理对象实现了相同的接口（内部方法相同）
 *		但方法内部实现不同（多添加了一些其他的业务逻辑）
 *	2、cglib
 *	3、aspectj
 */
public class DynamicProxy implements InvocationHandler{
	
	//被代理对象
	private Object target;
	
	public DynamicProxy(Object target) {
		this.target = target;
	}

//	public static <T> T newInstanse(Object target, Class<T> proxyClass) {
//		Class<?> cls = target.getClass();				//通过target对象获取Class对象
//		String targetClassName = cls.getName();			//获取target对象的类全名
//		targetClassName = targetClassName.substring(targetClassName.lastIndexOf(".")+1);
//		Class<?>[] interfaces = cls.getInterfaces();	//获取target对象的类实现过的所有接口
//		String packageName = cls.getPackage().getName();//获取target对象的类所在的包名称
//		
//		if(interfaces == null || interfaces.length == 0) {
//			throw new RuntimeException("被代理对象的类必须至少实现一个接口。。。。。。。");
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("package ").append(packageName).append(";\r\n\r\n");
//		
//		//动态生成一个代理类名称
//		String genericClassName = targetClassName + "_" + new Date().getTime();
//		
//		sb.append("public class ").append(genericClassName).append(" implements ");
//		
//		for(Class<?> c : interfaces)  {
//			sb.append(c.getName()).append(",");
//		}
//		sb = new StringBuilder(sb.substring(0, sb.length()-1));
//		sb.append("{\r\n\r\n");
//		
//		sb.append("    private ").append(targetClassName).append(" target;\r\n\r\n");
//		sb.append("    public void setTarget("+targetClassName+" target){\r\n");
//		sb.append("        this.target = target;\r\n");
//		sb.append("    }\r\n\r\n");
//		sb.append("    public "+targetClassName+" getTarget(){\r\n");
//		sb.append("        return target;\r\n");
//		sb.append("    }\r\n\r\n");
//		
//		//添加实现接口的所有抽象方法的源代码
//		Method[] ms = null;
//		StringBuilder methodDefine = null;
//		int i = 0;
//		for(Class<?> c : interfaces)  {
//			ms = c.getMethods();
//			for(Method m : ms) {
//				methodDefine = new StringBuilder();
//				i = 0;
//				methodDefine.append("    public " + m.getReturnType().getName() + " " + m.getName()+"(");
//				for(Class<?> paramCls : m.getParameterTypes()) {
//					methodDefine.append(paramCls.getName() + " arg" + i + ",");
//					i++;
//				}
//				if(methodDefine.toString().endsWith(",")) 
//					methodDefine = new StringBuilder(methodDefine.substring(0, methodDefine.length()-1));
//				methodDefine.append("){\r\n");
//				methodDefine.append("        System.out.println(\"你好\");\r\n");
//				methodDefine.append("        target.").append(m.getName()).append("();\r\n");
//				methodDefine.append("        System.out.println(\"大家好\");\r\n");
//				methodDefine.append("    }\r\n\r\n");
//				sb.append(methodDefine);
//			}
//		}
//		sb.append("}");
//		
//		Writer writer = null;
//		try {
//			String filePath = "build/classes/" + packageName.replace(".", "/") + "/" + genericClassName+".java";
//			File file = new File(filePath);
//			writer = new FileWriter(file);
//			writer.write(sb.toString());
//			writer.flush();
//			writer.close();
//			
//			
//			//动态编译一个Java类文件
//			JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
//			DiagnosticCollector<JavaFileObject> diagn = new DiagnosticCollector<JavaFileObject>();
//			StandardJavaFileManager manager = complier.getStandardFileManager(diagn, null, null);
//			List<File> files = new ArrayList<File>();
//			files.add(file);
//			Iterable<? extends JavaFileObject> it = manager.getJavaFileObjectsFromFiles(files);
//			JavaCompiler.CompilationTask task = complier.getTask(null, manager, diagn, null, null, it);
//			boolean b = task.call();
//			manager.close();
//			//if(b) file.delete();
//			
//			//类加载 实例化
//			Class<?> clsProxy = Class.forName(packageName + "." + genericClassName);
//			T t = (T)clsProxy.newInstance();
//			clsProxy.getMethod("setTarget", cls).invoke(t, target);
//			return t;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	public static void main(String[] args) {
		
		UserService us = new UserServiceImpl();
		//DynamicProxy.newInstanse(us, UserService.class)
		DynamicProxy h = new DynamicProxy(us);
		UserService proxy = (UserService)Proxy.newProxyInstance(us.getClass().getClassLoader(), us.getClass().getInterfaces(), h);
		proxy.loadUserByUserName("admin");
		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("你好");
		Object result = method.invoke(target, args);
		System.out.println("大家好");
		return result;
	}
}
