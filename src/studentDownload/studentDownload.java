package studentDownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.ResultSet;
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
import fileUpload.CryptoException;
import teacherSharing.decryption;
import teacherSharing.teacherUpload;

/**
 * Servlet implementation class studentDownload
 */
@WebServlet("/studentdownload")
public class studentDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(studentDownload.class.getName());
	private String username = "Bob";
	private String teemo = null;
	int teemo1 = 0;
	


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public studentDownload() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			teemo = (String)session.getAttribute("userID");
			teemo1 = Integer.parseInt(teemo);
		}
		else {
			//response.sendRedirect("Login");
			System.out.println("Session not created - redirect to login");
		}
		
		ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
		ThreadContext.put("Username", username);
		logger.debug("entered Student Download page");
		ThreadContext.clearAll();
		
		PrintWriter out = response.getWriter();
		
		String key1 = "nhibgu";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			String query = "SELECT User.Keys FROM User WHERE UserID = " + teemo1 + ";";
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
						+		"function dosth(fgfg){"
						+			"document.getElementById('pls').value = fgfg;"
						+			"document.getElementById('downloadfile1').submit();"
						+			"}"
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
						+			"<form id='downloadfile1' action='studentdownload' method='post'>");
		for(database.model.File f: fileArray){
			for(String hai : f.convertRecipient()){
				if(hai.equals(Integer.toString(teemo1))){
					out.println(
											"<div class='whole'>"
							+				"<p style='font-size:60px; background-color:#FFA07A;'>Exam Papers</p>"
							+				"<p>Attached files : <button onclick='dosth(" + f.getFileID() + ")'><img src='images/attached.png' height='2%' width='2%'>" + f.getFileName() + "</button></p>"	
							+				"<input type='hidden' name='index' id='pls'></input>"
							+			"</div>"
							);
				}
				}
		}
		
		out.println(		"</form>"		
						+		"</div>"
						+	"</body>"
						+"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key1 = "nhibgu";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			String query = "SELECT User.Keys FROM User WHERE UserID = " + teemo1 + ";";
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
		//PrintWriter out = response.getWriter();
		int sss = Integer.parseInt(request.getParameter("index"));
		System.out.println(sss);
		try{
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<database.model.File> fileArray = dbms.getDatabaseFile();
			for(database.model.File d: fileArray){
				if(d.getFileID() == sss){

					File tempFile = File.createTempFile(d.getFileName(), ".tmp", null);
					FileOutputStream fos = new FileOutputStream(tempFile);
					fos.write(d.getFileData());
					File decryptedFile = new File("document.decrypted");
					try {
						decryption.decrypt(key1, tempFile, decryptedFile);
					} catch (CryptoException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
						System.out.println("fail");
					}
					/*FileInputStream hh = new FileInputStream(decryptedFile);
			        OutputStream h = response.getOutputStream();
			       
			        byte[] test = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = hh.read(test)) != -1) {
			                           h.write(test, 0, bytesRead);
			                       }
			                       */
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();  
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition", "attachment;filename=\"" + d.getFileName() +"\"");
					FileInputStream stream = new FileInputStream(decryptedFile);
					int i;   
					while ((i=stream.read()) != -1) {  
					out.write(i);   
					}   
					stream.close();   
					out.close();
					
					ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
					ThreadContext.put("Username", username);
					logger.debug("downloaded a file");
					ThreadContext.clearAll();
					
					//byte[] bytesArray = new byte[(int) decryptedFile.length()];
					//response.getOutputStream().write(bytesArray);
				}
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
