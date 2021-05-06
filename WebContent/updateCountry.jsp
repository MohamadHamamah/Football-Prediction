<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mohamad.entity.Country" %>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<form action="<%= request.getContextPath()%>/UserController" method="post">
			<% Country country = (Country) request.getAttribute("country"); %>
			
			<div class="row">
	    		<div class="col-20">
	      			<label for="countryName">Country Name:</label>
	    		</div>
	    		<div class="col-80">
	      			<input type="text" name="country_name" value=<%= country.getCountryName()%> required="required"/>
	   			 </div>	
			</div>	
			
			<div class="row">
   				<input type="submit" value="Update">
  			</div>	
			<input type="hidden" name="country_id" value=<%= country.getCountryId()%>>
			<input type="hidden" name="form" value="updateCountry">
		</form>
	</div>		
</body>
</html>
<%@include file="include/footer.jsp" %>