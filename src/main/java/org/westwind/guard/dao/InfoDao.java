package org.westwind.guard.dao;

import java.util.List;

import org.westwind.guard.model.Info;
import org.westwind.guard.model.Product;
import org.westwind.guard.model.Word;

public interface InfoDao {
	
	public List<Info> getInfo(String productName,int limit,int offset);
	
	//获取最新N条info;
	public List<Info> getNewInfo(int num);
	
	//查找更新的info;
	public List<Info> getNewInfoById(long id);
	
}
