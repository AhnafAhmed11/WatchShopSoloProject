<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<h1><img alt="FDM logo" src="H:\\fdm-logo-black.jpg" style="width:125px;height:95px;"><br></h1>
		<h2>Login</h2>

	<sf:form action="submitLogin" method="POST" modelAttribute="customer">
		<sf:label path="username">Username</sf:label>
		<sf:input path="username" size="30" />
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" />
		<input type="submit" name="commit" value="submit" />
	</sf:form>

	<B>${requestScope.message}</B>

	<br/><br/><br/>

	<a href='registration'>Go to register page</a> <br><br>


	<!-- img src="<c:url value="/resources/image.jpg" />"> -->
</body>
</html>