<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	OutLaw.jsp
	<%
		File file = new File(".");
		String[] list = file.list();
		for(String fileName:list){
			out.println("file name = "+fileName+"</br>");
		}
	%>
	<br>
	<br>
</body>
</html>