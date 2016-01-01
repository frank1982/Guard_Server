package org.westwind.guard.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.westwind.guard.dao.ProductDao;
import org.westwind.guard.dao.WordDao;
import org.westwind.guard.model.Product;
import org.westwind.guard.model.Word;

@Controller
public class WordController {
	
	@RequestMapping(value="/addWord.action",method = RequestMethod.POST,produces = "charset=utf-8")
	public ModelAndView addWord(Word word){  //自动拼装成对象;   
		
		try {
			//解决中文乱码
			String strWord=new String(word.getWord().getBytes("iso-8859-1"),"utf-8");
			//System.out.println(strWord);			
			word.setWord(strWord);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//add product to database	
		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		WordDao wordDao=(WordDao) ctx.getBean("wordDao");
		wordDao.addWord(word);
		
        //show result
		int numOfPage=20;
        ModelAndView mav = new ModelAndView("wordTable");//实例化一个VIew的ModelAndView实例  		  
        List<Word> list=wordDao.getWord(0,numOfPage);
        int number=wordDao.getWordNumber();
        mav.addObject("wordList", list);
        mav.addObject("wordTotalNum", number);
        mav.addObject("pageTotalNum", (int)(number/numOfPage)+1);
        mav.addObject("pageNum", 1);
		return mav;  
	}
	
	//getWords
	@RequestMapping(value="/getWord.action",method = RequestMethod.GET,produces = "charset=utf-8")
	public ModelAndView getWord(int offset){  //自动拼装成对象;   

        //show result
		int numOfPage=20;
		//System.out.println(offset);
		ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		WordDao wordDao=(WordDao) ctx.getBean("wordDao");
        ModelAndView mav = new ModelAndView("wordTable");//实例化一个VIew的ModelAndView实例  		  
        List<Word> list=wordDao.getWord(offset,numOfPage);
        int number=wordDao.getWordNumber();
        mav.addObject("wordList", list);
        mav.addObject("wordTotalNum", number);
        mav.addObject("pageTotalNum", (int)(number/numOfPage)+1);
        mav.addObject("pageNum", (int)((offset+1)/numOfPage)+1);
		return mav;  
	}
	
	//delete product
		@RequestMapping(value="/delWord.action")
		public ModelAndView delWord(int id){     

			ApplicationContext ctx=null;
	        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
			WordDao wordDao=(WordDao) ctx.getBean("wordDao");
	        wordDao.delWord(id);
			
	        //show result
	        int numOfPage=5;
	        ModelAndView mav = new ModelAndView("wordTable");//实例化一个VIew的ModelAndView实例  		  
	        List<Word> list=wordDao.getWord(0,numOfPage);
	        int number=wordDao.getWordNumber();
	        mav.addObject("wordList", list);
	        mav.addObject("wordTotalNum", number);
	        mav.addObject("pageTotalNum", (int)(number/numOfPage)+1);
	        mav.addObject("pageNum", 1);
			return mav;  
		}
}
