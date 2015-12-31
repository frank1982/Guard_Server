<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body width="800px">
	<center>
	<!-- <h4>${productList[0].productName}</h4> -->
	<table id="productTable" width="800px" border="1px" cellpadding="0" cellspacing="0">
		<tr><td align="center">id</td><td align="center">productName</td><td align="center">companyName</td>
		<td align="center"></td></tr>
		 <c:forEach items="${productList}" var="item">  
              <tr>  
                        <td align="center">${item.id}</td>  
                        <td align="center">${item.productName}</td>
                        <td align="center">${item.companyName}</td> 
                        <td align="center"><a href="delProduct.action?id=${item.id}">删除</a></td>
              </tr>  
         </c:forEach>  
	</table>
	</center>
</body>
</html>