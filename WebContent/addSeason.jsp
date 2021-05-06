<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div class="container">
			<form action="<%= request.getContextPath()%>/AdminSpace" method="post">
				<div class="row">
		    		<div class="col-20">
		      			<label for="seasonStartingYear">Season Year:</label>
		    		</div>
		    		<div class="col-80">
		      			<input type="number" name="season_start" required="required"/>
		   			</div>	
				</div>	
				
				<div class="row">
	   				<input type="submit" value="Add Season">
	  			</div>	
	
				<input type="hidden" name="form" value="addSeason">
			</form>
		</div>		
	</body>
</html>
<%@include file="include/footer.jsp" %>