package directory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Directory
 */
@WebServlet("/Directory")
public class Directory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//private DataTable dataTable;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/directory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get and validate name.
        String name = request.getParameter("search");
        
        // Get schoolClass
    	String schoolClass = request.getParameter("schoolClass");
    	
    	String line = "<tr><td>" + name + "</td>" +
    			"<td>" + schoolClass + "</td" +
    			"<td>1234 5678</td><td>testing@testing.com</td></tr>";
    	
        if (validateName(name)) {
        	request.setAttribute("returnResult", line);
        }
        
        request.getRequestDispatcher("/jsp/directory.jsp").forward(request, response);
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
