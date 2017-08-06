package beforeSubmission;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import database.DatabaseAccess;
import database.model.UserAll;
<<<<<<< HEAD
=======
import studentDownload.studentDownload;
>>>>>>> origin/master

/**
 * Servlet implementation class beforeSubmission
 */
@WebServlet("/beforesubmission")
public class beforeSubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(beforeSubmission.class.getName());
	private String username = "Bob";
	private String teemo = null; //Here
	int teemo1 = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public beforeSubmission() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			teemo = (String)session.getAttribute("userID"); //Here 
			teemo1 = Integer.parseInt(teemo);
		}
		else {
			//response.sendRedirect("Login");
			System.out.println("Session not created - redirect to login");
		}
		
		ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
		ThreadContext.put("Username", username);
		logger.debug("entered Settings page");
		ThreadContext.clearAll();
		
		PrintWriter out = response.getWriter();
		
		String myname = "";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<UserAll> fa = dbms.getDatabaseUserAll();
			for(UserAll qq:fa){
				if(qq.getUser().getUserID() == teemo1){
					myname = qq.getUser().getName();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		

		ArrayList<database.model.File> fileArray = new ArrayList<database.model.File>();
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			fileArray = dbms.getDatabaseFile();
			database.model.File file1 = fileArray.get(0);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		
		out.println(
				"<!DOCTYPE html>"
						+ "<html>"
						+ 	"<head>"
						+ 		"<meta charset='UTF-8'>"
						+ 		"<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
						+ 		"<!-- Font Mono-Sans -->"
						+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
						+ 		"<!-- Latest compiled and minified CSS -->"
						+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>"
						+ 		"<!-- Optional theme -->"
						+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>"
						+		"<link rel='stylesheet' href='css/beforeSubmission.css'>"
						+		"<title>After Submit</title>"
						+		"<script>function goHome() {window.location('Home');}"
						+		"</script>"
						+ 	"</head>"
						+ 	"<body>"
						+ 		"<div class='container-fluid' id='Container'>"
						+ 			"<!-- Navigation -->" 
						+ 			"<nav class='navbar navbar-default'>"
						+ 				"<div class='container-fluid'>"
						+ 					"<div class='navbar-header'>"
						+ 						"<a class='navbar-brand' href='Home'>Bas?</a>"
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
						+ 						"<li id='loginBtn'>"
						+ 							"<a href='#'>Welcome, Bob.</a>"
						+ 						"</li>"
						+ 					"</ul>"
						+ 				"</div>"
						+ 			"</nav>"
						+			"<div>"
						+				"<div id='whole'>");

		for(database.model.File f: fileArray){
			for(String hai : f.convertRecipient()){
				if(hai.equals(teemo) || hai.equals(myname)){
					out.println("<div class='centralized'>"
							+ "<p class='title' onclick=\"location.href='html/StudentUpload.html'\" style='cursor:pointer;'><img src='images/checklist.gif'>" + f.getFileName() + "  Submission" + "</p></a>"
							+	"<p class='comments'>Submit your work here</p>"
							+	"<p class='comments'>Finish your work before the due date</p>"
							+	"</div>"
							);

				}
			}
		}

		out.println(				
				"</div>"
						+			"</div>"
						+		"</div>");
		out.println(
				"<p>"+ teemo + "</p>"

				);
		out.println(

				"</body>"
						+"</html>"

				);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
