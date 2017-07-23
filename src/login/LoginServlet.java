package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseAccess;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				+ 	"<body>"
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
				+ 							"<button id='btnLogin'>Login</button>"
				+ 						"</div>"
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
				+ 							"<button type='submit' name='popupBtn' value='popupBtn' id='popupBtn' onclick='renewPass()'>Change Password</button>"
				+ 						"</div>"
				+ 					"</div>"
				+				"</form>"
				+ 			"</div>"
				+ 		"</div>"
				+ 	"</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userID");
		String password = request.getParameter("passID");
		String receipientEmail = request.getParameter("changeEmail");
		String popupBtn = request.getParameter("popupBtn");
		LoginModel LM = new LoginModel();
		if (popupBtn == null) {
			System.out.println("Button not clicked");
		}
		else if (popupBtn.equals("popupBtn")) {
			//LM.generateNewPass();
			//LM.sendEmail(receipientEmail);
			System.out.println("Button clicked");
			HashPass HP = new HashPass();
			String getNewHashedPassword = HP.getHashedPassword(LM.getNewPass(), HP.createSalt());
			//Update the newly generated salt and hashed password to database
		}
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
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
					System.out.println("Entered If");
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					response.sendRedirect("Home");
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
							+ 	"<body>"
							+ 		"<div id='theDiv'>"
							+ 			"<div id='aDiv'>"
							+ 				"<div id='bDiv'>"
							+ 					"<div id='basImgDiv'>"
							+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
							+ 					"</div>"
							+ 					"<h3 id='basTitle'>"
							+ 						"Bas Chun Maen"
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
							+ 							"<button id='btnLogin'>Login</button>"
							+ 						"</div>"
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
							+ 							"<button type='submit' name='popupBtn' value='popupBtn' id='popupBtn' onclick='renewPass()'>Change Password</button>"
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
						+ 		"<script src='script/Login.js'></script>"
						+ 		"<title>Login</title>"
						+ 	"</head>"
						+ 	"<body>"
						+ 		"<div id='theDiv'>"
						+ 			"<div id='aDiv'>"
						+				"<div id='bDiv'>"
						+					"<div id='basImgDiv'>"
						+ 						"<img id='basLogo' src='images/BasLogo.png'/>"
						+ 					"</div>"
						+ 					"<h3 id='basTitle'>"
						+ 						"Bas Chun Maen"
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
						+ 							"<button id='btnLogin'>Login</button>"
						+ 						"</div>"
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
						+ 							"<button type='submit' name='popupBtn' value='popupBtn' id='popupBtn' onclick='renewPass()'>Change Password</button>"
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
