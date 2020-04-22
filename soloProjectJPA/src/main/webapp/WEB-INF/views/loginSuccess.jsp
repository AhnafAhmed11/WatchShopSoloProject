<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login Success page</title>
</head>
<body>
	<h1>You have Logged In!</h1>

	Here are the details you entered:	<br>
	Username: ${customer.username}   <br>
	Password: ${customer.password}   <br><br>
	
	<a href='customerProfile'>Your info</a> <br>
	<a href='addAccount'>Add an account</a> <br><br>
	
	<a href='listWatches'>List of Watches</a> <br>
	<a href='listOrders'>List of orders</a> <br><br>
	
	<a href='login'>Logout</a> <br><br>
	<sf:form action="removeCustomer" method="POST" modelAttribute="customer">
     <input type="submit" name="commit" value="Delete Account" /><br><br>
   </sf:form>
	
</body>
</html>