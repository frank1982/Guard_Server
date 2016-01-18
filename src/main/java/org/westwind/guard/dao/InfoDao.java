package org.westwind.guard.dao;

import java.util.List;

import org.westwind.guard.model.Info;
import org.westwind.guard.model.Product;
import org.westwind.guard.model.Word;

public interface InfoDao {
	
	public List<Info> getInfo(String productName,int limit,int offset);
}
