package org.westwind.guard.dao;

import java.util.Date;
import java.util.List;

import org.westwind.guard.model.Admin;
import org.westwind.guard.model.Product;

public interface ProductDao {
	
	public List<Product> getProduct();
	public void addProduct(Product product);
	public void delProduct(int id);
	public Date getUpdateTime();
	public String getProductName(int productid);//根据productid找到productName;
	//获取所有产品数量
	public int getProductNum();
}
