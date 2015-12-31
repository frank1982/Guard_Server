<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<style type="text/css">
li{
float:left;
margin-left:30px;
margin-top:30px;
}
</style>
</head>
<body>
	<div id="menu" style="position:absolute;width:200px;top:50px;left:50px;">
		<ul>
			<li><a href="productAction.jsp" target="actionFrame" >产品管理</a></li>
			<li><a href="http://www.sina.com.cn">用户管理</a></li>
			<li><a href="http://www.sina.com.cn">信息管理</a></li>
			<li><a href="http://www.sina.com.cn">推送管理</a></li>
			<li><a href="wordAction.jsp" target="actionFrame">搜索关键字</a></li>
		</ul>
	</div>
	<div id="actionDiv" name="actionDiv" style="position:absolute;width:1000px;top:50px;left:300px;height:100px">
		<iframe name="actionFrame" height="600px" width="800px" frameborder="no" border="0"></iframe>
	</div>
	<div id="tableDiv" name="tableDiv" width="800px" style="position:absolute;width:1000px;top:180px;left:300px;height:600px">
		<iframe name="tableFrame" height="600px" width="100%" frameborder="no" border="0"></iframe>
	</div>
</body>
</html>