package org.westwind.guard.dao;

import java.util.List;

import org.westwind.guard.model.Admin;
import org.westwind.guard.model.Product;

public interface ProductDao {
	
	public List<Product> getProduct();
	public void addProduct(Product product);
	public void delProduct(int id);
}
