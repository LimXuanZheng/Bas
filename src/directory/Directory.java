package directory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseAccess;
import database.model.UserAll;

//If I have time I confirm going to make the out.print nicer... For the time being deal with it
@WebServlet("/Directory")
public class Directory extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private int userType = 1;
    private static String username = "Bob";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CheckIP.redirect(request, response);
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
		}
		else {
			//response.sendRedirect("Login");
			System.out.println("Didn't work");
		}
		ArrayList<UserAll> userAllArray = new ArrayList<UserAll>();
		try {
			DatabaseAccess dba = new DatabaseAccess(userType);
			userAllArray = dba.getDatabaseUserAll();
			
			response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    			
	    	out.println("<!DOCTYPE html>"
	    			+ "<html>"
	    			+ 	"<head>"
	    			+ 		"<meta charset='UTF-8'>"
	    			+ 		"<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
	    			+ 		"<link rel='stylesheet' href='css/directoryStyle.css'>"
	    			+ 		"<!-- Font Mono-Sans -->"
	    			+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
	    			+ 		"<!-- Latest compiled and minified CSS -->"
	    			+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>"
	    			+ 		"<!-- Optional theme -->"
	    			+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>"
	    			//+		"<!-- My Own Script -->"
	    			//+		"<script src='script/.js'></script>"
	    			+		"<title>Directory</title>"
	    			+ 	"</head>"
	    			+ 	"<body>"
	    			+ 		"<div class='container-fluid' id='Container'>"
	    			+ 			"<!-- Navigation -->" 
	    			+ 			"<nav class='navbar navbar-default'>"
	    			+ 				"<div class='container-fluid'>"
	    			+ 					"<div class='navbar-header'>"
	    			+ 						"<a class='navbar-brand' href='Home'>Purple</a>"
	    			+ 					"</div>"
	    			+ 					"<ul class='nav navbar-nav'>"
	    			+ 						"<li>"
	    			+ 							"<a href='Home'>Home</a>"
	    			+ 						"</li>"
	    			+ 						"<li class='active'>"
	    			+ 							"<a href='#'>Directory</a>"
	    			+ 						"</li>"
	    			+ 					"</ul>"
	    			+ 					"<ul class='nav navbar-nav navbar-right'>"
	    			+						"<li class='dropdown'>"
	    			+					   		"<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Welcome, " + username + ". </a>"
	    			+					        "<ul class='dropdown-menu'>"
	    			+					        	"<li><a href='#'>Setting</a></li>"
	    			+					        	"<li><a href='Login'>Logout</a></li>"
	    			+							"</ul>"
	    			+						"</li>"
	    			+ 					"</ul>"
	    			+ 				"</div>"
	    			+ 			"</nav>"
	    			+ 			"<div id='Container-Container'>"
	    			+ 				"<div class='container-fluid' id='Left-Container'>"
	    			+ 					"<form action='Directory' method='POST'>"
	    			+ 						"<input type='text' name='search' id='searchBox' placeholder='Name'>"
	    			+ 						"<select name='class'>"
	    			+ 							"<option value='1E1'>1E1</option>"
	    			+ 							"<option value='1E2'>1E2</option>"
	    			+ 							"<option value='1E3'>1E3</option>"
	    			+ 							"<option value='1E4'>1E4</option>"
	    			+ 							"<option value='1E5'>1E5</option>"
	    			+ 						"</select>"
	    			+ 						"<select name='subject'>"
	    			+ 							"<option value='English'>English</option>"
	    			+ 							"<option value='Chinese'>Chinese</option>"
	    			+ 							"<option value='Science'>Science</option>"
	    			+ 							"<option value='Math'>Math</option>"
	    			+ 							"<option value='Art'>Art</option>"
	    			+ 						"</select>"
	    			+ 						"<input type='submit' name='submit'>"
	    			+ 					"</form>"
	    			+ 				"</div>"
	    			+ 				"<div class='container-fluid' id='Right-Container'>"
	    			+ 					"<table class='table table-striped'>"
	    			+ 						"<thead>"
	    			+ 							"<tr>");
	    	
	    			if(userType == 1){
	    				out.println(
	    						"<th><p>Name</p></th>"
	    						+ "<th><p>Gender</p></th>"
	    						+ "<th><p>Class</p></th>"
	    						+ "<th><p>Contact number</p></th>"
	    						+ "<th><p>Email</p></th>"
	    						+ "<th><p>Department</p></th>"
	    						+				"</tr>"
	    		    			+ 			"</thead>"
	    		    			+ 		"<tbody>");
	    				
	    				for(UserAll u:userAllArray){
	    					if(u.getTeacher().getTeacherID() > 0){ //Only shows if it is teacher... I lazy create a new method in Database Object (DatabaseAccess)
	    						out.println("<tr><td>" + u.getUser().getName() + "</td><td>" + u.getUser().getGender() + "</td><td>" + u.getUser().getSchoolClass() + "</td><td>" + u.getUser().getContactNo() + "</td><td>" + u.getUser().getEmail() + "</td><td>" + u.getTeacher().getDepartment() + "</td></tr>");
	    					}
	    				}
	    			}
	    			else if(userType == 2){
	    				out.println(
	    						"<th><p>NRIC</p></th>"
	    						+ "<th><p>Name</p></th>"
	    						+ "<th><p>Gender</p></th>"
	    						+ "<th><p>Class</p></th>"
	    						+ "<th><p>Contact number</p></th>"
	    						+ "<th><p>Email</p></th>"
	    						+ "<th><p>CCA</p></th>"
	    						+				"</tr>"
	    		    			+ 			"</thead>"
	    		    			+ 		"<tbody>");
	    				
	    				for(UserAll u:userAllArray){
    						out.println("<tr><td>" + u.getUser().getnRIC() + "</td><td>" + u.getUser().getName() + "</td><td>" + u.getUser().getGender() + "</td><td>" + u.getUser().getSchoolClass() + "</td><td>" + u.getUser().getContactNo() + "</td><td>" + u.getUser().getEmail() + "</td><td>" + u.getTeacher().getDepartment() + "</td></tr>");
	    				}
	    			}
	    	
			
			
			out.println(					
											"</tbody>"
					+ 					"</table>"
					+ 				"</div>"
					+ 			"</div>"
					+ 		"</div>"
					+ 		"<!-- jQuery library -->"
					+ 		"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'>"
					+ 		"</script>"
					+ 		"<!-- Latest compiled and minified JavaScript -->"
					+ 		"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa' crossorigin='anonymous'>"
					+ 		"</script>"
					+ 	"</body>"
					+ "</html>"); 
			out.close(); //PrintWriter Closed...
			dba.close(); //Database Connection Object Closed...
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get and validate name.
        String name = request.getParameter("search");
        // Get schoolClass
    	//String schoolClass = request.getParameter("schoolClass");
    	//Get Subject
    	//String subject = request.getParameter("subject");
    	
    	ArrayList<UserAll> userAllArray = new ArrayList<UserAll>();
    	try {
			DatabaseAccess dba = new DatabaseAccess(userType);
			//userAllArray = dba.getDatabaseUserAll();
			
			String sqlline = "SELECT User.UserID, Login.Username, Login.Password, Login.Salt, User.Name, User.Gender, User.DOB, User.ContactNo, User.Email, User.Class, User.Address, Student.NRIC, Student.CCA, Teacher.TeacherID, Teacher.Department FROM User LEFT OUTER JOIN Login ON (User.UserID = Login.UserID) LEFT OUTER JOIN Student ON (User.UserID = Student.UserID) LEFT OUTER JOIN Teacher ON (User.UserID = Teacher.UserID) WHERE User.Name LIKE ?;"; //Testing Search Name (It's Working)
			if(validateName(name)){ //If name is not letters, method is exited and (prompts error)
				userAllArray = dba.convertResultSetToArrayList(dba.getSearchDatabaseData(sqlline, name));
			}else{
				dba.close();
				response.sendRedirect("Directory");
				return;
			}
			
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("<!DOCTYPE html>" //Mmmm... Messy... Just I liked...
	    			+ "<html>"
	    			+ "<head>"
	    			+ "<meta charset='UTF-8'>"
	    			+ "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
	    			+ "<link rel='stylesheet' href='css/directoryStyle2.css'>"
	    			+ "<!-- Font Mono-Sans -->"
	    			+ "<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
	    			+ "<!-- Latest compiled and minified CSS -->"
	    			+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>"
	    			+ "<!-- Optional theme -->"
	    			+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>"
	    			+ "<title>Directory</title>"
	    			+ "</head>"
	    			+ "<body>"
	    			+ "<div class='container-fluid' id='Container'>"
	    			+ "<!-- Navigation -->"
	    			+ "<nav class='navbar navbar-default'>"
	    			+ "<div class='container-fluid'>"
	    			+ "<div class='navbar-header'>"
	    			+ "<a class='navbar-brand' href='Home'>Purple</a>"
	    			+ "</div>"
	    			+ "<ul class='nav navbar-nav'>"
	    			+ "<li>"
	    			+ "<a href='Home'>Home</a>"
	    			+ "</li>"
	    			+ "<li class='active'>"
	    			+ "<a href='#'>Directory</a>"
	    			+ "</li>"
	    			+ "</ul>"
	    			+ "<ul class='nav navbar-nav navbar-right'>"
	    			+ "<li class='dropdown'>"
	    			+ "<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Welcome, " + username + ". </a>"
	    			+ "<ul class='dropdown-menu'>"
	    			+ "<li><a href='#'>Setting</a></li>"
	    			+ "<li><a href='Login'>Logout</a></li>"
	    			+ "</ul>"
	    			+ "</li>"
	    			+ "</ul>"
	    			+ "</div>"
	    			+ "</nav>"
	    			+ "<div id='Container-Container'>"
	    			+ "<div class='container-fluid' id='Right-Container'>"
	    			+ "<table class='table table-striped'>"
	    			+ "<thead>"
	    			+ "<tr>"
	    			+ "<th><p>UserID</p></th>"
	    			+ "<th><p>Username</p></th>"
	    			+ "<th><p>Password</p></th>"
	    			+ "<th><p>Name</p></th>"
	    			+ "<th><p>Gender</p></th>"
	    			+ "<th><p>Date Of Birth</p></th>"
	    			+ "<th><p>Contact Number</p></th>"
	    			+ "<th><p>Email</p></th>"
	    			+ "<th><p>Class</p></th>"
	    			+ "<th><p>Address</p></th>"
	    			+ "<th><p>NRIC</p></th>"
	    			+ "<th><p>CCA</p></th>"
	    			+ "<th><p>TeacherID</p></th>"
	    			+ "<th><p>Department</p></th>"
	    			+ "</tr>"
	    			+ "</thead>"
	    			+ "<tbody>");
	    	
			for(UserAll u:userAllArray){
				if(userType == 1){
		    		if(u.getTeacher().getTeacherID() > 0){ //Only shows if it is teacher...
		    			out.println("<tr><td>" + u.getUser().getUserID() + "</td><td>" + u.getLogin().getUsername() + "</td><td>" + u.getLogin().getPassword() + "</td><td>" + u.getUser().getnRIC() + "</td><td>" + u.getUser().getName() + "</td><td>" + u.getUser().getGender() + "</td><td>" + u.getUser().getdOB() + "</td><td>" + u.getUser().getContactNo() + "</td><td>" + u.getUser().getEmail() + "</td><td>" + u.getUser().getSchoolClass() + "</td><td>" + u.getUser().getAddress() + "</td><td>" + u.getStudent().getcCA() + "</td><td>" + u.getTeacher().getTeacherID() + "</td><td>" + u.getTeacher().getDepartment() + "</td></tr>");
		    		}
		    	}
				else if(userType == 2){
					out.println("<tr><td>" + u.getUser().getUserID() + "</td><td>" + u.getLogin().getUsername() + "</td><td>" + u.getLogin().getPassword() + "</td><td>" + u.getUser().getnRIC() + "</td><td>" + u.getUser().getName() + "</td><td>" + u.getUser().getGender() + "</td><td>" + u.getUser().getdOB() + "</td><td>" + u.getUser().getContactNo() + "</td><td>" + u.getUser().getEmail() + "</td><td>" + u.getUser().getSchoolClass() + "</td><td>" + u.getUser().getAddress() + "</td><td>" + u.getStudent().getcCA() + "</td><td>" + u.getTeacher().getTeacherID() + "</td><td>" + u.getTeacher().getDepartment() + "</td></tr>");
				}
			}
			
			out.println("</tbody>"
					+ "</table>"
					+ "</div>"
					+ "</div>"
					+ "</div>"
					+ "<!-- jQuery library -->"
					+ "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'>"
					+ "</script>"
					+ "<!-- Latest compiled and minified JavaScript -->"
					+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa' crossorigin='anonymous'>"
					+ "</script>"
					+ "</body>"
					+ "</html>");
			
			out.close(); //PrintWriter Closing...
			dba.close(); //Database Object Closing...
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean validateName(String name){
		if(name != null && name != "")
			if(name.matches("[a-zA-Z]+"))
				return true;
			else
				return false;
		else
			return false;
	}

}
