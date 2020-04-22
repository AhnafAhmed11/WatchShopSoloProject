<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add an account</title>
</head>
<body>
	<h1>Add a bank account</h1>
<!--<form action="http://localhost:8088/WebAppExercise/RegisterServlet" method="post">-->
  <br>
  <sf:form action="submitAddAccount" method="POST" modelAttribute="bankDetails">
  
		<sf:label path="cardNum">Card Number: </sf:label>
		<sf:input path="cardNum" size="30" />
		<br />
		<sf:label path="sortCode">Sortcode: </sf:label>
		<sf:input path="sortCode" size="30" />
		<br />
		<sf:label path="balance">balance: </sf:label>
		<sf:input path="balance" size="30" />
		<br />
		
		<input type="submit" name="commit" value="submit" />
	</sf:form>
  
 <!-- <button name="submit" value="submit" type="submit"> Submit </button><br> -->
 
  <a href= "login">Logout</a>
</body>
</html>