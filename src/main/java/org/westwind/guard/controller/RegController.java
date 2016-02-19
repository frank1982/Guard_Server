package org.westwind.guard.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.westwind.guard.dao.InfoDao;
import org.westwind.guard.dao.UserDao;

@Controller
public class RegController {

	//检查用户名是否重复
	@RequestMapping(value="/checkId.action")
    @ResponseBody()
	public String checkId(@RequestParam String userid){     

		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		UserDao userDao=(UserDao) ctx.getBean("userDao");	
		if(userDao.getIdNum(userid)>0){
			return "yes";
		}
		return "no";
	}
	
	//注册新用户
	@RequestMapping(value="/addUser.action")
	@ResponseBody()
	public String addUser(@RequestParam String username,String pwd){     

		ApplicationContext ctx=null;
	    ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
	    UserDao userDao=(UserDao) ctx.getBean("userDao");	
		userDao.regUser(username, pwd);
		if (userDao.getId(username) > 0){
			return "yes";
		}
		return "no";
	}
	
	//设置新密码
	@RequestMapping(value="/setPwd.action")
	@ResponseBody()
	public String setPwd(@RequestParam String username,String pwd){     

		ApplicationContext ctx=null;
	    ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
	    UserDao userDao=(UserDao) ctx.getBean("userDao");	
		userDao.setPwd(username, pwd);
		System.out.println("set pwd finish");
		if (userDao.checkUser(username, pwd) > 0){
			
			System.out.println("chech new pwd");
			return "yes";
			
		}
		return "no";
	}
}
