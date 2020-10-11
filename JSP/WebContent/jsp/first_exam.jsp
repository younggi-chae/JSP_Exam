<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>JSP first exam</h1>
		<%
			Calendar data = Calendar.getInstance();
			SimpleDateFormat today = 
					new SimpleDateFormat("yyyy년 MM월 dd일");
		%>
		
		오늘은 <%= today.format(data.getTime()) %> 입니다.
		
</body>
</html>