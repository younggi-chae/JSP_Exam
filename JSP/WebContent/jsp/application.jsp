<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String path = "/message/notice.txt";
	String fullPath = application.getRealPath(path);
	//out.println(fullPath);
	
	try{
	   BufferedReader br = new BufferedReader(new FileReader(fullPath));
	   
	   String str = "";
	   
	   while((str = br.readLine()) != null){
		   out.println(str + "<br>");
	   }
	   
	} catch(Exception e){
		e.printStackTrace();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>