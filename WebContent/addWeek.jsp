<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.Week" %>
<%@ page import="com.mohamad.entity.Country" %>
<%@ page import="com.mohamad.entity.Team" %>
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
			<% 	
				@SuppressWarnings("unchecked")
				List<Country> countries = (List<Country>) request.getAttribute("countries");
				@SuppressWarnings("unchecked")
				List<Team> teams = (List<Team>) request.getAttribute("teams");
				Week lastWeek = (Week) getServletContext().getAttribute("lastWeek");
				String currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(Calendar.getInstance().getTime()); 
			%>
				
			<form action="<%= request.getContextPath()%>/AdminSpace" method="post">
				<div class="row">
		      		<label for="seasonStartingSeason"> 
		      			Week <%= lastWeek.getWeekId() + 1 %>
		      		</label>
				</div>	
				
				<div class="row">
		      		<div class="col-30"> Home Team </div>
		      		<div class="col-30"> Away Team </div>
		      		<div class="col-10"> Competition </div>
		      		<div class="col-10"> Doublable </div>
		      		<div class="col-20"> Match Time </div>
				</div>	
				
				<c:forEach var = "i" begin="0" end="9" >
					<div class="row">
				   		<div class="col-30">
				    		<select class="countryClass" name = "country${i}"  id = "country${i}"data-team-id="homeTeam${i}">
				      			<c:forEach items = "<%= countries %>" var = "country">
							    	<option value = "${country.getCountryId()}" >   ${country.getCountryName()}  </option>
							    </c:forEach>
								
							</select>
							<select class="teamClass" name = "homeTeam${i}" id = "homeTeam${i}" >
			      				<c:forEach items = "<%= teams %>" var = "team">
			      					<option data-country-id ="${team.getCountry().getCountryId()}" value = "${team.getTeamId()}" >   ${team.getTeamName()}  </option>
								</c:forEach>
							</select>
				   		</div>	
				   		
				   		<div class="col-30">
				    		<select class="countryClass" name = "country${i}${i}"  id = "country${i}${i}" data-team-id="awayTeam${i}">
				      			<c:forEach items = "<%= countries %>" var = "country">
							    	<option value = "${country.getCountryId()}" >   ${country.getCountryName()}  </option>
							    </c:forEach>
								
							</select>
							<select class="teamClass" name = <c:out value =  "awayTeam${i}"/> id = <c:out value = "awayTeam${i}"/> >
			      				<c:forEach items = "<%= teams %>" var = "team">
			      					<option data-country-id ="${team.getCountry().getCountryId()}" value = "${team.getTeamId()}" >   ${team.getTeamName()}  </option>
								</c:forEach>
							</select>
				   		</div>	
				   		
				   		<div class="col-10">
				   			<select name = "competition${i}" id = "competition${i}">
				   				<c:forEach items ="${competitions}" var = "competition">
			      					<option  value = "<%= (String)pageContext.getAttribute("competition") %>">				
										<c:out value="${competition}"></c:out>	
				      				</option>
								</c:forEach>
				   			</select>
				   		</div>	
				   		
				   		<div class="col-10">
				   			<select name = "doublable${i}" id = "doublable${i}">
			      				<option  value = "yes" > 
			      				 Yes 
			      				 </option>
			      				<option  value = "no" selected  >  No </option>
				   			</select>
				   		</div>	
				   		
				   		<div class="col-20">
				   			<input type="datetime-local" id="matchDate${i}" name="matchDate" value = <%= currentDate %> />
				   		</div>	
					</div>	
				</c:forEach>
				
				<div class="row">
	   				<input type="submit" value="Add Week Matches">
	  			</div>	
				<input type="hidden" name="form" value="addWeek">
			</form>
		</div>		
	</body>
</html>
<%@include file="include/footer.jsp" %>