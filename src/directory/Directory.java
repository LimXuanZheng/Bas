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
       
    public Directory() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		String title = "Testing";
		
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>" +
				"<body><h2>Search= " + request.getParameter("search") + "</h2><br>" +
				"<h2>ComboBox= " + request.getParameter("class") + "</h2>" +
				"</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
