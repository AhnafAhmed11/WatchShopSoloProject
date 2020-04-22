<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your bank details</title>
</head>
<body>
	<h1>Your account details</h1>
		Card ID: ${accountInDB.cardID}<br>
		Card Number: ${accountInDB.cardNum}<br>
		Balance: ${accountInDB.balance}<br>
		Sortcode: ${accountInDB.sortCode}<br><br>
		
	<sf:form action="removeBankDetails/${accountInDB.cardID}" method="POST" modelAttribute="bankDetails">
     <input type="submit" name="commit" value="Delete Account" /><br><br>
   </sf:form>
	
	</body>
</body>
</html>