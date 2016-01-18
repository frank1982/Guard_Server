package org.westwind.guard.model;

import java.math.BigInteger;
import java.util.Date;

public class Info {
	
	private BigInteger id;
	private String productName;
	private String title;
	private String descript;
	private String link;
	private Date infoTime;
	
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setProductName(String productName){
		this.productName=productName;
	}
	public String getProductName(){
		return productName;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setDescript(String descript){
		this.descript=descript;
	}
	public String getDescript(){
		return descript;
	}
	public void setLink(String link){
		this.link=link;
	}
	public String getLink(){
		return link;
	}
	public void setInfoTime(Date infoTime){
		this.infoTime=infoTime;
	}
	public Date getInfoTime(){
		return infoTime;
	}
	
}
