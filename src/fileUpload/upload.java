package fileUpload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import fileUpload.CryptoException;
import teacherSharing.decryption;


/**
 * Servlet implementation class upload
 */
@WebServlet("/uploads")
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getParameter("datafile");
		String written = request.getParameter("textbox");
		if(path.equals("")){
			String file1="test.txt";
		      try{
		      	//create the PrintWriter
				FileWriter fw = new FileWriter(file1);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outFile = new PrintWriter(bw);

				//write value out to the file
				outFile.println(written);
				
				//close the file
				outFile.close();
				
		      }
		      catch (IOException exception){
		         System.out.println (exception);
		      }
			System.out.println("not a file");
			File ff = new File(file1);
			String mypath = ff.getAbsolutePath();
			File ffs = new File(mypath);
			String key = "Mary has one cat";
	        File encryptedFile = new File("document.encrypted");
	        try {
	        	encryption.encrypt(key, ffs, encryptedFile);
	        } catch (CryptoException ex) {
	            System.out.println(ex.getMessage());
	            ex.printStackTrace();
	            System.out.println("fail");
	        }
			
		}
		else{
		File f = new File(path);
		//out.println(path);
		String key = "Mary has one cat";
        File encryptedFile = new File("document.encrypted");
        try {
        	encryption.encrypt(key, f, encryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            System.out.println("fail");
        }
		}
        
        /*File decryptedFile = new File("document.decrypted");
        try {
        	decryption.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            //System.out.println("fail");
        }
        FileInputStream hh = new FileInputStream(decryptedFile);
        OutputStream h = response.getOutputStream();
       
        byte[] test = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = hh.read(test)) != -1) {
                           h.write(test, 0, bytesRead);
                       }
        
        /*
        
        /*InputStream in = new FileInputStream(encryptedFile);
        String name = f.getName();
        System.out.println(name);
        System.out.println(f.length());
        
        try {
                    DatabaseAccess dba = new DatabaseAccess(1);
                    String sqlline = "INSERT INTO File(FileName, Size, Data) VALUES (?, ?, ?);";
                    dba.updateDatabaseDataFileUpload(sqlline, name, f.length(), in);
                    dba.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                */
        
        
        
        
        
        
       
        
        //String test = encryptedFile.toString();
        //File file = new File("C:/Users/Lim Xuan Zheng/Documents/GitHub/Bas/src/encryptedd");
		//File f1 = new File(file, test);
		//f1.createNewFile();
       
		out.println("<!DOCTYPE html>"
    			+ "<html>"
    			+ 	"<head>"
    			+ 		"<meta charset='UTF-8'>"
    			+ 		"<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
    			//+ 		"<link rel='stylesheet' href='css/teacherSharing.css'>"
    			+ 		"<!-- Font Mono-Sans -->"
    			+ 		"<link href='http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic,700italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
    			+ 		"<!-- Latest compiled and minified CSS -->"
    			+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>"
    			+ 		"<!-- Optional theme -->"
    			+ 		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>"
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
    			+				"<p style='background-color:#7FFF00; font-size:70px; text-align:center;'> Assignment Successfully submitted</p>"
    			+				"<button class='btn btn-success' onclick='goHome()'>Continue</button>"
    			+			"</div>"
    			
				
				
				
				
				
				
				
				);
		
		out.println(written);

	}
	
	
    
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String filePath = request.getParameter("datafile");
		out.println("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		out.println("hello");
	}

}
