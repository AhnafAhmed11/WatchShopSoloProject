<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
</head>
<body>
	<h1>List of all orders</h1>
	<c:forEach var="eachOrder" items="${listOrders}">
		Order ID: ${eachOrder.orderID}<br>
		Order Number: ${eachOrder.orderNumber}<br> 
		Order date: ${eachOrder.orderDate}<br><br><br>
	</c:forEach>
	
	<a href= '${pageContext.request.contextPath}/loginSuccess'>Go back</a>
</body>
</html>