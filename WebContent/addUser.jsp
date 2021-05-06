<%@include file="include/header.jsp" %>
<div class="container">
	<form action="<%= request.getContextPath()%>/UserController" method="post">
		<div class="row">
    		<div class="col-20">
      			<label for="userName">User Name:</label>
    		</div>
    		<div class="col-80">
      			<input type="text" name="username" required="required"/>
   			 </div>	
		</div>	
		
		<div class="row">
    		<div class="col-20">
      			<label for="userName">Email:</label>
    		</div>
    		<div class="col-80">
      			<input type="email" name="email" required="required"/>
   			 </div>	
		</div>	
		
		<div class="row">
    		<div class="col-20">
      			<label for="userName">Password:</label>
    		</div>
    		<div class="col-80">
      			<input type="password" name="password" id="password" onchange="validatePassword()" required="required" />
   			 </div>	
		</div>	
		
		<div class="row">
    		<div class="col-20">
      			<label for="userName">Password Confirmation:</label>
    		</div>
    		<div class="col-80">
      			<input type="password" name="password_confirm" id="password_confirm" onkeyup="validatePassword()" required="required" />
      			<span id='message'></span>
   			 </div>	
		</div>	
		
		<div class="row">
    		<div class="col-20">
      			<label for="userName">Gender:</label>
    		</div>
    		<div class="col-80">
      			<input type="radio" id="male" name="gender" value="m" checked>
  				<label for="male">Male</label><br>
				<input type="radio" id="female" name="gender" value="f">
				<label for="female">Female</label><br>
   			 </div>	
		</div>
		
		 <div class="row">
    		<div class="col-20">
      			<label for="userName">Birth Date:</label>
    		</div>
    		<div class="col-80">
      			<input type="date" name="birthdate" value="1990-01-01"/>
   			 </div>	
		</div>			
		
		<div class="row">
			<div class="col-20">
      			<label for="userName">Mobile Number:</label>
    		</div>
    		<div class="col-80">
      			<input type="tel" name="mobileNo" maxlength="15" size="25" >
   			 </div>	
		</div>
		
		<div class="row">
   			<input type="submit" value="Sign up">
  		</div>	

		<input type="hidden" name="form" value="addUserOperation">
	</form>
</div>

<%@include file="include/footer.jsp" %>