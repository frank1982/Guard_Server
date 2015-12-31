package org.westwind.guard.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.westwind.guard.dao.AdminDao;
import org.westwind.guard.dao.ProductDao;
import org.westwind.guard.model.Admin;
import org.westwind.guard.model.Product;


@Controller
public class productManage {
	
	@RequestMapping(value="/getProduct.action")
	public ModelAndView getProduct(){     
		
		System.out.println("getProduct.action");
		ModelAndView mav = new ModelAndView("productTable");//实例化一个VIew的ModelAndView实例  		  
		
		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ProductDao productDao=(ProductDao) ctx.getBean("productDao");
        List<Product> list=productDao.getProduct();
        mav.addObject("productList", list);
		return mav;  
	}
	
	//add product
	@RequestMapping(value="/addProduct.action",method = RequestMethod.POST,produces = "charset=utf-8")
	public ModelAndView addProduct(Product product){     
		
		try {
			//解决中文乱码
			String strProductName=new String(product.getProductName().getBytes("iso-8859-1"),"utf-8");
			String strCompanyName=new String(product.getCompanyName().getBytes("iso-8859-1"),"utf-8");
			
			System.out.println(strProductName);
			System.out.println(strCompanyName);
			
			product.setProductName(strProductName);
			product.setCompanyName(strCompanyName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//add product to database	
		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		ProductDao productDao=(ProductDao) ctx.getBean("productDao");
        productDao.addProduct(product);
		
        //show result
        ModelAndView mav = new ModelAndView("productTable");//实例化一个VIew的ModelAndView实例  		  
        List<Product> list=productDao.getProduct();
        mav.addObject("productList", list);
		return mav;  
	}
	
	//delete product
	@RequestMapping(value="/delProduct.action")
	public ModelAndView delProduct(int id){     

		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		ProductDao productDao=(ProductDao) ctx.getBean("productDao");
        productDao.delProduct(id);
		
        //show result
        ModelAndView mav = new ModelAndView("productTable");//实例化一个VIew的ModelAndView实例  		  
        List<Product> list=productDao.getProduct();
        mav.addObject("productList", list);
		return mav;  
	}
}
