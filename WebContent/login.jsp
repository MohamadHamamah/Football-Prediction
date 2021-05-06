<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>
<div class="container">
	<form action="<%= request.getContextPath()%>/UserController" method="post">
	<div class="row">
	    <div class="col-20">
	  		<label for="userName">Email:</label>
	  	</div>
	    <div class="col-80">
	      	<input type="text" name="email" >
	   	</div>	
	</div>	
	
	<div class="row">
	    <div class="col-20">
	  		<label for="userName">Password:</label>
	  	</div>
	    <div class="col-80">
	      	<input type="password" name="password">
	   	</div>	
	</div>	
	
	<div class="row">
		<input type="submit" value="Login">
	</div>
	<input type="hidden" name="form" value="loginOperation">
	</form>
</div>

</body>
</html>
<%@include file="include/footer.jsp" %>