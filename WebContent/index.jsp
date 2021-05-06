<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<a href="<%= request.getContextPath()%>/UserController?page=login">Login</a><br>
<a href="<%= request.getContextPath()%>/UserController?page=adduser">Sign up</a><br>
<a href="<%= request.getContextPath()%>/UserController?page=updateuser">Update User</a><br>
<a href="<%= request.getContextPath()%>/UserController?page=changepassword">Change Password</a><br>
<a href="<%= request.getContextPath()%>/UserController?page=getinfo">Update Information</a><br>
</body>
</html>
<%@include file="include/footer.jsp" %>