package org.westwind.guard.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.westwind.guard.dao.UserDao;
import org.westwind.guard.dao.WordDao;

@Controller
public class LoginController {
	
	//login
	@RequestMapping(value="/userLogin.action")
    @ResponseBody()
	public String login(@RequestParam String username,String pwd){     

        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		UserDao userDao=(UserDao) ctx.getBean("userDao");
		int i=userDao.checkUser(username, pwd);
		String msg="";
		if(i>0){
			
			int id=userDao.getId(username);
			
			msg=String.valueOf(id);
			System.out.println("login id is:"+msg);
			System.out.println(username+" login success"); 
		}else{
			msg="error";
			System.out.println(username+" login error"); 
		}
        return msg;
	}
}
