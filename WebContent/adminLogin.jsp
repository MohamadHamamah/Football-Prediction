<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div class="container">
	<form action="<%= request.getContextPath()%>/AdminSpace" method="post">
	<div class="row">
	    <div class="col-20">
	  		<label for="userName">Email:</label>
	  	</div>
	    <div class="col-60">
	      	<input type="text" name="email" >
	   	</div>	
	   	<div class="col-20">
	  	</div>
	</div>	
	
	<div class="row">
	    <div class="col-20">
	  		<label for="userName">Password:</label>
	  	</div>
	    <div class="col-60">
	      	<input type="password" name="password">
	   	</div>	
	   	<div class="col-20">
	  		<span class="message"><%= request.getParameter("message") == null? "" : request.getParameter("message") %></span>
	  	</div>
	</div>	
	
	<div class="row">
		<input type="submit" value="Login">
	</div>
	<input type="hidden" name="form" value="login">
	</form>
</div>

</body>
</html>
<%@include file="include/footer.jsp" %>