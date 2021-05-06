<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.User" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/font.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/style.css">
<script src="<%= request.getContextPath()%>/assets/js/jquery-1.11.3.min.js"></script>
<title><%= request.getAttribute("title") %></title>
</head>

<body>
	<div class="header_div">
		<a> Football Prediction</a>
		<img alt="logo" src="<%= request.getContextPath()%>/assets/img/logo.png"/>
	</div>
		
	<c:set var="isAdmin" value='<%= request.getSession().getAttribute("adminId") != null%>'></c:set>
	<c:set var="isUser" value='<%= request.getSession().getAttribute("userId") != null%>'></c:set>
	<nav>
		<ul class="mainEn">
			
			<li class="haveSub" style="border-left-style:solid;">
				<a>Results</a>
				<ul class="sub">
					<li><a href="<%= request.getContextPath()%>/UserSpace?page=weekResults&week=<%= request.getSession().getAttribute("currentWeek")%>">Current Week Fixture </a></li>
					<li><a href="<%= request.getContextPath()%>/UserSpace?page=weekResults&week=<%= request.getSession().getAttribute("nextWeek")%>">Next Week Fixture</a></li>
					<!--<li><a>Top 5 Leagues</a></li> -->
				</ul>
			</li>
						
			<c:if test="${isUser}">
				<li class="haveSub">
					<a>Prediction</a>
					<ul class="sub">
						<li><a href="<%= request.getContextPath()%>/UserSpace?page=updatePredictions&week=<%= request.getSession().getAttribute("currentWeek")%>">Current Week</a></li>
						<li><a href="<%= request.getContextPath()%>/UserSpace?page=userresult&week=<%= request.getSession().getAttribute("currentWeek")%>">Results</a></li>
						<li><a href="<%= request.getContextPath()%>/UserSpace?page=standings">Overall Ranking</a></li>
						<!--<li><a>Top 5 Leagues</a></li> -->
					</ul>
				</li>
				<li class="haveSub">
					<a>League</a>
					<ul class="sub">
						<li><a>Your Leagues</a></li>
						<li><a>Add a League</a></li>
						<li><a>Join a League </a></li>
					</ul>
				</li>
			</c:if>			
			
			<c:if test="${isAdmin}">
				<li class="haveSub">
					<a>Matches</a>
					<ul class="sub">
						<li><a href="<%= request.getContextPath()%>/AdminSpace?page=addWeek">New Match Week</a></li>
						<li><a href="<%= request.getContextPath()%>/AdminSpace?page=updateWeek&week=<%= request.getSession().getAttribute("currentWeek")%>">Modify Match Week</a></li>
						<li><a href="<%= request.getContextPath()%>/AdminSpace?page=updateresults&week=<%= request.getSession().getAttribute("currentWeek")%>">Update Results</a></li>						
					</ul>
				</li>
				<li class="haveSub">
					<a>Teams Management</a>
					<ul class="sub">
						<li><a href="<%= request.getContextPath()%>/AdminSpace?page=addTeam">Add Team</a></li>
						<li><a href="<%= request.getContextPath()%>/AdminSpace?page=updateTeam">Modify Team</a></li>
						<li><a href="<%= request.getContextPath()%>/AdminSpace?page=addCountry">Add Country</a></li>
						<li><a href="<%= request.getContextPath()%>/AdminSpace?page=updateCountry">Modify Country</a></li>
					</ul>
				</li>
			</c:if>		
			
			<li class="haveSub">
				<a>Game Rules</a>
			 </li>
			
			<li class="haveSub">
				<a>User Account</a>
				<ul class="sub">
					<c:if test="${!isUser}">
						<li><a href="<%= request.getContextPath()%>/UserController?page=login">Login</a></li>
						<li><a href="<%= request.getContextPath()%>/UserController?page=adduser">Sign up</a></li>
					</c:if>	
					<c:if test="${isUser}">
						<li><a href="<%= request.getContextPath()%>/UserController?page=updateuser">Update Info</a></li>
						<li><a href="<%= request.getContextPath()%>/UserController?page=changepassword">Change Password</a></li>
						<li><a href="<%= request.getContextPath()%>/UserController?page=logout">Logout</a></li>
					</c:if>	
				</ul>
			 </li>
		</ul>
	</nav>
</body>
</html>