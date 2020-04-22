<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your basket</title>
</head>
<body>
<h1>Your Basket</h1>

	<c:forEach var="eachWatch" items="${basketItems}">

		Watch ID Number: ${eachWatch.key.watchID}<br> 
		Watch price: ${eachWatch.key.price}<br> 
		Quantity: ${eachWatch.value}<br><br>
	</c:forEach>

	Total: ${requestScope.total}<br><br>
	Customer Balance: ${requestScope.balance}<br><br>
	<b>${requestScope.message1}</b><br><br>

	<sf:form action="${pageContext.request.contextPath}/addOrder" method="POST" modelAttribute="order">
     <input type="submit" name="commit" value="Place order" /><br><br>
   </sf:form>

<a href= '${pageContext.request.contextPath}/listWatches'>Keep shopping</a>

</body>
</html>