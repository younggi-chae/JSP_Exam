<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%
	request.setCharacterEncoding("utf8");
	
int num1 = 0;
	int num2 = 0;
	if (request.getParameter("num1") != null) {
		num1 = Integer.parseInt(request.getParameter("num1"));
		num2 = Integer.parseInt(request.getParameter("num2"));
	}	
	
	if(request.getParameterValues("hobby") != null){	
	String hobby[] = request.getParameterValues("hobby");
	for(int i = 0; i < hobby.length; i++){
		out.print(hobby[i] + ", ");
	  }
	}
	
	int result = num1 + num2;
	request.setAttribute("result", result);
		
%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="server.jsp" method="post">
		숫자 1: <input type="text" name="num1"><br> 
		숫자 2: <input type="text" name="num2"><br> 
		취미 : <input type="checkbox" name="hobby" value="야구">야구
		<input type="checkbox" name="hobby" value="농구">농구
		<input type="checkbox" name="hobby" value="축구">축구
		<input type="submit" value="계산">
	</form>

	결과 :
	<b>${result}</b>
</body>
</html>