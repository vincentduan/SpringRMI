package com.test.ddy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.ddy.model.User;
import com.test.ddy.service.IRMIService;
import com.test.ddy.service.IUserService;

/**
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private IUserService userService;
	

	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 * @author duandingyang
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		List<User> users = userService.getUserList();
		request.setAttribute("users", users);
		return "index";
	}
	@RequestMapping(value = "/getRMI", method = RequestMethod.GET)
	public String getRMI(HttpServletRequest request) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(  
                "classpath:RmiServer.xml"); 
		User user  = new User();
		user.setName("vincent_duan");
		user.setAddress("shijingshan");
		IRMIService hs = (IRMIService) ctx.getBean("iRMIService");  
		String temp = hs.getUserName("my vincent ",user);
		System.out.println(temp);
		return "index";
	}

	/**
	 * 参数绑定，查询一条数据
	 * 
	 * @param userId
	 * @param request
	 * @return
	 * @author duandingyang
	 */
	@RequestMapping(value = "/showUser/{userId}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("userId") Integer userId, HttpServletRequest request) {
		User user = userService.getUserById(userId);
		request.setAttribute("user", user);
		return "index";
	}

	@RequestMapping(value = "/delUser/{userId}", method = RequestMethod.GET)
	public String delUserById(@PathVariable("userId") Integer userId, HttpServletRequest request) {
		userService.delUserById(userId);
		request.setAttribute("success", "success");
		return "index";
	}
	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	public String charts(HttpServletRequest request){
		return "charts2";
	}
}
