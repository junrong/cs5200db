<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.agn4.ds.dao.*" import="edu.neu.cs5200.agn4.ds.model.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World!</h1>
	<%
	UserManager um = new UserManager();
	
	
	User user = new User();
	 
	user.setUsername("alice");
	user.setPassword("124a");
	user.setFirstname("Alice");
	user.setLastname("Lara");
	user.setEmail("alic2@gmail.com");
	user.setDob(null);
	 
	
	um.createUser(user);
	%>
</body>
</html>