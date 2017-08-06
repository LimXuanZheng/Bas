package login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

import javax.servlet.ServletConfig;
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
import geoIP.CheckIP;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(LoginServlet.class.getName());
	private String username = "Bob";
	String userID = null;
	String location = null;
       
    public LoginServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	String logFile = System.getProperty("user.home");
    	File file = new File(logFile, "/Documents/GitHub/Bas/WebContent/WEB-INF/classes/log4j2.xml");
    	ArrayList<String> arr = new ArrayList<String>();
    	try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				arr.add(sc.nextLine());
			}
			arr.set(3, "<File name=\"MyFile\" filename=\"" + logFile + "\\Documents\\GitHub\\purplebackup\\AuditLog.log" + "\">");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter outFile = new PrintWriter(bw);
			for (String str : arr) {
				outFile.println(str);
			}
			sc.close();
			outFile.close();
			bw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String previousURL = request.getHeader("referer");
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			location = (String)session.getAttribute("location");
		}
		else {
			//response.sendRedirect("Login");
			System.out.println("Session not created - redirect to login");
		}
		
		if (previousURL != null) {
			ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
			ThreadContext.put("Username", username);
			ThreadContext.put("Location", location);
			logger.debug("logged out successfully");
			ThreadContext.clearAll();
			session.invalidate();
		}
		
		try {
			CheckIP checkIP = new CheckIP(request);
			checkIP.redirect(response);
			//checkIP.getLocation();
			//checkIP.showLocationOnGoogle(response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ 	"<head>"
				+ 		"<meta charset='UTF-8'>"
				+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
				+ 		"<link rel='stylesheet' href='css/Login.css'>"
				+ 		"<script src='script/LoginScript.js'></script>"
				+ 		"<title>Login</title>"
				+ 	"</head>"
				+ 	"<body onload='getLocation()'>"
				+ 		"<div id='theDiv'>"
				+ 			"<div id='aDiv'>"
				+ 				"<div id='bDiv'>"
				+ 					"<div id='basImgDiv'>"
				+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
				+ 					"</div>"
				+ 					"<h3 id='basTitle'>"
				+ 						"Purpleboard"
				+ 					"</h3>"
				+ 				"</div>"
				+ 				"<div id='cDiv'>"
				+ 					"<div id='loginHeaderDiv'>"
				+ 						"<h3 id='loginHeader'>"
				+ 							"Log in to your account"
				+ 						"</h3>"
				+ 					"</div>"
				+ 					"<form id='loginForm' method='POST'>"
				+ 						"<div id='namePart'>"
				+ 							"<div id='flexUInput'>"
				+ 								"<img id='nameImg' src='images/Username.png'/>"
				+ 								"<input type='text' id='userID' class='input' name='userID' placeholder='Username' required>"
				+ 							"</div>"
				+ 						"</div>"
				+ 						"<div id='passPart'>"
				+ 							"<div id='flexPInput'>"
				+ 								"<img id='passImg' src='images/Password.png'/>"
				+ 								"<input type='password' id='passID' class='input' name='passID' placeholder='Password' required>"
				+ 							"</div>"
				+ 						"</div>"
				+ 						"<div id='btnDiv'>"
				+ 							"<span id='forPass' onclick='showPopup()'>Forgot your Password?</span>"
				+ 							"<button type='submit' name='postBtn' value='btnLogin' id='btnLogin'>Login</button>"
				+ 						"</div>"
				+						"<input type='hidden' id='latlongLocation' name='latlongLocation'>"
				+ 					"</form>"
				+ 				"</div>"
				+ 			"</div>"
				+ 		"</div>"
				+ 		"<div id='popupDiv'>"
				+ 			"<div id='popupContent'>"
				+ 				"<div id='popupHeader'>"
				+ 					"<span id='close' onclick='closePopup()'>&times</span>"
				+ 					"<h2 id='popupTitle'>Forgot your Password?</h2>"
				+ 				"</div>"
				+				"<form id='popupForm' method='POST'>"
				+ 					"<div id='popupBody'>"
				+ 						"<div id='pInputDiv'>"
				+ 							"<input type='email' id='changeEmail' name='changeEmail' placeholder='Email' required>"
				//+ 							"<div id='pInputError'>Please enter your email.</div>"
				+ 						"</div>"
				+ 						"<p class='popupWords'>Enter your email address.</p>"
				+ 						"<p class='popupWords'>Click the button for a new password to be sent to your email.</p>"
				+ 					"</div>"
				+ 					"<div id='popupFooter'>"
				+ 						"<div id='pBtnDiv'>"
				+ 							"<button type='submit' name='postBtn' value='popupBtn' id='popupBtn'>Change Password</button>"
				+ 						"</div>"
				+ 					"</div>"
				+				"</form>"
				+ 			"</div>"
				+ 		"</div>"
				+ 	"</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Base64.Encoder enc = Base64.getEncoder();
		String username = request.getParameter("userID");
		String password = request.getParameter("passID");
		String receipientEmail = request.getParameter("changeEmail");
		String postBtn = request.getParameter("postBtn");
		String latlongLocation = request.getParameter("latlongLocation");
		LoginModel LM = new LoginModel();
		String location = latlongLocation;
		
		ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
		ThreadContext.put("Username", username);
		logger.debug("latlongLocation");
		ThreadContext.clearAll();
		
		if (postBtn == null) {
			System.out.println("Button not clicked");
		}
		else if (postBtn.equals("popupBtn")) {
			System.out.println("Email button clicked");
			LM.generateNewPass();
			LM.sendEmail(receipientEmail);
			System.out.println(receipientEmail);
			HashPass HP = new HashPass();
			byte [] resetSalt = HP.createSalt();
			String newSaltStr = enc.encodeToString(resetSalt);
			String resetHashedPassword = HP.getHashedPassword(LM.getNewPass(), resetSalt);
			//Update the newly generated salt and hashed password to database given the email entered
			try {
				DatabaseAccess dBA = new DatabaseAccess(1);
				String sqlLine1 = "UPDATE Login SET Login.salt = \"" + newSaltStr + "\" WHERE userID = (SELECT UserId FROM User WHERE User.email = \"" + receipientEmail + "\");";
				String sqlLine2 = "UPDATE Login SET Login.password = \"" + resetHashedPassword + "\" WHERE userID = (SELECT UserId FROM User WHERE User.email = \"" + receipientEmail + "\");";
				dBA.updateDatabaseData(sqlLine1);
				dBA.updateDatabaseData(sqlLine2);
				dBA.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>"
					+ "<html>"
					+ 	"<head>"
					+ 		"<meta charset='UTF-8'>"
					+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
					+ 		"<link rel='stylesheet' href='css/Login.css'>"
					+ 		"<script src='script/LoginScript.js'></script>"
					+ 		"<title>Login</title>"
					+		"<style>"
					+ 			"#snackbar { min-width: 250px; margin-left: -130px; background-color: #333; color: #FFF; text-align: center; border-radius: 2px; padding: 16px; position: fixed; z-index: 1; left: 50%; bottom: 30px; font-size: 17px; }"
					+ 		"</style>"
					+ 	"</head>"
					+ 	"<body onload='getLocation()'>"
					+ 		"<div id='theDiv'>"
					+ 			"<div id='aDiv'>"
					+ 				"<div id='bDiv'>"
					+ 					"<div id='basImgDiv'>"
					+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
					+ 					"</div>"
					+ 					"<h3 id='basTitle'>"
					+ 						"Purpleboard"
					+ 					"</h3>"
					+ 				"</div>"
					+ 				"<div id='cDiv'>"
					+ 					"<div id='loginHeaderDiv'>"
					+ 						"<h3 id='loginHeader'>"
					+ 							"Log in to your account"
					+ 						"</h3>"
					+ 					"</div>"
					+ 					"<form id='loginForm' method='POST'>"
					+ 						"<div id='namePart'>"
					+ 							"<div id='flexUInput'>"
					+ 								"<img id='nameImg' src='images/Username.png'/>"
					+ 								"<input type='text' id='userID' class='input' name='userID' placeholder='Username' required>"
					+ 							"</div>"
					+ 						"</div>"
					+ 						"<div id='passPart'>"
					+ 							"<div id='flexPInput'>"
					+ 								"<img id='passImg' src='images/Password.png'/>"
					+ 								"<input type='password' id='passID' class='input' name='passID' placeholder='Password' required>"
					+ 							"</div>"
					+ 						"</div>"
					+ 						"<div id='btnDiv'>"
					+ 							"<span id='forPass' onclick='showPopup()'>Forgot your Password?</span>"
					+ 							"<button type='submit' name='postBtn' value='btnLogin' id='btnLogin'>Login</button>"
					+ 						"</div>"
					+						"<input type='hidden' id='latlongLocation' name='latlongLocation'>"
					+ 					"</form>"
					+ 				"</div>"
					+ 			"</div>"
					+			"<div id='snackbar'>"
					+				"Password changed successfully"
					+			"</div>"
					+ 		"</div>"
					+ 		"<div id='popupDiv'>"
					+ 			"<div id='popupContent'>"
					+ 				"<div id='popupHeader'>"
					+ 					"<span id='close' onclick='closePopup()'>&times</span>"
					+ 					"<h2 id='popupTitle'>Forgot your Password?</h2>"
					+ 				"</div>"
					+				"<form id='popupForm' method='POST'>"
					+ 					"<div id='popupBody'>"
					+ 						"<div id='pInputDiv'>"
					+ 							"<input type='email' id='changeEmail' name='changeEmail' placeholder='Email' required>"
					//+ 							"<div id='pInputError'>Please enter your email.</div>"
					+ 						"</div>"
					+ 						"<p class='popupWords'>Enter your email address.</p>"
					+ 						"<p class='popupWords'>Click the button for a new password to be sent to your email.</p>"
					+ 					"</div>"
					+ 					"<div id='popupFooter'>"
					+ 						"<div id='pBtnDiv'>"
					+ 							"<button type='submit' name='postBtn' value='popupBtn' id='popupBtn'>Change Password</button>"
					+ 						"</div>"
					+ 					"</div>"
					+				"</form>"
					+ 			"</div>"
					+ 		"</div>"
					+ 	"</body>"
					+ "</html>");
		}
		else if (postBtn.equals("btnLogin")) {
			System.out.println("Password button clicked");
			if (username.contains("<script>") && username.contains("</script>")) {
				username = null;
				System.out.println("Attempted cross-site scripting");
				ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
				ThreadContext.put("Username", username);
				ThreadContext.put("Location", location);
				logger.debug("Attempted cross-site scripting");
				ThreadContext.clearAll();
				
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>"
						+ "<html>"
						+ 	"<head>"
						+ 		"<meta charset='UTF-8'>"
						+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
						+ 		"<link rel='stylesheet' href='css/Login.css'>"
						+ 		"<script src='script/LoginScript.js'></script>"
						+ 		"<title>Login</title>"
						+ 	"</head>"
						+ 	"<body onload='getLocation()'>"
						+ 		"<div id='theDiv'>"
						+ 			"<div id='aDiv'>"
						+				"<div id='bDiv'>"
						+					"<div id='basImgDiv'>"
						+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
						+ 					"</div>"
						+ 					"<h3 id='basTitle'>"
						+ 						"Purpleboard"
						+ 					"</h3>"
						+ 				"</div>"
						+ 				"<div id='cDiv'>"
						+ 					"<div id='loginHeaderDiv'>"
						+ 						"<h3 id='loginHeader'>"
						+ 							"Log in to your account"
						+ 						"</h3>"
						+ 					"</div>"
						+ 					"<form id='loginForm' method='POST'>"
						+ 						"<div id='namePart'>"
						+ 							"<div id='flexUInput'>"
						+ 								"<img id='nameImg' src='images/Username.png'/>"
						+ 								"<input type='text' id='userID' class='input' name='userID' placeholder='Username' required>"
						+ 							"</div>"
						+ 							"<div id='inputNError'>Cross-site scripting detected.</div>"
						+ 						"</div>"
						+ 						"<div id='passPart'>"
						+ 							"<div id='flexPInput'>"
						+ 								"<img id='passImg' src='images/Password.png'/>"
						+ 								"<input type='password' id='passID' class='input' name='passID' placeholder='Password' required>"
						+ 							"</div>"
						+ 						"</div>"
						+ 						"<div id='btnDiv'>"
						+ 							"<span id='forPass' onclick='showPopup()'>Forgot your Password?</span>"
						+ 							"<button type='submit' name='postBtn' value='btnLogin' id='btnLogin'>Login</button>"
						+ 						"</div>"
						+						"<input type='hidden' id='latlongLocation' name='latlongLocation'>"
						+ 					"</form>"
						+ 				"</div>"
						+ 			"</div>"
						+ 		"</div>"
						+ 		"<div id='popupDiv'>"
						+ 			"<div id='popupContent'>"
						+ 				"<div id='popupHeader'>"
						+ 					"<span id='close' onclick='closePopup()'>&times</span>"
						+ 					"<h2 id='popupTitle'>Forgot your Password?</h2>"
						+ 				"</div>"
						+				"<form id='popupForm' method='POST'>"
						+ 					"<div id='popupBody'>"
						+ 						"<div id='pInputDiv'>"
						+ 							"<input type='email' id='changeEmail' name='changeEmail' placeholder='Email' required>"
						//+ 							"<div id='pInputError'>Please enter your email.</div>"
						+ 						"</div>"
						+ 						"<p class='popupWords'>Enter your email address.</p>"
						+ 						"<p class='popupWords'>Click the button for a new password to be sent to your email.</p>"
						+ 					"</div>"
						+ 					"<div id='popupFooter'>"
						+ 						"<div id='pBtnDiv'>"
						+ 							"<button type='submit' name='postBtn' value='popupBtn' id='popupBtn'>Change Password</button>"
						+ 						"</div>"
						+ 					"</div>"
						+				"</form>"
						+ 			"</div>"
						+ 		"</div>"
						+ 	"</body>"
						+ "</html>");
			}
			else if (password.contains("</script>") && password.contains("</script>")) {
				username = null;
				System.out.println("Attempted cross-site scripting");
				ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
				ThreadContext.put("Username", username);
				ThreadContext.put("Location", location);
				logger.debug("Attempted cross-site scripting");
				ThreadContext.clearAll();
				
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>"
						+ "<html>"
						+ 	"<head>"
						+ 		"<meta charset='UTF-8'>"
						+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
						+ 		"<link rel='stylesheet' href='css/Login.css'>"
						+ 		"<script src='script/LoginScript.js'></script>"
						+ 		"<title>Login</title>"
						+ 	"</head>"
						+ 	"<body onload='getLocation()'>"
						+ 		"<div id='theDiv'>"
						+ 			"<div id='aDiv'>"
						+ 				"<div id='bDiv'>"
						+ 					"<div id='basImgDiv'>"
						+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
						+ 					"</div>"
						+ 					"<h3 id='basTitle'>"
						+ 						"Purpleboard"
						+ 					"</h3>"
						+ 				"</div>"
						+ 				"<div id='cDiv'>"
						+ 					"<div id='loginHeaderDiv'>"
						+ 						"<h3 id='loginHeader'>"
						+ 							"Log in to your account"
						+ 						"</h3>"
						+	 				"</div>"
						+ 					"<form id='loginForm' method='POST'>"
						+ 						"<div id='namePart'>"
						+ 							"<div id='flexUInput'>"
						+ 								"<img id='nameImg' src='images/Username.png'/>"
						+ 								"<input type='text' id='userID' class='input' name='userID' placeholder='Username' required>"
						+ 							"</div>"
						+						"</div>"
						+ 						"<div id='passPart'>"
						+ 							"<div id='flexPInput'>"
						+ 								"<img id='passImg' src='images/Password.png'/>"
						+ 								"<input type='password' id='passID' class='input' name='passID' placeholder='Password' required>"
						+ 							"</div>"
						+ 							"<div id='inputPError'>Cross-site scripting detected.</div>"
						+ 						"</div>"
						+ 						"<div id='btnDiv'>"
						+ 							"<span id='forPass' onclick='showPopup()'>Forgot your Password?</span>"
						+ 							"<button type='submit' name='postBtn' value='btnLogin' id='btnLogin'>Login</button>"
						+ 						"</div>"
						+						"<input type='hidden' id='latlongLocation' name='latlongLocation'>"
						+ 					"</form>"
						+ 				"</div>"
						+ 			"</div>"
						+ 		"</div>"
						+ 		"<div id='popupDiv'>"
						+ 			"<div id='popupContent'>"
						+ 				"<div id='popupHeader'>"
						+ 					"<span id='close' onclick='closePopup()'>&times</span>"
						+ 					"<h2 id='popupTitle'>Forgot your Password?</h2>"
						+ 				"</div>"
						+				"<form id='popupForm' method='POST'>"
						+ 					"<div id='popupBody'>"
						+ 						"<div id='pInputDiv'>"
						+ 							"<input type='email' id='changeEmail' name='changeEmail' placeholder='Email' required>"
						//+ 							"<div id='pInputError'>Please enter your email.</div>"
						+ 						"</div>"
						+ 						"<p class='popupWords'>Enter your email address.</p>"
						+ 						"<p class='popupWords'>Click the button for a new password to be sent to your email.</p>"
						+ 					"</div>"
						+ 					"<div id='popupFooter'>"
						+ 						"<div id='pBtnDiv'>"
						+ 							"<button type='submit' name='postBtn' value='popupBtn' id='popupBtn'>Change Password</button>"
						+ 						"</div>"
						+ 					"</div>"
						+				"</form>"
						+ 			"</div>"
						+ 		"</div>"
						+ 	"</body>"
						+ "</html>");
			}
			else {
				try {
					DatabaseAccess dba = new DatabaseAccess(1);
					String sqlline = "SELECT Login.UserID, Login.Username, Login.Password, Login.Salt FROM Login WHERE Username = ?;";
					ResultSet login = dba.getDatabaseData(sqlline, username);
					
					if (login.next()) {
						//Base64.Decoder dnc = Base64.getDecoder();
						//byte [] saltDecoded = dnc.decode(login.getString("Salt"));
						HashPass HP = new HashPass();
						byte [] saltDecoded = HP.getDecodedSalt(login.getString("Salt"));
						String hashedPassword = HP.getHashedPassword(password, saltDecoded);
						
						if (login.getString("Password").equals(hashedPassword)) {
							String userIDLine = "SELECT User.userID FROM User INNER JOIN Login ON User.UserID = Login.UserID WHERE Login.Username = \"" + username + "\";";
							ResultSet rs = dba.getDatabaseData(userIDLine);
							rs.next();
							userID = rs.getString("userID");
							System.out.println(userID);
							System.out.println("Entered If");
							HttpSession session = request.getSession();
							session.setAttribute("username", username);
							session.setAttribute("userID", userID);
							session.setAttribute("location", location);
							response.sendRedirect("Home");
							
							ThreadContext.put("IP", (InetAddress.getLocalHost()).toString());
							ThreadContext.put("Username", username);
							ThreadContext.put("Location", location);
							logger.debug("logged in successfully");
							ThreadContext.clearAll();
						}
						else {
							System.out.println("Entered Else");
							PrintWriter out = response.getWriter();
							out.println("<!DOCTYPE html>"
									+ "<html>"
									+ 	"<head>"
									+ 		"<meta charset='UTF-8'>"
									+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
									+ 		"<link rel='stylesheet' href='css/Login.css'>"
									+ 		"<script src='script/Login.js'></script>"
									+ 		"<title>Login</title>"
									+ 	"</head>"
									+ 	"<body onload='getLocation()'>"
									+ 		"<div id='theDiv'>"
									+ 			"<div id='aDiv'>"
									+ 				"<div id='bDiv'>"
									+ 					"<div id='basImgDiv'>"
									+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
									+ 					"</div>"
									+ 					"<h3 id='basTitle'>"
									+ 						"Purpleboard"
									+ 					"</h3>"
									+ 				"</div>"
									+ 				"<div id='cDiv'>"
									+ 					"<div id='loginHeaderDiv'>"
									+ 						"<h3 id='loginHeader'>"
									+ 							"Log in to your account"
									+ 						"</h3>"
									+	 				"</div>"
									+ 					"<form id='loginForm' method='POST'>"
									+ 						"<div id='namePart'>"
									+ 							"<div id='flexUInput'>"
									+ 								"<img id='nameImg' src='images/Username.png'/>"
									+ 								"<input type='text' id='userID' class='input' name='userID' placeholder='Username' required>"
									+ 							"</div>"
									+						"</div>"
									+ 						"<div id='passPart'>"
									+ 							"<div id='flexPInput'>"
									+ 								"<img id='passImg' src='images/Password.png'/>"
									+ 								"<input type='password' id='passID' class='input' name='passID' placeholder='Password' required>"
									+ 							"</div>"
									+ 							"<div id='inputPError'>Password does not match.</div>"
									+ 						"</div>"
									+ 						"<div id='btnDiv'>"
									+ 							"<span id='forPass' onclick='showPopup()'>Forgot your Password?</span>"
									+ 							"<button type='submit' name='postBtn' value='btnLogin' id='btnLogin'>Login</button>"
									+ 						"</div>"
									+						"<input type='hidden' id='latlongLocation' name='latlongLocation'>"
									+ 					"</form>"
									+ 				"</div>"
									+ 			"</div>"
									+ 		"</div>"
									+ 		"<div id='popupDiv'>"
									+ 			"<div id='popupContent'>"
									+ 				"<div id='popupHeader'>"
									+ 					"<span id='close' onclick='closePopup()'>&times</span>"
									+ 					"<h2 id='popupTitle'>Forgot your Password?</h2>"
									+ 				"</div>"
									+				"<form id='popupForm' method='POST'>"
									+ 					"<div id='popupBody'>"
									+ 						"<div id='pInputDiv'>"
									+ 							"<input type='email' id='changeEmail' name='changeEmail' placeholder='Email' required>"
									//+ 							"<div id='pInputError'>Please enter your email.</div>"
									+ 						"</div>"
									+ 						"<p class='popupWords'>Enter your email address.</p>"
									+ 						"<p class='popupWords'>Click the button for a new password to be sent to your email.</p>"
									+ 					"</div>"
									+ 					"<div id='popupFooter'>"
									+ 						"<div id='pBtnDiv'>"
									+ 							"<button type='submit' name='postBtn' value='popupBtn' id='popupBtn'>Change Password</button>"
									+ 						"</div>"
									+ 					"</div>"
									+				"</form>"
									+ 			"</div>"
									+ 		"</div>"
									+ 	"</body>"
									+ "</html>");
							}
					}
					else {
						System.out.println("Username does not match");
						PrintWriter out = response.getWriter();
						out.println("<!DOCTYPE html>"
								+ "<html>"
								+ 	"<head>"
								+ 		"<meta charset='UTF-8'>"
								+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
								+ 		"<link rel='stylesheet' href='css/Login.css'>"
								+ 		"<script src='script/LoginScript.js'></script>"
								+ 		"<title>Login</title>"
								+ 	"</head>"
								+ 	"<body onload='getLocation()'>"
								+ 		"<div id='theDiv'>"
								+ 			"<div id='aDiv'>"
								+				"<div id='bDiv'>"
								+					"<div id='basImgDiv'>"
								+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
								+ 					"</div>"
								+ 					"<h3 id='basTitle'>"
								+ 						"Purpleboard"
								+ 					"</h3>"
								+ 				"</div>"
								+ 				"<div id='cDiv'>"
								+ 					"<div id='loginHeaderDiv'>"
								+ 						"<h3 id='loginHeader'>"
								+ 							"Log in to your account"
								+ 						"</h3>"
								+ 					"</div>"
								+ 					"<form id='loginForm' method='POST'>"
								+ 						"<div id='namePart'>"
								+ 							"<div id='flexUInput'>"
								+ 								"<img id='nameImg' src='images/Username.png'/>"
								+ 								"<input type='text' id='userID' class='input' name='userID' placeholder='Username' required>"
								+ 							"</div>"
								+ 							"<div id='inputNError'>Username does not match.</div>"
								+ 						"</div>"
								+ 						"<div id='passPart'>"
								+ 							"<div id='flexPInput'>"
								+ 								"<img id='passImg' src='images/Password.png'/>"
								+ 								"<input type='password' id='passID' class='input' name='passID' placeholder='Password' required>"
								+ 							"</div>"
								+ 						"</div>"
								+ 						"<div id='btnDiv'>"
								+ 							"<span id='forPass' onclick='showPopup()'>Forgot your Password?</span>"
								+ 							"<button type='submit' name='postBtn' value='btnLogin' id='btnLogin'>Login</button>"
								+ 						"</div>"
								+						"<input type='hidden' id='latlongLocation' name='latlongLocation'>"
								+ 					"</form>"
								+ 				"</div>"
								+ 			"</div>"
								+ 		"</div>"
								+ 		"<div id='popupDiv'>"
								+ 			"<div id='popupContent'>"
								+ 				"<div id='popupHeader'>"
								+ 					"<span id='close' onclick='closePopup()'>&times</span>"
								+ 					"<h2 id='popupTitle'>Forgot your Password?</h2>"
								+ 				"</div>"
								+				"<form id='popupForm' method='POST'>"
								+ 					"<div id='popupBody'>"
								+ 						"<div id='pInputDiv'>"
								+ 							"<input type='email' id='changeEmail' name='changeEmail' placeholder='Email' required>"
								//+ 							"<div id='pInputError'>Please enter your email.</div>"
								+ 						"</div>"
								+ 						"<p class='popupWords'>Enter your email address.</p>"
								+ 						"<p class='popupWords'>Click the button for a new password to be sent to your email.</p>"
								+ 					"</div>"
								+ 					"<div id='popupFooter'>"
								+ 						"<div id='pBtnDiv'>"
								+ 							"<button type='submit' name='postBtn' value='popupBtn' id='popupBtn'>Change Password</button>"
								+ 						"</div>"
								+ 					"</div>"
								+				"</form>"
								+ 			"</div>"
								+ 		"</div>"
								+ 	"</body>"
								+ "</html>");
					}
						
					login.close();
					dba.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
