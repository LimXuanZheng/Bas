package beforeSubmission;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class beforeSubmission
 */
@WebServlet("/beforesubmission")
public class beforeSubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public beforeSubmission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
		    			+				"<div id='whole'>"
		    			+					"<div id='first'>"
		    			+						"<a href='html/StudentUpload.html'><p id='title' style='cursor:pointer;'><img src='images/checklist.gif'>Tutorial 1A</p></a>"
		    			+						"<p id='comments'>Submit your tutorial questions here, home?</p>"
		    			+					"</div>"
		    			+					"<div id='second'>"
		    			+						"<p id='title' onclick=\"location.href='html/StudentUpload.html'\" style='cursor:pointer;'><img src='images/checklist.gif'>Practical 1A</p>"
		    			+						"<p id='comments'>Zip your practical files and submit here</p>"
		    			+					"</div>"
		    			+					"<div id='third'>"
		    			+						"<p id='title' onclick=\"location.href='html/StudentUpload.html'\" style='cursor:pointer;'><img src='images/checklist.gif'>2016 Math Paper</p>"
		    			+						"<p id='comments'>Download your exam papers here</p>"
		    			+					"</div>"
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
