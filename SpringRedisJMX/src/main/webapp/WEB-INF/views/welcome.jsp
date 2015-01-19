<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome ${user.userName}!</h1>
	Details:
	<table>
		<tr><td>email:</td><td>${user.email}</td></tr>
		<tr><td>age:</td><td>${user.age}</td></tr>
	</table>
	<sf:form action="delete" method="POST" modelAttribute="user">
		<input type="submit" id="submit" value="DeleteMe" size="20"/>
	</sf:form>
</body>
</html>