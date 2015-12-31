<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<table id="wordTable" width="800px" border="1px" cellpadding="0" cellspacing="0">
		<tr><td align="center">id</td><td align="center">word</td><td align="center"></td></tr>
		 <c:forEach items="${wordList}" var="item">  
              <tr>  
                        <td align="center">${item.id}</td>  
                        <td align="center">${item.word}</td>
                        <td align="center"><a href="delWord.action?id=${item.id}">删除</a></td>
              </tr>  
         </c:forEach>  
	</table>
	<table>
	<tr>
		<td align="center"><a>合计&nbsp;${wordTotalNum}条</a></td>
		<td align="center"><a>共&nbsp;${pageTotalNum}页</a></td>
		<td align="center"><a>上一页</a></td>
		<td align="center"><a>第${pageNum}页</a></td>
		<td align="center"><a>下一页</a></td>
	</tr>
	</table>
	</center>
</body>
</html>