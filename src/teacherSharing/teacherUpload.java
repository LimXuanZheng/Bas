package teacherSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileUpload.CryptoException;
import fileUpload.encryption;

/**
 * Servlet implementation class teacherUpload
 */
@WebServlet("/teacherupload")
public class teacherUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String filepathess = request.getParameter("datafile");
		File f = new File(filepathess);
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
        
        /*InputStream in = new FileInputStream(encryptedFile);
        String name = f.getName();
        System.out.println(name);
        System.out.println(f.length());
        */
        
        
        
        
        /*File decryptedFile = new File("document.decrypted");
        try {
        	decryption.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            //System.out.println("fail");
        }
        FileInputStream hh = new FileInputStream(encryptedFile);
        OutputStream h = response.getOutputStream();
       
        byte[] test = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = hh.read(test)) != -1) {
                           h.write(test, 0, bytesRead);
                       }
                       */
        
		//System.out.println(filepathess);
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
		    	+		"<script>function goBack() {window.location('teachersharing');}"
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
    			+				"<p style='background-color:#7FFF00; font-size:70px; text-align:center;'> File Successfully Uploaded</p>"
    			+				"<button class='btn btn-success' onclick='goBack()'>Continue</button>"
    			+			"</div>"
				
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