package directory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;

/**
 * Servlet implementation class Directory
 */
@WebServlet("/Directory")
public class Directory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//private DataTable dataTable;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("/html/directory.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get and validate name.
        String name = request.getParameter("search");
        // Get schoolClass
    	String schoolClass = request.getParameter("schoolClass");
    	//Get Subject
    	String subject = request.getParameter("subject");
    	
    	/*
    	ResultSet rs = null;
        if (validateName(name)) {
        	DatabaseAccess dba = new DatabaseAccess();
        	rs = dba.getDatabaseData("SELECT * FROM User WHERE Name='';");
        }
        
        PrintWriter out = response.getWriter();
        try {
	        while(rs.next()){
	        	//Retrieve by column name
				String username  = rs.getString("Name");
	            String DOB = rs.getString("DOB");
	            int userID = rs.getInt("UserID");
	
	            //Display values
	            out.println("Username: " + username + "<br>");
	            out.println(", Password: " + DOB + "<br>");
	            out.println(", UserID: " + userID + "<br>");
	        }
        }catch (SQLException e) {
				e.printStackTrace();
		}
		
		*/
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	out.println("<html><head></head><body>");
    	//Display values
        out.println("Name: " + name + "<br>");
        out.println("Class: " + schoolClass + "<br>");
        out.println("Subject: " + subject + "<br>");
        out.println("</body></html>");
        
        request.getRequestDispatcher("/html/directory.html").forward(request, response);
	}

	private boolean validateName(String name){
		if(name != null)
			if(name.matches("[a-zA-Z]+"))
				return true;
			else
				return false;
		else
			return false;
	}
}
