package homePage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import database.DatabaseAccess;
import database.model.User;
import geoIP.CheckIP;
import messaging.ReceiveMessage;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
@MultipartConfig
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> boxes = new ArrayList<String>();
	private static String username = "Bob";
	public static String userID = null;
	public String toUser1 = "13";
	public String whetherToShowTheUserOrNot1 = null;
	public String userClicked1 = null;
	
    public Home() {
        super();
    }

    public void init() throws ServletException{
    	final String user;
    	user = "Student";
    	
    	if(user.equals("Student")){
    		boxes.add("View Grades");
    		boxes.add("Assignment Submission");
    		boxes.add("View Past Year Papers");
    	}
    	else if(user.equals("Teacher")){
    		boxes.add("Attendance");
    		boxes.add("Post Announcement");
    	}else{
    		System.out.println("Not student nor teacher? Error");
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CheckIP checkIP = new CheckIP(request);
			checkIP.redirect(response);
			checkIP.getLocation();
			//checkIP.showLocationOnGoogle(response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			e.printStackTrace();
		} //catch (ParseException e) {
		//	e.printStackTrace();
		//}
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			userID = (String)session.getAttribute("userID");
			toUser1 = (String)session.getAttribute("toUser");
			whetherToShowTheUserOrNot1 = (String)session.getAttribute("whetherToShowTheUserOrNot");
			userClicked1 = (String)session.getAttribute("userClicked");
		}
		else {
			response.sendRedirect("Login");
			System.out.println("Session not created - redirect to login");
		}
		response.setContentType("text/html;charset=UTF-8");
		ArrayList<String> notificationArray = new ArrayList<String>();
		notificationArray.add("Testing 1");
		notificationArray.add("Hello");
		notificationArray.add("Please hand in your assignment by 12/3/17 -Mr Chew");
		
		PrintWriter out = response.getWriter();
		out.println(
		"<!DOCTYPE html><html lang='en'>"
		+ "<head>"
		+ 	"<meta charset='UTF-8'>"
		+ 	"<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
		+ 	"<link rel='stylesheet' href='css/homePageStyle.css'>"
		+ 	"<!-- Font Mono-Sans -->"
		+	"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
		+ 	"<!-- Latest compiled and minified CSS -->"
		+ 	"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>"
		+ 	"<!-- Optional theme -->"
		+ 	"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>"
		+	"<!-- My Own Script -->"
		+	"<script src='script/homePageScript.js'></script>"
		+ 	"<title>Home Pages</title>");
		
		out.println(
		 "</head>"
		+ "<body>"
		+ 	"<div class='container-fluid' id='Top-Container-Background'>"
		+ 		"<div class='container-fluid' id='Top-Container'>"
		+ 			"<!-- Navigation -->"
		+ 			"<nav class='navbar navbar-default'>"
		+ 				"<div class='container-fluid'>"
		+ 					"<div class='navbar-header'>"
		+ 						"<a class='navbar-brand' href='#'>Purple</a>"
		+					"</div>"
		+					"<div class='collapse navbar-collapse'>"
		+ 						"<ul class='nav navbar-nav'>"
		+ 							"<li class='active'><a href='#'>Home</a></li>"
		+ 							"<li><a href='Directory'>Directory</a></li>"
		+ 						"</ul>"
		+ 						"<ul class='nav navbar-nav navbar-right'>"
		+							"<li class='dropdown'>"
		+					   			"<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Welcome, " + username + ". </a>"
		+					        	"<ul class='dropdown-menu'>"
		+					        		"<li><a href='Settings'>Setting</a></li>"
		+					        		"<li><a href='Login'>Logout</a></li>"
		+								"</ul>"
		+							"</li>"
		+ 						"</ul>"
		+					"</div>"
		+ 				"</div>"
		+ 			"</nav>"
		+ 			"<!-- Center Words -->"
		+ 			"<div id='Top-Center-Container'>"
		+ 				"<h2 id='welcomeText'>Welcome to BasChunMaen!</h2>"
		+ 			"</div>"
		+		"</div>"
		+ 	"</div>"
		+ 	"<!-- Notification Tab -->"
		+	"<span class='badge' id='notificationBadge'>" + notificationArray.size() + "</span>"
		+	"<div id='notificationTab' onclick='animateNotification()'>"
		+ 		"<div id='HeadBox'>"
		+			 "<p id='notiIcon'><span class='glyphicon glyphicon-bullhorn'></span></p>"
		+ 		"</div>"
		+ 		"<div id='InfoBox'>");
		
		//Input notification's here example: 
		//<div>Please Handle in you paper by 12/3/17 -Mr Chew</div>
		//<div>Just wanted to say Hi! -Ms Tay</div>
		for(String s:notificationArray){
			out.print("<div>" + s + "</div>");
		}
		
		out.print(
		 		"</div>"
		+ 	"</div>"
		+ 	"<!-- Bottom Naviagtion -->"
		+ 	"<div class='container-fluid' id='Bot-Container'>"
		+ 		"<div class='row' id='Bot-Container-Container'>");
		
		for(String s:boxes){
			out.print("<div class='col-md-2'");
			switch(s){
				case "Assignment Submission":
					out.print("onclick=\"location.href='beforesubmission'\" style=\"cursor: pointer;\"");
					break;
				case "View Past Year Papers":
					out.print("onclick=\"location.href='html/studentDownload.html'\" style=\"cursor: pointer;\"");
					break;
			}
			out.print(">"
					+ 	"<p>" + s + "</p>"
					+ "</div>");
		}
		
		out.println(""
		+ 		"</div>"
		+ 	"</div>");
		
		if(Integer.parseInt(userID) > 10){
			out.println(
					"<div id='wholeChatBox'>"
			+		"<div id='topBarOfChatBox'>"
			+ 			"<div id='leftFloat'>");
			
			if(whetherToShowTheUserOrNot1 != null){
				out.println("<p id='userToName'>" + whetherToShowTheUserOrNot1 + "</p>");
			}else{
				out.println("<p id='userToName'>{placeholder}</p>");
			}
			
			out.println(
						"</div>"
			+ 			"<div id='rightFloat'>"
			+ 				"<span class='glyphicon glyphicon-minus' onclick='minimiseChat()'></span>"
			+ 				"<span class='glyphicon glyphicon-remove' onclick='closeChat()'></span>"
			+ 			"</div>"
			+		 "</div>"
			+ 		"<div id='chatBox'>"
			+ 			"<iframe src='ReceiveMessage' id='receiveMessageBox' scrolling='no'></iframe>"
			+ 			"<iframe src='SendMessage' id='sendMessageInput' scrolling='no'></iframe>"
			+ 		"</div>"
			+ 	"</div>"
			+ 	"<div id='leftUserList'>"
			+ 		"<form action='Home' id='someform' method='POST'>"
			+ 			"<input type='hidden' id='hiddenInput' name='hiddenInput'>"
			+ 			"<input type='hidden' id='hiddenInput1' name='hiddenInputName'>"
			+ 			"<input type='hidden' id='hiddenInput2' name='hiddenInputShown'>");
			
			ArrayList<User> userArray= new ArrayList<User>();
			try {
				DatabaseAccess dba = new DatabaseAccess(1);
				userArray = dba.getDatabaseUser();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
			for(User u:userArray){
				if(u.getUserID() > 10 && u.getUserID() != Integer.parseInt(userID)){
					out.println(
							"<div class='usersOfChat' onclick='changeUser(this)'>"
					+ 			"<span class='glyphicon glyphicon-user'></span>"
					+ 			"<p>" + u.getName() + "</p>"
					+			"<p style='display: none;'>" + u.getUserID() + "</p>"
					+ 		"</div>");
				}
			}
			out.println("</div>");
			
			if(userClicked1 != null){
				if(userClicked1.equalsIgnoreCase("Show")){
					out.println("<script>document.getElementById(\"wholeChatBox\").style.display = 'block';</script>");
				}else{
					out.println("<script>document.getElementById(\"wholeChatBox\").style.display = 'none';</script>");
				}
			}
		}
		
		out.println(
		 	"<!-- jQuery library -->"
		+ 	"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
		+ 	"<!-- Latest compiled and minified JavaScript -->"
		+ 	"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa' crossorigin='anonymous'></script>"
		+ "</body>"
		+ "</html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toUser = request.getParameter("hiddenInput");
		HttpSession session = request.getSession();
		session.setAttribute("toUser", toUser);
		String userClicked = request.getParameter("hiddenInputName");
		session.setAttribute("userClicked", userClicked);
		String whetherToShowTheUserOrNot = request.getParameter("hiddenInputShown");
		session.setAttribute("whetherToShowTheUserOrNot", whetherToShowTheUserOrNot);
		ReceiveMessage.arrayString.clear();
		response.setIntHeader("refresh", 1);
	}
}
