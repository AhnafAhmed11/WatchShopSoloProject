<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration page</title>
</head>
<body>
<!--<img alt="FDM logo" src="H:\\fdm-logo-black.jpg" style="width:125px;height:95px;"><br>-->

<h1>Welcome to the registration page</h1>
<!--<form action="http://localhost:8088/WebAppExercise/RegisterServlet" method="post">-->
  <br>
  <sf:form action="submitRegister" method="POST" modelAttribute="customer">
  
		<sf:label path="firstname"><b>Firstname: </b></sf:label>
		<sf:input path="firstname" size="30" />
		<br />
		<sf:label path="lastname"><b>Lastname: </b></sf:label>
		<sf:input path="lastname" size="30" />
		<br />
		<sf:label path="address"><b>Address: </b></sf:label>
		<sf:input path="address" size="30" />
		<br />
		<sf:label path="phone"><b>Phone number: </b></sf:label>
		<sf:input path="phone" size="30" />
		<br />
		<sf:label path="username"><b>Username: </b></sf:label>
		<sf:input path="username" size="30" />
		<br />
		<sf:label path="password"><b>Password: </b></sf:label>
		<sf:input path="password" size="30" />
		<br />
		
		<input type="submit" name="commit" value="submit" />
	</sf:form><br>
	
	<B>${requestScope.message}</B><br><br>
  
 <!-- <button name="submit" value="submit" type="submit"> Submit </button><br> -->
 
  <a href= "login">Return to Login</a>
<!--</form>-->
</body>
</html>