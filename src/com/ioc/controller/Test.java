package com.ioc.controller;

/**
 * 
 * @author Administrator
 *	Spring的IOC/DI：
 *	IOC（Inversion Of Control）叫做控制反转，使用spring框架之前，若我们需要使用某个类的对象时，必须使用new
 *	关键字创建对象（一般写成私有属性并且实例化），
 *	1）Spring的IOC是利用监听器监听服务器启动，读取配置文件（SpringMVC的
 *	配置文件默认名称为servlet-name的值加-servlet.xml，且默认放在WEB-INF目录下；SpringIOC的配置文件默认名称为
 *	applicationContext.xml，默认也是放WEB-INF目录下）中的所有配置（bean和context:component-scan标签），
 *	获取bean标签的id或name属性、class属性，以id或name属性值为键，以class属性值的类全名利用反射技术创建的对象为值，
 *	形成键值对保存在一个类似HashMap的集合中；context:component-scan标签的base-package属性值是一个包名称，
 *	意思就是去自动扫描指定的包（包括其子包...）以内的所有加了@Controller、@Service、@Repository、@Component
 *	这四种注解之一的类，以类名称为键，以利用反射技术创的建对象为值，形成键值对保存在一个类似HashMap的集合中。
 *	2）DI（Dependency Injection）叫做依赖注入，通过第一步创建好的对象全都保存在HashMap的集合中，但是某些对象会持
 *	有其他对象为私有属性，Spring容器会自动给这些私有属性填入相应的对象进行赋值（利用反射技术调用这些属性的set方法）。
 *	读取bean标签内部所有property标签的name属性和ref属性（name属性值必须与当前bean类中的属性名称保持一致，ref属性
 *	关联另一个bean标签的id或name属性值）
 *	3）创建对象的时机提前到了服务器启动的时候，提高了访问效率(访问时不需要创建对象了，只需通过键到容器取出对象即可调用方法)
 *	4）类中的私有属性（如果是其他类型的对象的话）就不需要使用new创建对象了，这样做降低了对象之间的耦合度，做到了高内聚、低
 *	耦合。
 */
public class Test {
	
	public Test() {
		System.out.println("Test创建对象了。。。。。。。。。");
	}
	
	public void aaa() {
		System.out.println("Test对象的aaa被调用了");
	}

}
