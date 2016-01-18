package org.westwind.guard.dao;

import org.westwind.guard.model.User;

public interface UserDao {
	
	public int checkUser(String username,String pwd);
	public int getId(String username);
}
