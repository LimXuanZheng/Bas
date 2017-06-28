package fileUpload;

import java.io.File;
import java.io.FileInputStream;
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
		//PrintWriter out = response.getWriter();
		String path = request.getParameter("datafile");
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
        */
        InputStream in = new FileInputStream(encryptedFile);
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
        
        
        
        
       
        
        //String test = encryptedFile.toString();
        //File file = new File("C:/Users/Lim Xuan Zheng/Documents/GitHub/Bas/src/encryptedd");
		//File f1 = new File(file, test);
		//f1.createNewFile();
       
		

	}
	
	
    
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String filePath = request.getParameter("datafile");
		out.println("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
