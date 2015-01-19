<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- enctype creates binding issues - http://stackoverflow.com/questions/26759095/form-data-not-binding-with-spring-controller-annotation-getting-new-object-after --%>
	<%-- <sf:form action="register" method="POST" enctype="multipart/form-data" modelAttribute="user"> --%>
	<sf:form action="register" method="POST" modelAttribute="user">
		<label>User Name:</label>
		<sf:input path="userName" id="userName" size="20"/>
		<sf:errors path="userName" cssClass="error" />
		
		<br />
		<label>Password:</label>
		<sf:password path="passphrase" id="password" size="20"/>
		<sf:errors path="passphrase" cssClass="error" />
		
		<br />
		<label>Email:</label>
		<sf:input path="email" id="email" size="20"/>
		<sf:errors path="email" cssClass="error" />
		
		<br />
		<label>Image:</label>
		<input type="file" id="avatar" size="20"/>
		<sf:errors path="avatar" cssClass="error" />
		
		<br />
		<input type="submit" id="submit" value="Register" size="20"/>		
	</sf:form>
	<!-- Hello World!! -->
</body>
</html>