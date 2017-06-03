package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {
    final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    final String DB_URL="jdbc:mysql://localhost:3306/nspj_database";
    
    public DatabaseAccess(){
    	super();
    }
    
    public ResultSet getDatabaseData(String sql){
	    final String USER = "root";
	    final String PASS = "SassyPenguin@123";

	    try{
	       Class.forName(JDBC_DRIVER);
	       Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	       Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       return rs;
	    }catch(SQLException se){
	       se.printStackTrace();
	    }catch(Exception e){
	       e.printStackTrace();
	    }
	    return null;
    }
    
    public void insertDatabaseData(String sql){
    	final String USER = "root";
	    final String PASS = "SassyPenguin@123";

	    try{
	       Class.forName(JDBC_DRIVER);
	       Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	       Statement stmt = conn.createStatement();
	       
	       int count = stmt.executeUpdate(sql);
	       if(count ==0){
	    	   System.out.println("Update failed... Try again later");
	       } else{
	    	   System.out.println("Update successful... Try again later?");
	       }
	       stmt.close();
	       conn.close();
	    }catch(SQLException se){
	       se.printStackTrace();
	    }catch(Exception e){
	       e.printStackTrace();
	    }
    }
    
    /*
    public static void main(String[] args) throws SQLException{
    	DatabaseAccess dba = new DatabaseAccess();
    	//dba.insertDatabaseData("INSERT INTO User VALUES ('2','Lim Xuan Zheng','M','2017-11-11','99999999','dwiadwon@ndownadon.com','4E3','MathGod')");
    	ResultSet rs = dba.getDatabaseData("SELECT * FROM user");
    	
    	while(rs.next()){
            //Retrieve by column name
    		int userID = rs.getInt("UserID");
            String name  = rs.getString("Name");
            String gender = rs.getString("Gender");
            String DOB = rs.getString("DOB");
            String email = rs.getString("Email");
            String schoolClass = rs.getString("Class");
            String subject = rs.getString("Subject");
            

            //Display values
            System.out.println("User ID: " + userID);
            System.out.println("Name: " + name);
            System.out.println("Gender: " + gender);
            System.out.println("Date of Birth: " + DOB);
            System.out.println("Email: " + email);
            System.out.println("Class: " + schoolClass);
            System.out.println("Subject: " + subject + "\n");
         }
    	
    	rs.close();
    } 
    */
}
