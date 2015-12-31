package org.westwind.guard.controller;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InfoController {

	@RequestMapping("/sayhi.action")
	public String print(ModelMap model){

        System.out.println("mode and view...");          
        return "hello";
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
        	System.out.println(retStrFormatNowDate);
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
}
