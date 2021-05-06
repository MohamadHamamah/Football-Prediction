<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.Country" %>
<%@ page import="com.mohamad.entity.Team" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<form action="<%= request.getContextPath()%>/UserController" method="post">
	        <% @SuppressWarnings("unchecked")
	        	List<Country> countries = (List<Country>) request.getAttribute("countries"); %>
	        <% Team team = (Team) request.getAttribute("team"); %>
	         
			<div class="row">
	    		<div class="col-20">
	      			<label for="teamName">Team Name:</label>
	    		</div>
	    		<div class="col-80">
	      			<input type="text" name="teamName" value=<%= team.getTeamName()%> required="required"/>
	   			 </div>	
			</div>	
			
			<div class="row">
	    		<div class="col-20">
	      			<label for="teamName">Country:</label>
	    		</div>
	    		<div class="col-80">
	      			<select name="country" id="countries">
	      				<c:forEach items = "<%= countries %>" var = "country" >
					    	<option value = "${country.getCountryId()}"   <c:if test="${country.equals(team.getCountry())}"> selected </c:if> >  ${country.getCountryName()} </option>					    	 
					    </c:forEach> 
				</select>
	   			 </div>	
			</div>	
			
			
			<div class="row">
   				<input type="submit" value="Update">
  			</div>	
  			
			<input type="hidden" name="team_id" value=<%= team.getTeamId() %> >
			<input type="hidden" name="form" value="updateTeam">
		</form>
	</div>		
</body>
</html>

<%@include file="include/footer.jsp" %>