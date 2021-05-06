<%@ page import="com.mohamad.entity.User" %>
<%@ page import="java.text.SimpleDateFormat"  %>
<%@include file="include/header.jsp" %>

<div class="container">
	<% User user = (User) request.getAttribute("user"); %>
	<form action="${pageContext.request.contextPath}/UserController" method="post">
		<input type="hidden" name="user_id" value=<%= user.getUser_id()%>>
		<div class="row">
    		<div class="col-20">
      			<label for="userName">User Name:</label>
    		</div>
    		<div class="col-80">
      			<input type="text" name="username" value=<%= user.getUsername()%> required="required"/>
   			 </div>	
		</div>	
		
		<div class="row">
    		<div class="col-20">
      			<label for="userName">Email:</label>
    		</div>
    		<div class="col-80">
      			<input type="email" name="email" value=<%= user.getEmail()%> required="required"/>
   			 </div>	
		</div>	
		
		<div class="row">
    		<div class="col-20">
      			<label for="userName">Gender:</label>
    		</div>
    		<div class="col-80">
      			<input type="radio" id="male" name="gender" value="m" <%= user.getGender()=='m'?"checked":"" %> />
	  			<label for="male">Male</label>
				<input type="radio" id="female" name="gender" value="f" <%= user.getGender()=='f'?"checked":"" %> />
				<label for="female">Female</label>
   			 </div>	
		</div>
		
		<div class="row">
    		<div class="col-20">
      			<label for="userName">Birth Date:</label>
    		</div>
    		<div class="col-80">
      			<input type="date" name="birthdate" value=<%= (new SimpleDateFormat("yyyy-mm-dd")).format(user.getBirthDate()) %> />
   			 </div>	
		</div>			
		
		<div class="row">
			<div class="col-20">
      			<label for="userName">Mobile Number:</label>
    		</div>
    		<div class="col-80">
      			<input type="tel" name="mobileNo" maxlength="15" size="25" value=<%= user.getMobileNo()%> />
   			 </div>	
		</div>
		
		<div class="row">
   			<input type="submit" value="Update User">
  		</div>	
  		
		<input type="hidden" name="form" value="updateUserOperation">
		
	</form>
</div>
<%@include file="include/footer.jsp" %>