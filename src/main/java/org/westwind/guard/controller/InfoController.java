package org.westwind.guard.controller;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import org.westwind.guard.dao.InfoDao;
import org.westwind.guard.dao.ProductDao;
import org.westwind.guard.dao.User_ProductDao;
import org.westwind.guard.dao.WordDao;
import org.westwind.guard.model.Info;
import org.westwind.guard.model.Product;
import org.westwind.guard.model.User_Product;
import org.westwind.guard.model.Word;

@Controller
public class InfoController {

	@RequestMapping("/sayhi.action")
	public String print(ModelMap model){

        System.out.println("mode and view...");          
        return "hello";
	}
	
	//根据userid,limit,offset从客户端发起查询
    @RequestMapping(value="/getMyNews.action",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody()
    public String getMyNews(@RequestParam String userid,String limit,String offset){
    	
    	int myLimit=Integer.parseInt(limit);
    	int myOffset=Integer.parseInt(offset);
    	
        //首先根据userid找到对应的已订阅的productid;
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		User_ProductDao user_productDao=(User_ProductDao) ctx.getBean("user_productDao");
		ProductDao productDao=(ProductDao) ctx.getBean("productDao");
		InfoDao infoDao=(InfoDao) ctx.getBean("infoDao");
		
		List<User_Product> list=user_productDao.getProductid(Integer.parseInt(userid));//根据userid找到productid list;
		System.out.println("list.size:"+list.size());
		
		JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
		
		//下面要根据productid筛选info
        if(list.size()>0){//查到订阅的产品信息

        	for(int i = 0; i < list.size(); i++){

    			int productid=list.get(i).getProductid();
    			//System.out.println(productid);
    			//根据productid找到productName;
    			String productName=productDao.getProductName(productid);
    			//System.out.println(productName);
    			//下面要根据productName找info
    			List<Info> infoList=infoDao.getInfo(productName, myLimit, myOffset);
    			//System.out.println("infoList.size:"+infoList.size());
    			for(int j=0;j<infoList.size();j++){
    				tmpJson.put("id", infoList.get(j).getId());
    	        	tmpJson.put("productName", infoList.get(j).getProductName());
    	        	tmpJson.put("title", infoList.get(j).getTitle());
    	        	tmpJson.put("descript", infoList.get(j).getDescript());
    	        	tmpJson.put("link", infoList.get(j).getLink());
    	        	Date infoTime=infoList.get(j).getInfoTime();
    	            String infoTimeStr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(infoTime);
    	        	tmpJson.put("infoTime", infoTimeStr);
    	        	//System.out.println(updateTimeStr);
    	        	JsonArray.add(tmpJson);
    			}
            }
        }

        return JsonArray.toString();
    } 
	
	//客户端获取当前监控的产品总数
	@RequestMapping(value="/getPlatformNum.action")
    @ResponseBody()
	public String getProductNum(){     
		
        System.out.println("getProductNum"); 
        ApplicationContext ctx=null;
		ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		ProductDao productDao=(ProductDao) ctx.getBean("productDao");
		int num=productDao.getProductNum();
		String numStr=String.valueOf(num);
        return numStr;
	}
	
	//根据id查询最新数据
    @RequestMapping(value="/getNewsById.action",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody()
    public String findNewsById(@RequestParam String id){

        //System.out.println("id is: "+id);
        JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
        long initId=Long.valueOf(id).longValue(); 
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		InfoDao infoDao=(InfoDao) ctx.getBean("infoDao");		

    	List<Info> infoList=infoDao.getNewInfoById(initId);
        for(int j=infoList.size()-1;j>=0;j--){
    		tmpJson.put("id", String.valueOf(infoList.get(j).getId()));
    	    tmpJson.put("platformName", infoList.get(j).getProductName());
    	    tmpJson.put("title", infoList.get(j).getTitle());
    	    Date infoTime=infoList.get(j).getInfoTime();
    	    String infoTimeStr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(infoTime);
    	    tmpJson.put("insertTime", infoTimeStr);
    	    JsonArray.add(tmpJson);
    	}
        
        return JsonArray.toString();
    } 
    
    //客户端第一次查询最新数据
    @RequestMapping(value="/getNews.action",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody()
    public String findNews(){

    	//首先根据userid找到对应的已订阅的productid;
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		InfoDao infoDao=(InfoDao) ctx.getBean("infoDao");		
		JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
    	List<Info> infoList=infoDao.getNewInfo(4);//最新4条;
    	for(int j=infoList.size()-1;j>=0;j--){
    		tmpJson.put("id", String.valueOf(infoList.get(j).getId()));
    	    tmpJson.put("platformName", infoList.get(j).getProductName());
    	    tmpJson.put("title", infoList.get(j).getTitle());
    	    Date infoTime=infoList.get(j).getInfoTime();
    	    String infoTimeStr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(infoTime);
    	    tmpJson.put("insertTime", infoTimeStr);
    	    JsonArray.add(tmpJson);
    	}
		
        return JsonArray.toString();
        
    } 
    
    //删除订阅信息
    @RequestMapping(value="/delProductGuard.action",method = RequestMethod.GET, produces = "charset=utf-8")
    @ResponseBody()
    public String delProductGuard(@RequestParam String userid,String productid){
    	
		int userId=Integer.parseInt(userid);
		int productId=Integer.parseInt(productid);
		User_Product user_product=new User_Product();
		user_product.setUserid(userId);
		user_product.setProductid(productId);
		
		//add product to database	
		ApplicationContext ctx=null;
		ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		User_ProductDao user_productDao=(User_ProductDao) ctx.getBean("user_productDao");
		user_productDao.delUser_Product(userId,productId);
    	
        return "success";
    } 
    
    //增加订阅信息
    @RequestMapping(value="/setProductGuard.action",method = RequestMethod.GET, produces = "charset=utf-8")
    @ResponseBody()
    public String setProductGuard(@RequestParam String userid,String productid){
    	
    	//productname = new String(productid.getBytes("iso8859-1"),"utf-8");
		System.out.println("productName is:"+productid);
		System.out.println("userId is:"+userid);
		
		int userId=Integer.parseInt(userid);
		int productId=Integer.parseInt(productid);
		User_Product user_product=new User_Product();
		user_product.setUserid(userId);
		user_product.setProductid(productId);
		
		//add product to database	
		ApplicationContext ctx=null;
		ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		User_ProductDao user_productDao=(User_ProductDao) ctx.getBean("user_productDao");
		user_productDao.addUser_Product(user_product);
    	
        return "success";
    } 
}
