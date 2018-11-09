package com.ioc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyListener implements ServletContextListener{

	//容器
	public static Map<String, Object> map = new HashMap<String, Object>();
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		InputStream in = null;
		try {
			in = new FileInputStream(event.getServletContext().getRealPath("/WEB-INF/mvc-servlet.xml"));
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();//得到根节点对象
			List<Element> list = root.elements("bean");
			List<Element> propertyEles;
			String id = null, className = null;
			Class<?> cls = null;
			String propertyName, propertyRef;
			Object obj = null;
			for(Element e : list) {
				className = e.attributeValue("class");
				id = e.attributeValue("id");
				//id = id == null || "".equals(id) ? e.attributeValue("name") : id;
				//id = id == null || "".equals(id) ? className.substring(className.lastIndexOf(".")+1) : id;
				obj = Class.forName(className).newInstance();
				map.put(id, obj);
			}
			
			Field injectionField;
			//依赖注入
			for(Element e : list) {
				id = e.attributeValue("id");
				//id = id == null || "".equals(id) ? e.attributeValue("name") : id;
				//id = id == null || "".equals(id) ? className.substring(className.lastIndexOf(".")+1) : id;
				obj = map.get(id);
				cls = obj.getClass();
				//查找property标签
				propertyEles = e.elements("property");
				for(Element propertyEle : propertyEles) {
					propertyName = propertyEle.attributeValue("name");
					propertyRef = propertyEle.attributeValue("ref");
					injectionField = cls.getField(propertyName);
					injectionField.setAccessible(true);
					injectionField.set(obj, map.get(propertyRef));
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
