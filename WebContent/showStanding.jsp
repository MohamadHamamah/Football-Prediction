<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div class="container">
			<% @SuppressWarnings("unchecked")
				List<User> users = (List<User>) request.getAttribute("users"); %>		
			<%  String currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(Calendar.getInstance().getTime()); %>
			
					
		
			
			<div class="row">
	      		<label for="seasonStartingSeason"> 
	      			Standing:
	      		</label>
			</div>	
			
			<c:forEach var = "i" begin="0" end="${users.size()-1}" >
				<div class="row">	
					<div class="col-5">
			    		<span><%= (int)pageContext.getAttribute("i") + 1 %></span>  
			   		</div>												
			   		<div  class="col-40">
			   			<span><%= users.get((int)pageContext.getAttribute("i")).getUsername() %></span>		
			   		</div>	
			   		
			   		<div class="col-40">
			    		<span><%= users.get((int)pageContext.getAttribute("i")).getPoints() %></span>  
			   		</div>	
				</div>							
			</c:forEach>
					
		</div>		
	</body>
</html>
<%@include file="include/footer.jsp" %>