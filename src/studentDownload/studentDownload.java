package studentDownload;

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

/**
 * Servlet implementation class studentDownload
 */
@WebServlet("/studentdownload")
public class studentDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public studentDownload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String key1 = "nhibgu";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			String query = "SELECT User.Keys FROM User WHERE UserID = 13;";
			ResultSet rs = dbms.getDatabaseData(query);
			while(rs.next()){
				key1 = rs.getString("Keys");
			}
			System.out.println(key1);
		}
		catch (ClassNotFoundException e) {
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
						+		"<link rel='stylesheet' href='css/studentDownload.css'>"
						+		"<title>Student Download</title>"
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
						+ 			"</nav>");
		for(database.model.File f: fileArray){
			for(String hai : f.convertRecipient()){
				if(hai.equals("13")){
					out.println(
											"<div class='whole'>"
							+				"<p style='font-size:60px; background-color:#FFA07A;'>Exam Papers</p>"
							+				"<p>Attached files : <a href='../studentdownload'><img src='images/attached.png' height='2%' width='2%'>" + f.getFileName() + "</a></p>"	
							+			"</div>"
							);
				}
				}
		}
		
		out.println(				
								"</div>"
						+	"</body>"
						+"</html>");
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
