package org.westwind.guard.dao;

import java.util.List;

import org.westwind.guard.model.Product;
import org.westwind.guard.model.User_Product;

public interface User_ProductDao {
	
	public void addUser_Product(User_Product user_product);
	public int checkUser_Product(int userid,int productid);//检查该产品是否被该用户订阅;
	public void delUser_Product(int userid,int productid);//取消订阅关系;
	//根据userid找到对应的productid;
	public List<User_Product> getProductid(int userid);
}
