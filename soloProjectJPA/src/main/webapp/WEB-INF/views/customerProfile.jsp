<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
	<h1>Your profile</h1>
		First name: ${customerInDB.firstname}<br>
		Last name: ${customerInDB.lastname}<br>
		Address: ${customerInDB.address}<br>
		Phone: ${customerInDB.phone}<br>
		Username: ${customerInDB.username}<br>
		Password: ${customerInDB.password}<br>
		Card ID: ${customerInDB.bankDetails.cardID}<br><br>
		
		<a href='listOfBankDetails'>My bank details</a> <br><br>
		<a href= '${pageContext.request.contextPath}/loginSuccess'>Go back</a>
</body>
</html>