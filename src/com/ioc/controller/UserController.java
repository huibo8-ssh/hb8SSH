package com.ioc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ioc.entity.Menu;
import com.ioc.entity.User;
import com.ioc.service.MenuService;
import com.ioc.service.UserService;

/**
 * 
 * @author Administrator
 *	周末任务：
 *		1）写SpringIOC的原理（组织一段话，通俗易懂，多举例）
 *		2）写SpringAOP的原理
 *		3）写SpringMVC的原理
 *		4）写Spring、SpringMVC、Hibernate整合SSH的做法步骤
 *		5）展示一个类似百度贴吧的帖子列表菜单 完成数据列表展示，
 *			帖子表（主键、标题、内容、发帖人id外键约束、发帖时间、帖子类型int、审阅状态-待审阅和已审阅） 
 *			一对多
 *			评论表（主键、内容、帖子id外键约束、评论时间、评论人id外键约束、审阅状态-待审阅和已审阅） 
 *		点击每一行帖子数据，可以查看该贴子的所有评论列表
 *
 *
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="login.do", method={RequestMethod.POST, RequestMethod.GET})
	public String login(String userName, String userPass, HttpSession session, ModelMap model) {
		System.out.println("login方法....................." + userName + "~" + userPass);
		User user = userService.loadUserByUserName(userName);
		if(null != user) {
			if(userPass.equals(user.getUserPass())) {
				session.setAttribute("currentUser", user);
				List<Menu> menus = menuService.loadMenusByUid(user.getUid());
				session.setAttribute("menus", menus);
				return "index";
			}else {
				model.addAttribute("errorMsg", "登录失败-密码错误！");
				return "login";
			}
		}else {
			model.addAttribute("errorMsg", "登录失败-用户名不存在！");
			return "login";
		}
	}
	
	@RequestMapping(value="reg.do")
	public String reg(Date date, ModelMap model){
		System.out.println("reg方法.....................");
		System.out.println(date.getYear());
		model.addAttribute("aaaa", "中国重庆");
		return "index";
	}
	
	@RequestMapping(value="logout.do")
	public String logout() {
		System.out.println("logout方法.....................");
		return null;
	}

	/**
	 * 异步请求返回json字符串
	 * 对象、数组、集合转为json字符串的方式
	 * 1、阿里巴巴的fastjson
	 * 2、谷歌的gson
	 * 3、jsonlib
	 * 4、spring的jackson
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="loadUserAjax.do")
	public User loadUserAjax()  {
		User user = new User(10, "admin", "123", "张三丰", null);
		return user;
	}
	
}
