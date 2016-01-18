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
    public String getMyNews(@RequestParam String userid){

        System.out.println("id is: "+userid);
        //首先根据userid找到对应的已订阅的productid;
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		User_ProductDao user_productDao=(User_ProductDao) ctx.getBean("user_productDao");
		ProductDao productDao=(ProductDao) ctx.getBean("productDao");
		InfoDao infoDao=(InfoDao) ctx.getBean("infoDao");
		
		List<User_Product> list=user_productDao.getProductid(Integer.parseInt(userid));//根据userid找到productid list;
		
		
		JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
		
		//下面要根据productid筛选info
		for(int i = 0; i < list.size(); i++){

			int productid=list.get(i).getProductid();
			System.out.println(productid);
			//根据productid找到productName;
        }
		
        
        
        /*
        JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
        long initId=Long.valueOf(id).longValue(); 
        Random random =new Random();
        int num=random.nextInt(4)+1;//[0,4)
        for(int i=0;i<num;i++){
        	initId++;
        	tmpJson.put("id", String.valueOf(initId));
        	tmpJson.put("platformName", "陆金所");
        	Date nowTime = new Date(System.currentTimeMillis());
        	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	String retStrFormatNowDate = sdFormatter.format(nowTime);
        	//System.out.println(retStrFormatNowDate);
        	tmpJson.put("insertTime", retStrFormatNowDate);
        	tmpJson.put("title", "陆金所提现出现大面积延迟?"+String.valueOf(initId));
        	JsonArray.add(tmpJson);
        }
		
        return JsonArray.toString();
        */
        return "success";
    } 
	
	
	@RequestMapping(value="/getPlatformNum.action")
    @ResponseBody()
	public String getProductNum(){     
		
        System.out.println("getProductNum"); 
        return "1256";
	}
	
	//根据id查询最新数据
    @RequestMapping(value="/getNewsById.action",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody()
    public String findNewsById(@RequestParam String id){

        //System.out.println("id is: "+id);
        JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
        long initId=Long.valueOf(id).longValue(); 
        Random random =new Random();
        int num=random.nextInt(4)+1;//[0,4)
        for(int i=0;i<num;i++){
        	initId++;
        	tmpJson.put("id", String.valueOf(initId));
        	tmpJson.put("platformName", "陆金所");
        	Date nowTime = new Date(System.currentTimeMillis());
        	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	String retStrFormatNowDate = sdFormatter.format(nowTime);
        	//System.out.println(retStrFormatNowDate);
        	tmpJson.put("insertTime", retStrFormatNowDate);
        	tmpJson.put("title", "陆金所提现出现大面积延迟?"+String.valueOf(initId));
        	JsonArray.add(tmpJson);
        }

        return JsonArray.toString();
    } 
    
    //查询最新数据
    @RequestMapping(value="/getNews.action",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody()
    public String findNews(){

        JSONArray JsonArray = new JSONArray();
        JSONObject tmpJson = new JSONObject();
        long initId= 1000000000;
        for(int i=0;i<4;i++){
        	tmpJson.put("id", String.valueOf(initId));
        	tmpJson.put("platformName", "陆金所");
        	Date nowTime = new Date(System.currentTimeMillis());
        	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	String retStrFormatNowDate = sdFormatter.format(nowTime);
        	tmpJson.put("insertTime", retStrFormatNowDate);
        	tmpJson.put("title", "陆金所提现出现大面积延迟?"+String.valueOf(initId));
        	JsonArray.add(tmpJson);
        	initId++;
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
