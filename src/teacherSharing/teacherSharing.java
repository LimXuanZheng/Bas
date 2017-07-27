package teacherSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import database.model.User;
import database.model.UserAll;
import fileUpload.CryptoException;
import fileUpload.encryption;

/**
 * Servlet implementation class teacherSharing
 */
@WebServlet("/teachersharing")
public class teacherSharing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String filepathes;
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
		String key1 = "";
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<database.model.File> fileArray = dbms.getDatabaseFile();
			database.model.File file1 = fileArray.get(0);
			key1 = file1.getUser().getKeys();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		
		/*ArrayList<String> names = new ArrayList<String>();
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<User> alluser = dbms.getDatabaseUser();
			for(User a: alluser){
				String j = a.getName();
				names.add(j);
			}
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(String s:names){
			System.out.println(s);
		}
		
		*/
		//String key = "Mary has one cat";
		
		String filename1 = "Nothing to show";
		int filesize1;
		Date date1;
		byte [] filedata;
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			ArrayList<database.model.File> fileArray = dbms.getDatabaseFile();
			database.model.File file1 = fileArray.get(0);
			String name1 = file1.getUser().getName();
			System.out.println(name1);
			for(database.model.File s:fileArray){
				filename1 = s.getFileName();
				filesize1 = s.getFileSize();
				date1 = s.getDate();
				filedata = s.getFileData();
				System.out.println(filename1);
				System.out.println(filesize1);
				System.out.println(date1);
				File tempFile = File.createTempFile(filename1, ".tmp", null);
				FileOutputStream fos = new FileOutputStream(tempFile);
				fos.write(filedata);
				
				File decryptedFile = new File("document.decrypted");
		        try {
		        	decryption.decrypt(key1, tempFile, decryptedFile);
		        } catch (CryptoException ex) {
		            System.out.println(ex.getMessage());
		            ex.printStackTrace();
		            System.out.println("fail");
		        }
		        
		        
		        
		        
		        //FileInputStream hh = new FileInputStream(decryptedFile);
		        //OutputStream h = response.getOutputStream();
		       
		       // byte[] test = new byte[4096];
		        //int bytesRead = -1;
		        //while ((bytesRead = hh.read(test)) != -1) {
		         //                  h.write(test, 0, bytesRead);
		           //            }
		        
		        
		        
		        
		        
		        
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		
		
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
    			+ 	"<body id='body' onload='loadalltheshit()'>"
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
    			+			"<p id='hidehere'>The class path is here</p>"
    			+			"<div id='overlay'>"
    			+				"<div id='content1'>"
    			+					"<form id='anything' action='teacherupload' onsubmit='asubmit()'>"
    			+						"<input type='radio' id='checkifmy' name='chooseFile' onclick='javascript:showHidden();' checked>My Drive</input>"
    			+						"<input type='radio' id='checkifshare' name='chooseFile' onclick='javascript:showHidden();'>Shared Drive</input>"
    			+						"<input type='radio' id='checkifnew' name='chooseFile' onclick='javascript:showHidden();'>New Drive</input>"
    			+						"<br><label class='custom-file'>"
				+							"<input type='file' id='datafilepath' class='custom-file-input' name='datafile' style='font-size:20px;'>"
				+							"<span class='custom-file-control'></span>"
				+						"</label>"
    			+						"<br><p id='nameOfNewButton'>Name of Folder: <input type='text' name='FName' id='getBtnName'></input></p>"
    			+						"<br><div class='dropdown' id='dropdowns'>"						
				+			    			"<button  type='button' onclick='showsomething()' class='dropbtn'>Dropdown</button>"
				+			    			 "<div id='myDropdown' class='dropdown-content'>"
				+			    			  	"<input type='text' placeholder='Search..' id='myInput' onkeyup='filterFunction()'>"
				+			    			    "<a href='#about'>Darren</a>"
				+			    			    "<a href='#base'>Nicholson</a>"
				+			    			    "<a href='#blog'>Mr Loo</a>"
				+			    			    "<a href='#contact'>Mr Thumb</a>"
				+			    			    "<a href='#custom'>Strong women</a>"
				+			    			    "<a href='#support'>Matthew</a>"
				+			    			    "<a href='#tools'>Doggy</a>"
				+			    			  "</div>"
				+			    			"</div>"
				+						"<input type='submit' id='submit' value='Submit'></input>"
    			+					"</form>"
    			//+					"<button id='confirm' onclick='confirm()' >Confirm</button>"
    			+					"<button type='button' id='cancel' onclick='cancel()'>Cancel</button>"
    			//+					"</form>"
    			+				"</div>"
    			+			"</div>"
    			+			"<div>"
    			+				"<div class='contents' id='storeButton'>"
    			+					"<button class='button' id='newbutton' onclick='createNew()'>New</button>"
    			+					"<button class='button' id='mybutton'>My Drive</button>"//onclick='displayallmy()'
    			+					"<button class='button' id='lastOne'>Shared Drive</button>"
    			+				"</div>"
    			+				"<div class='contents' id='storeTable'>"
    			+					"<table id='displaying'>"
    			+						"<tr>"
    			+							"<th>Name</th>"
    			+							"<th>Owner</th>"
    			+							"<th>Last Modified</th>"
    			+							"<th>File Size</th>"
    			+						"</tr>"
    			+						"<tr id='row1'>"
    			+							"<td id='row1.1'>nothing to display</td>"
    			+							"<td id='row1.2'>nothing to display</td>"
    			+							"<td id='row1.3'>nothing to display</td>"
    			+							"<td id='row1.4'>nothing to display</td>"
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		filepathes = request.getParameter("datafile");
		System.out.println(filepathes);
		System.out.println("hello");
		/*HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute("thepath", filepath);
			
			
		}
		else {
			System.out.println("Didn't work");
		}
		*/
		//doGet(request, response);
	}

}
