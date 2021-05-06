<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.mohamad.entity.Week" %>
<%@ page import="com.mohamad.entity.Match" %>
<%@ page import="com.mohamad.entity.Prediction" %>
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
				List<Prediction> predictions = (List<Prediction>) request.getAttribute("predictions"); %>	
			<%  int currentWeek = predictions.get(0).getMatch().getWeek().getWeekId(); %>
			<%  String currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(Calendar.getInstance().getTime()); %>
					
			<form action="<%= request.getContextPath()%>/UserSpace" method="post">
				
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
			   				<a href="<%=request.getContextPath()%>/UserSpace?page=updatePredictions&week=<%=currentWeek-1 %>"><span style="float:left">Previous Week</span></a>
			   			</c:if>
			    		
			    		<c:if test="${currentWeek != lastWeek.getWeekId()}">
			   				<a href="<%=request.getContextPath()%>/UserSpace?page=updatePredictions&week=<%=currentWeek+1 %>"><span style="float:right">Next Week</span></a>
			   			</c:if>			    	
			   		</div>	
			   		
			   		<div class="col-40">		
			   		</div>	
				</div>	
				
				<c:forEach var = "i" begin="0" end="${predictions.size()-1}" >
					<div class="row">
						<input type="hidden" name="prediction${i}" value="<%=predictions.get((int)pageContext.getAttribute("i")).getPredictionId()%>">
						<input type="hidden" name="matchId${i}" value="<%=predictions.get((int)pageContext.getAttribute("i")).getMatch().getMatchId()%>">
				   		<div  class="col-40">
				   			<span class="homeTeam"><%= predictions.get((int)pageContext.getAttribute("i")).getMatch().getHomeTeam().getTeamName() %></span>		
				   		</div>	
				   		
				   		<div class="col-5">
				    		<input class="result" name="homeTeamResult${i}" type="number" value="<%= predictions.get((int)pageContext.getAttribute("i")).getHomePredictedScore() == -1 ? 0 : predictions.get((int)pageContext.getAttribute("i")).getHomePredictedScore() %>">
				    		<input class="result" name="awayTeamResult${i}" type="number" value="<%= predictions.get((int)pageContext.getAttribute("i")).getAwayPredictedScore() == -1 ? 0 : predictions.get((int)pageContext.getAttribute("i")).getAwayPredictedScore() %>">
				   		</div>	
				   		
				   		<div class="col-40">
				   			<span class="awayTeam"><%= predictions.get((int)pageContext.getAttribute("i")).getMatch().getAwayTeam().getTeamName() %></span>		
				   		</div>	
					</div>	
				</c:forEach>
				
				<div class="row">
					<select name = "doubleMatch">
						<c:forEach var = "i" begin="0" end="${predictions.size()-1}" >			
							<c:if test="${predictions.get(i).getMatch().getDoublable() == true }">
								<option value="${i}"
									<%= predictions.get((int)pageContext.getAttribute("i")).getPredictionDoublable() == true ? "selected" : "" %>>
									${predictions.get(i).getMatch().getHomeTeam().getTeamName()} vs ${predictions.get(i).getMatch().getAwayTeam().getTeamName()}
								</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				
				<div class="row">
	   				<input type="submit" value="Submit">
	  			</div>	
				<input type="hidden" name="week" value=<%= currentWeek %>>
				<input type="hidden" name="form" value="updatepredictions">
			</form>
		</div>		
	</body>
</html>
<%@include file="include/footer.jsp" %>