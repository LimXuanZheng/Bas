package directory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import database.model.UserAll;

/**
 * Servlet implementation class Directory
 */
@WebServlet("/Directory")
public class Directory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//private DataTable dataTable;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/directory.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get and validate name.
        String name = request.getParameter("search");
        // Get schoolClass
    	String schoolClass = request.getParameter("schoolClass");
    	//Get Subject
    	String subject = request.getParameter("subject");
    	ArrayList<UserAll> userAllArray = new ArrayList<UserAll>();
    	try {
			DatabaseAccess dba = new DatabaseAccess();
			userAllArray = dba.getDatabaseUserAll();
    	
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'><link rel='stylesheet' href='css/directoryStyle.css'><!-- Font Mono-Sans --><link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'><!-- Latest compiled and minified CSS --><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'><!-- Optional theme --><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'><title>Directory</title></head><body><div class='container-fluid' id='Container'><!-- Navigation --><nav class='navbar navbar-default'><div class='container-fluid'><div class='navbar-header'><a class='navbar-brand' href='Home'>Bas?</a></div><ul class='nav navbar-nav'><li><a href='Home'>Home</a></li><li class='active'><a href='#'>Directory</a></li></ul><ul class='nav navbar-nav navbar-right'><li id='loginBtn'><a href='#'>Welcome, Bob.</a></li></ul></div></nav><div id='Container-Container'><div class='container-fluid' id='Right-Container'><table class='table table-striped'><thead><tr><th><p>UserID</p></th><th><p>Username</p></th><th><p>Password</p></th><th><p>Name</p></th></th><th><p>Gender</p></th></th><th><p>Date Of Birth</p></th></th><th><p>Contact Number</p></th></th><th><p>Email</p></th></th><th><p>Class</p></th></th><th><p>Address</p></th></th><th><p>NRIC</p></th></th><th><p>CCA</p></th></th><th><p>TeacherID</p></th></th><th><p>Department</p></th></tr></thead><tbody>");
			for(UserAll u:userAllArray){
				out.println("<tr><td>" + u.getUser().getUserID() + "</td><td>" + u.getLogin().getUsername() + "</td><td>" + u.getLogin().getPassword() + "</td><td>" + u.getUser().getName() + "</td><td>" + u.getUser().getGender() + "</td><td>" + u.getUser().getdOB() + "</td><td>" + u.getUser().getContactNo() + "</td><td>" + u.getUser().getEmail() + "</td><td>" + u.getUser().getSchoolClass() + "</td><td>" + u.getUser().getAddress() + "</td><td>" + u.getStudent().getnRIC() + "</td><td>" + u.getStudent().getcCA() + "</td><td>" + u.getTeacher().getTeacherID() + "</td><td>" + u.getTeacher().getTeacherID() + "</td></tr>");
			}
			out.println("</tbody></table></div></div></div><!-- jQuery library --><script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script><!-- Latest compiled and minified JavaScript --><script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa' crossorigin='anonymous'></script></body></html>");
			out.close();
			dba.close();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean validateName(String name){
		if(name != null)
			if(name.matches("[a-zA-Z]+"))
				return true;
			else
				return false;
		else
			return false;
	}
}
