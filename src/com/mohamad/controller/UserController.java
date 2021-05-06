package com.mohamad.controller;
import com.mohamad.entity.User;
import com.mohamad.model.UserModel;
import java.sql.Date;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Resource(name = "jdbc/project")
    private DataSource dataSource;   


    
    public UserController() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		String page = request.getParameter("page");
		page = page.toLowerCase();		
		page = manageSession(request, response, page);
		
		switch (page) {
		case "login" :
			loginFormLoad(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "adduser":
			addUserFormLoader(request, response);
			break;
		case "updateuser":
			UpdateUserFormLoader(request, response);
			break;
		case "getinfo":
			getUserInformation(request, response);
			break;
		case "changepassword":	
		     changeUserPasswordForm(request, response);
		     break;
		case "forgetPassword":	
		     forgetPasswordForm(Integer.parseInt(request.getParameter("usersId")));
		     break;
		default:
			errorPage(request, response);	
	}

}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String operation = request.getParameter("form");
		operation = operation.toLowerCase(); 
		switch (operation) {
		case "adduseroperation":
			addUserOperation(request, response);
			break;
		case "updateuseroperation":
			updateUserOperation(dataSource, request, response);
			break;
		case "loginoperation":
			login(request, response);
			break;
		default:
			errorPage(request, response);
			break;
		}
		
	}

	private String manageSession(HttpServletRequest request, HttpServletResponse response, String page) { 		
		if(request.getSession().getAttribute("userId") == null && !page.equals("login")){
	    	page = "login";
	    }
		return page;
	}
	
	private void loginFormLoad(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		request.setAttribute("title", "Login");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		User user = new UserModel().getUser(dataSource, email);
		
		if(user != null && user.getPassword().equals(password)) {
			//Invalidating session if any
			request.getSession().invalidate();
			HttpSession newSession = request.getSession(true);
		    newSession.setMaxInactiveInterval(300);
		    newSession.setAttribute("userId", user.getUser_id());
		    response.sendRedirect("index.jsp");
		}else {
			// TODO Send message error explaining to the user the reason for login failure
			System.out.println("Condition is not satisfied");
			System.out.println(password);
			response.sendRedirect("login.jsp");
		}
		
	}

	private void updateUserOperation(DataSource dataSource, HttpServletRequest request, HttpServletResponse response) {
		java.util.Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("birthdate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		User updatedUser = new User( Integer.parseInt(request.getParameter("user_id")) , request.getParameter("username"), request.getParameter("email"), request.getParameter("password"), request.getParameter("gender").charAt(0),  new Date(birthDate.getTime()), request.getParameter("mobileNo"));
		new UserModel().updateUser(dataSource, updatedUser);
	}

	private void addUserOperation(HttpServletRequest request, HttpServletResponse response) {
		java.util.Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("birthdate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(birthDate);
		User newUser = new User(request.getParameter("username"), request.getParameter("email"), request.getParameter("password"), request.getParameter("gender").charAt(0), new Date(birthDate.getTime()), request.getParameter("mobileNo"));
		new UserModel().addUser(dataSource, newUser);
	}
	
	

	private void getUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		int id = (int) request.getSession().getAttribute("userId");
		request.setAttribute("user", new UserModel().getUser(dataSource, id));
	}
	private void getUserInformation(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		getUser(request, response);
		request.setAttribute("title", "User Information");
		request.getRequestDispatcher("userInfo.jsp").forward(request, response);
	}



	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		request.getSession().invalidate();		
		response.sendRedirect(request.getContextPath()+"/UserController?page=login");		
	}


	private void forgetPasswordForm(int parseInt) {
		// TODO Auto-generated method stub
		
	}

	private void changeUserPasswordForm( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		getUser(request, response);
		request.setAttribute("title", "Change Password");
		request.getRequestDispatcher("changePassword.jsp").forward(request, response);
	}

	private void forgetPassword(int parseInt) {
		// TODO Auto-generated method stub
		
	}

	private void errorPage(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.setAttribute("title", "Error page");
			request.getRequestDispatcher("error.jsp").forward(request, response);
	
	}

	private void changeUserPassword(int parseInt) {
		// TODO Auto-generated method stub
		
	}

	private void UpdateUserFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		getUser(request, response);
		request.setAttribute("title", "Update User");
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}

	private void addUserFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		request.setAttribute("title", "Add User");
		request.getRequestDispatcher("addUser.jsp").forward(request, response);
	}
}