package org.westwind.guard.dao;

import org.westwind.guard.model.User;

public interface UserDao {
	
	public int checkUser(String username,String pwd);
	public int getId(String username);
	public int getIdNum(String username);
	public void regUser(String username,String pwd);//注册新用户
	public void setPwd(String username,String pwd);//设置新密码
}
