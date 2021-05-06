<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.Country" %>
<%@ page import="com.mohamad.entity.Team" %>
<%@ page import="com.mohamad.entity.Match" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
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
			<% @SuppressWarnings("unchecked")
				List<Team> teams = (List<Team>) request.getAttribute("teams"); %>
			<% @SuppressWarnings("unchecked")
				List<String> competitions = (List<String>) request.getAttribute("competitions"); %>
			<%  int week = (int) request.getAttribute("week"); %>
			<%  Match match = (Match) request.getAttribute("match"); %>
				
			<div class="row">
		      	<div class="col-30"> Home Team </div>
		      	<div class="col-30"> Away Team </div>
		     	<div class="col-10"> Competition </div>
		     	<div class="col-10"> Doublable </div>
		     	<div class="col-20"> Match Time </div>
			</div>	
				
			<div class="row">
		   		<div class="col-30">
		    		<select class="countryClass" name = "country"  id = "country"data-team-id="homeTeam">
		      			<c:forEach items = "<%= countries %>" var = "country">
					    	<option value = "${country.getCountryId()}" 
					    	<%= match.getHomeTeam().getCountry().getCountryId() == ((Country)pageContext.getAttribute("country")).getCountryId() ?"selected":""  %>>   
					    	${country.getCountryName()}  </option>	
					    </c:forEach>
					</select>
					<select class="teamClass" name = "homeTeam" id = "homeTeam" >
		      			<c:forEach items = "<%= teams %>" var = "team">
		      				<option data-country-id ="${team.getCountry().getCountryId()}" value = "${team.getTeamId()}" 
		      				<%= match.getHomeTeam().getTeamId() == ((Team)pageContext.getAttribute("team")).getTeamId() ?"selected":""  %>>
		      				${team.getTeamName()}  </option>
						</c:forEach>
					</select>
		   		</div>	
			   		
			   	<div class="col-30">
			   		<select class="countryClass" name = "country"  id = "country" data-team-id="awayTeam">
			   			<c:forEach items = "<%= countries %>" var = "country">
					    	<option value = "${country.getCountryId()}"
					    	<%= match.getAwayTeam().getCountry().getCountryId() == ((Country)pageContext.getAttribute("country")).getCountryId() ?"selected":""  %>>
					    	   ${country.getCountryName()}  </option>
					    </c:forEach>
							
					</select>
					<select class="teamClass" name = <c:out value =  "awayTeam"/> id = <c:out value = "awayTeam"/> >
		      			<c:forEach items = "<%= teams %>" var = "team">
		      				<option data-country-id ="${team.getCountry().getCountryId()}" value = "${team.getTeamId()}" 
							<%= match.getAwayTeam().getTeamId() == ((Team)pageContext.getAttribute("team")).getTeamId() ?"selected":""  %>>
							   ${team.getTeamName()}  </option>
						</c:forEach>
					</select>
			   	</div>	
			   		
			   	<div class="col-10">
			   		<select name = "competition" id = "competition">
			   			<c:forEach items ="<%= competitions %>" var = "competition">
		      				<option  value = "<%= (String)pageContext.getAttribute("competition") %>"
		      				<c:if test="${ match.getCompetition() == competition}">
										"select = 'selected'"
							</c:if>>
								<c:out value="${competition}"></c:out>	
		      				</option>
						</c:forEach>
		   			</select>
			   	</div>	
			   		
			   	<div class="col-10">
			   		<select name = "doublable" id = "doublable">
		     			<option  value = "yes" <%= match.getDoublable() == true ?"selected":""  %>> Yes </option>
			      		<option  value = "no" <%= match.getDoublable() == false ?"selected":""  %>>  No </option>
			   		</select>
			   	</div>	
			   		
			   	<div class="col-20">
			   		<input type="datetime-local" id="matchDate" name="matchDate" value = <%= match.getMatchTime() %> />
			  	</div>	
			</div>	
			
			<div class="row">
   				<input type="submit" value="Add Match">
  			</div>	
  			<input type="hidden" name="matchId" value=<%= match.getMatchId() %>>
			<input type="hidden" name="week" value=<%= week %>>
			<input type="hidden" name="form" value="updateMatch">
		</form>
	</div>		
</body>
</html>
<%@include file="include/footer.jsp" %>