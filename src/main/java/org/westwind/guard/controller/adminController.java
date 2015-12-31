package org.westwind.guard.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.westwind.guard.dao.AdminDao;
import org.westwind.guard.model.Admin;


@Controller
public class adminController {
	
	@RequestMapping(value="/login.action",method = RequestMethod.POST)
	public String login(@RequestParam String name,@RequestParam String pwd){

        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        AdminDao adminDao=(AdminDao) ctx.getBean("adminDao");
        Admin admin=new Admin();
        admin.setName(name);
        admin.setPwd(pwd);
        Admin newAdmin=new Admin();
        newAdmin=adminDao.getAdmin(admin);
        
        if(newAdmin != null){
        	
        	System.out.println("login success");
        	return "main";
        	
        }else{
        	System.out.println("login failture");
        	return "loginError";
        }
        
        
	}
}
