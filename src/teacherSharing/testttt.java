package teacherSharing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseAccess;
import database.model.UserAll;

/**
 * Servlet implementation class testttt
 */
@WebServlet("/testttt")
public class testttt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testttt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String gf = "hi";
		ArrayList<String> haha = new ArrayList<String>();
		ArrayList<UserAll> alluser1 = new ArrayList<UserAll>();
		ArrayList<database.model.File> fileArray = new ArrayList<database.model.File>();
		try {
			DatabaseAccess dbms = new DatabaseAccess(1);
			fileArray = dbms.getDatabaseFile();
			alluser1 = dbms.getDatabaseUserAll();
		}
	 catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		
		for(database.model.File f:fileArray){
			System.out.println(f.getFileName());
			System.out.println(f.getShareType());
			System.out.println(f.getRecipient());
			System.out.println(f.getUser().getName());
		}
		
		out.println("hello");
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
