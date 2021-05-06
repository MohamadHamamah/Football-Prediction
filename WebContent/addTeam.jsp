<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.Country" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<form action="<%= request.getContextPath()%>/AdminSpace" method="post">
	        <% @SuppressWarnings("unchecked")
	        	List<Country> countries = (List<Country>) request.getAttribute("countries"); %>
			<div class="row">
	    		<div class="col-20">
	      			<label for="teamName">Team Name:</label>
	    		</div>
	    		<div class="col-80">
	      			<input type="text" name="teamName" required="required"/>
	   			 </div>	
			</div>	
			
			<div class="row">
	    		<div class="col-20">
	      			<label for="teamName">Country:</label>
	    		</div>
	    		<div class="col-80">
	      			<select name="country" id="countries">
	      				<c:forEach items = "<%= countries %>" var = "country">
					    	<option value = "${country.getCountryId()}" >   ${country.getCountryName()}  </option>
					    </c:forEach>
						
					</select>
	   			 </div>	
			</div>	
			
			<div class="row">
   				<input type="submit" value="Add Team">
  			</div>	

			<input type="hidden" name="form" value="addTeam">
		</form>
	</div>		
</body>
</html>
<%@include file="include/footer.jsp" %>