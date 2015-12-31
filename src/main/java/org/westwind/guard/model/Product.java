package org.westwind.guard.model;

public class Product {
	
	private int id;
	private String productName;
	private String companyName;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName=productName;
	}
	public String getCompanyName(){
		return companyName;
	}
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
}
