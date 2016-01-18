package org.westwind.guard.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.westwind.guard.dao.AdminDao;
import org.westwind.guard.dao.ProductDao;
import org.westwind.guard.dao.User_ProductDao;
import org.westwind.guard.model.Admin;
import org.westwind.guard.model.Product;


@Controller
public class productManage {
	
	//获取最新产品insertTime
	@RequestMapping(value="/getUpdateTime.action")
	@ResponseBody()
	public String getUpdateTime(){    
		
		System.out.println("getUpdateTime");
		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ProductDao productDao=(ProductDao) ctx.getBean("productDao");
        Date updateTime=productDao.getUpdateTime();
        //System.out.println((new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(updateTime));
        String updateTimeStr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
        return updateTimeStr;
	}
	
	//后台查询
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
	
	//app 查询产品明细,废弃
	@RequestMapping(value="/getProductJsondata.action", produces = "application/json; charset=utf-8")
	@ResponseBody()
	public String getProductJsondata(){    
		
		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ProductDao productDao=(ProductDao) ctx.getBean("productDao");
        List<Product> list=productDao.getProduct();
        
        //net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(list);
        JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
        for(int i=0;i<list.size();i++){
       
        	tmpJson.put("id", list.get(i).getId());
        	tmpJson.put("productName", list.get(i).getProductName());
        	tmpJson.put("companyName", list.get(i).getCompanyName());
        	Date updateTime=list.get(i).getUpdateTime();
            String updateTimeStr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
        	tmpJson.put("updateTime", updateTimeStr);
        	System.out.println(updateTimeStr);
        	JsonArray.add(tmpJson);
        }
        return JsonArray.toString();
	}
	
	//add product,管理后台
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
	
	//app 查询产品明细New,增加了已被订阅的状态
		@RequestMapping(value="/getProductJsondataAndStatus.action", produces = "application/json; charset=utf-8")
		@ResponseBody()
		public String getProductJsondataAndStatus(@RequestParam String userid){    
			
			System.out.println("userid is: "+userid);
			ApplicationContext ctx=null;
	        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
	        ProductDao productDao=(ProductDao) ctx.getBean("productDao");
	        List<Product> list=productDao.getProduct();
	        
	        //net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(list);
	        JSONArray JsonArray = new JSONArray();
	        JSONObject tmpJson = new JSONObject();
	        for(int i=0;i<list.size();i++){
	       
	        	tmpJson.put("id", list.get(i).getId());
	        	tmpJson.put("productName", list.get(i).getProductName());
	        	tmpJson.put("companyName", list.get(i).getCompanyName());
	        	Date updateTime=list.get(i).getUpdateTime();
	            String updateTimeStr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
	        	tmpJson.put("updateTime", updateTimeStr);
	        	System.out.println(updateTimeStr);
	        	System.out.println("userid is: "+userid);
	        	int useridInt=Integer.parseInt(userid);
	        	System.out.println("userid is: "+useridInt);
	        	//是否被该用户订阅的状态
	        	User_ProductDao user_productDao=(User_ProductDao) ctx.getBean("user_productDao");
	    		int isProductGuard=user_productDao.checkUser_Product(useridInt, list.get(i).getId());
	        	if(isProductGuard>0){
	        		//System.out.println("on cell is gaurd on server");
	        		tmpJson.put("isGuard", "isGuard");
	        	}else{
	        		//System.out.println("on cell not gaurd on server");
	        		tmpJson.put("isGuard", "notGuard");
	        	}
	        	JsonArray.add(tmpJson);
	        }
	        return JsonArray.toString();
		}
}
