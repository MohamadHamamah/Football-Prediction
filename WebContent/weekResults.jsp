<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.Match" %>
<%@ page import="com.mohamad.entity.Week" %>
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
			List<Match> matches = (List<Match>) request.getAttribute("matches"); %>
			<%  int currentWeek = matches.get(0).getWeek().getWeekId(); 
				pageContext.setAttribute("currentWeek", currentWeek);%>
				
				<div class="row">
		      		<label for="seasonStartingSeason"> 
		      			Week <%= currentWeek %>
		      		</label>
				</div>	
				
				<div class="row">
			   		<div  class="col-30">		
			   		</div>	
			   		
			   		<div class="col-30">
			   			<c:if test="${ currentWeek != 1}">
			   				<a href="<%=request.getContextPath()%>/UserSpace?page=browsematches&week=<%=currentWeek-1 %>"><span style="float:left">Previous Week</span></a>
			   			</c:if>
			    		
			    		<c:if test="${currentWeek != lastWeek.getWeekId()}">
			   				<a href="<%=request.getContextPath()%>/UserSpace?page=browsematches&week=<%=currentWeek+1 %>"><span style="float:right">Next Week</span></a>
			   			</c:if>			    	
			   		</div>	
			   		
			   		<div class="col-40">		
			   		</div>	
				</div>	
				
				<c:forEach var = "i" begin="0" end="${matches.size()-1}" >
					<div class="row">
						<input type="hidden" name="matchId${i}" value="<%=matches.get((int)pageContext.getAttribute("i")).getMatchId()%>">
				   		<div  class="col-45">
				   			<span class="homeTeam"><%= matches.get((int)pageContext.getAttribute("i")).getHomeTeam().getTeamName() %></span>		
				   		</div>	
				   		
				   		<div class="col-5">
				    		<span class="result"> <%= matches.get((int)pageContext.getAttribute("i")).getHomeScore() == -1 ? "-" : matches.get((int)pageContext.getAttribute("i")).getHomeScore() %></span>
				    		<span style="float:left">-</span>
				    		<span class="result"> <%= matches.get((int)pageContext.getAttribute("i")).getAwayScore() == -1 ? "-" : matches.get((int)pageContext.getAttribute("i")).getAwayScore() %></span>
				   		</div>	
				   		
				   		<div class="col-45">
				   			<span class="awayTeam"><%= matches.get((int)pageContext.getAttribute("i")).getAwayTeam().getTeamName() %></span>		
				   		</div>	
					</div>	
				</c:forEach>
		</div>		
	</body>
</html>
<%@include file="include/footer.jsp" %>