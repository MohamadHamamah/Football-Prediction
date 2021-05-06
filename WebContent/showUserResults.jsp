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
			<% @SuppressWarnings("unchecked")
				List<Integer> points = (List<Integer>) request.getAttribute("points"); %>	
			<%  int currentWeek = predictions.get(0).getMatch().getWeek().getWeekId(); %>
			<%  String currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(Calendar.getInstance().getTime()); %>							
			
			<div class="row">
	      		<label for="seasonStartingSeason"> 
	      			Results of <%= predictions.get(0).getUser().getUsername() %> in Week <%= currentWeek %>
	      		</label>
			</div>	
			
			<div class="row">
		   		<div  class="col-30">		
		   		</div>	
		   		
		   		<div class="col-30">
		   			<c:if test="${ currentWeek != 1}">
		   				<a href="<%=request.getContextPath()%>/UserSpace?page=userResult&week=<%=currentWeek-1 %>&user=<%= predictions.get(0).getUser().getUser_id() %>"><span style="float:left">Previous Week</span></a>
		   			</c:if>
		    		
		    		<c:if test="${currentWeek != lastWeek.getWeekId()}">
		   				<a href="<%=request.getContextPath()%>/UserSpace?page=userResult&week=<%=currentWeek+1 %>&user=<%= predictions.get(0).getUser().getUser_id() %>"><span style="float:right">Next Week</span></a>
		   			</c:if>			    	
		   		</div>	
		   		
		   		<div class="col-40">		
		   		</div>	
			</div>	
			
			<c:forEach var = "i" begin="0" end="${predictions.size()-1}" >
				<div class="row">												
			   		<div  class="col-40">
			   			<span class="homeTeam"><%= predictions.get((int)pageContext.getAttribute("i")).getMatch().getHomeTeam().getTeamName() %></span>		
			   		</div>	
			   		
			   		<div class="col-5">
			    		<span class="result"><%= predictions.get((int)pageContext.getAttribute("i")).getMatch().getHomeScore() == -1 ? "-" : predictions.get((int)pageContext.getAttribute("i")).getMatch().getHomeScore() %></span> 
			    		<span class="result"><%= predictions.get((int)pageContext.getAttribute("i")).getMatch().getAwayScore() == -1 ? "-" : predictions.get((int)pageContext.getAttribute("i")).getMatch().getAwayScore() %></span> 
			   		</div>	
			   		
			   		<div class="col-40">
			   			<span class="awayTeam"><%= predictions.get((int)pageContext.getAttribute("i")).getMatch().getAwayTeam().getTeamName() %></span>		
			   		</div>	
				</div>	
				
				<div class="row">	
					<div  class="col-40">
						<span class="homeTeam">Prediction:</span>
					</div>	
			   		
			   		<div class="col-5">
			    		<span class="result"><%= predictions.get((int)pageContext.getAttribute("i")).getHomePredictedScore() == -1 ? 0 : predictions.get((int)pageContext.getAttribute("i")).getHomePredictedScore() %></span> 
			    		<span class="result"><%= predictions.get((int)pageContext.getAttribute("i")).getAwayPredictedScore() == -1 ? 0 : predictions.get((int)pageContext.getAttribute("i")).getAwayPredictedScore() %></span>
			   		</div>	
			   		
			   		<div class="col-40">
			   			<span class="awayTeam">
			   				Points: <%= points.get((int)pageContext.getAttribute("i")) %>
			   				<c:if test=""></c:if>
			   			</span>			   					
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
										
		</div>		
	</body>
</html>
<%@include file="include/footer.jsp" %>