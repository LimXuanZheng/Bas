package teacherSharing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileUpload.CryptoException;
import fileUpload.encryption;

/**
 * Servlet implementation class teacherSharing
 */
@WebServlet("/teachersharing")
public class teacherSharing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherSharing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
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
    			+		"<link rel='stylesheet' href='css/teacherSharing.css'>"
    			+		"<title>TeacherSharing</title>"
    			+		"<script src='script/teacher.js'></script>"
    			+ 	"</head>"
    			+ 	"<body id='body'>"
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
    			+			"<div id='overlay'>"
    			+				"<div id='content1'>"
    			+					"<form>"
    			+						"<input type='checkbox' name='my'>My Drive</input>"
    			+						"<input type='checkbox' name='share'>Shared Drive</input>"
    			+						"<input type='checkbox' id='checkifnew' name='NEW'>New Drive</input>"
    			+						"<br><label class='custom-file'>"
				+							"<input type='file' id='datafilepath' class='custom-file-input' name='datafile' style='font-size:20px;'>"
				+							"<span class='custom-file-control'></span>"
				+						"</label>"
    			+						"<br>Name of File: <input type='text' name='FName'></input>"
    			+					"</form>"
    			+					"<button id='confirm' onclick='confirm();'>Confirm</button>"
    			+					"<button id='cancel' onclick='cancel()'>Cancel</button>"
    			+				"</div>"
    			+			"</div>"
    			+			"<div>"
    			+				"<div class='contents' id='storeButton'>"
    			+					"<button class='button' id='newbutton' onclick='createNew()'>New</button>"
    			+					"<button class='button'>My Drive</button>"
    			+					"<button class='button' id='lastOne'>Shared Drive</button>"
    			+				"</div>"
    			+				"<div class='contents'>"
    			+					"<table>"
    			+						"<tr>"
    			+							"<th>Name</th>"
    			+							"<th>Owner</th>"
    			+							"<th>Last Modified</th>"
    			+							"<th>File Size</th>"
    			+						"</tr>"
    			+						"<tr>"
    			+							"<td>nothing to display</td>"
    			+							"<td>nothing to display</td>"
    			+							"<td>nothing to display</td>"
    			+							"<td>nothing to display</td>"
    			+						"</tr>"
    			+					"</table>"
    			+				"</div>"
    			+			"</div>"
    			+		"</div>"
    			+	"</body>"
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
