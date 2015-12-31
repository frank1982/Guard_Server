<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
li{
float:left;
margin-left:30px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<ul>
<li><a href="getProduct.action" target="tableFrame">查询全部产品</a></li>
<!-- <li><a href="addProduct.action?productName=frank&companyName=dddd" target="tableFrame">新增产品</a></li>  -->
<li><a href="addProduct.jsp" target="tableFrame">新增产品</a></li>

</ul>
</body>
</html>