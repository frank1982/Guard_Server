<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- use spring form tag -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请登录</title>
</head>
<body>
<center>
<form action="/guard/login.action" method="post">
		<table>
			<tr>
				<td  Name:></td><td><input type="text" id="name" name="name"/></td>
			</tr>
			<tr>
				<td  pwd:nbsp;></td><td><input type="text" id="pwd" name="pwd"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交"/></td>
			</tr>
		</table>
	</form>
</center>
	
</body>
</html>