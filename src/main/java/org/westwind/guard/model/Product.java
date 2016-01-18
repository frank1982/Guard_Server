package org.westwind.guard.model;

import java.util.Date;

public class Product {
	
	private int id;
	private String productName;
	private String companyName;
	private Date updateTime;
	
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
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setInsertTime(Date updateTime){
		this.updateTime=updateTime;
	}
}
