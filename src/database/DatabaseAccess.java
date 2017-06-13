package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.model.Lesson;
import database.model.Login;
import database.model.Student;
import database.model.Teacher;
import database.model.User;
import database.model.UserAll;

public class DatabaseAccess {
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    final String DB_URL="jdbc:mysql://localhost:3306/nspj_database";
    final String USER = "root";
    final String PASS = "SassyPenguin@123";
    Connection conn;
    Statement stmt;
    
	public DatabaseAccess() throws ClassNotFoundException, SQLException{
		 Class.forName(JDBC_DRIVER);
	     conn = DriverManager.getConnection(DB_URL, USER, PASS);
	     stmt = conn.createStatement();
	}

	private ResultSet getDatabaseData(String sql) throws SQLException{
		return stmt.executeQuery(sql);
	}
	
	public ArrayList<UserAll> getDatabaseUserAll() throws SQLException{
		ResultSet rs = getDatabaseData("SELECT User.UserID, Login.Username, Login.Password, User.Name, User.Gender, User.DOB, User.ContactNo, User.Email, User.Class, User.Address, Student.NRIC, Student.CCA, Teacher.TeacherID, Teacher.Department FROM User LEFT OUTER JOIN Login ON (User.UserID = Login.UserID) LEFT OUTER JOIN Student ON (User.UserID = Student.UserID) LEFT OUTER JOIN Teacher ON (User.UserID = Teacher.UserID) ORDER BY UserID;");
		ArrayList<UserAll> userAllArray = new ArrayList<UserAll>();
		
		while(rs.next()){
			int userID = rs.getInt("userID");
			String name = rs.getString("Name");
			String gender = rs.getString("Gender");
			String dOB = rs.getString("DOB");
			String contactNo = rs.getString("ContactNo");
			String email = rs.getString("Email");
			String schoolClass = rs.getString("Class");
			String address = rs.getString("Address");
			User user = new User(userID, name, gender, dOB, contactNo, email, schoolClass, address);
			
			String username = rs.getString("Username");
			String password = rs.getString("Password");
			Login login = new Login(username, password, user);
			
			String nRIC = rs.getString("NRIC");
			String cCA = rs.getString("CCA");
			Student student = new Student(nRIC, cCA, user);
			
			int teacherID = rs.getInt("TeacherID");
			String department = rs.getString("Department");
			Teacher teacher = new Teacher(teacherID, department, user);
			
			userAllArray.add(new UserAll(user, login, student, teacher));
		}
		
		rs.close();
		return userAllArray;
	}
	
	public ArrayList<User> getDatabaseUser() throws SQLException{
		ResultSet rs = getDatabaseData("SELECT * FROM User;");
		ArrayList<User> userArray = new ArrayList<User>();
		
		while(rs.next()){
			int userID = rs.getInt("userID");
			String name = rs.getString("Name");
			String gender = rs.getString("Gender");
			String dOB = rs.getString("DOB");
			String contactNo = rs.getString("ContactNo");
			String email = rs.getString("Email");
			String schoolClass = rs.getString("Class");
			String address = rs.getString("Address");
			userArray.add(new User(userID,name,gender,dOB,contactNo,email,schoolClass,address));
		}
		
		rs.close();
		return userArray;
	}
	
	public ArrayList<Login> getDatabaseLogin() throws SQLException{
		ResultSet rs = getDatabaseData("SELECT * FROM Login INNER JOIN User ON Login.userID = User.userID;");
		ArrayList<Login> loginArray = new ArrayList<Login>();
		
		while(rs.next()){
			int userID = rs.getInt("userID");
			String name = rs.getString("Name");
			String gender = rs.getString("Gender");
			String dOB = rs.getString("DOB");
			String contactNo = rs.getString("ContactNo");
			String email = rs.getString("Email");
			String schoolClass = rs.getString("Class");
			String address = rs.getString("Address");
			
			User user = new User(userID,name,gender,dOB,contactNo,email,schoolClass,address);
			String username = rs.getString("Username");
			String password = rs.getString("Password");
			Login login = new Login(username, password, user);
			loginArray.add(login);
		}
		
		rs.close();
		return loginArray;
	}
	
	public ArrayList<Student> getDatabaseStudent() throws SQLException{
		ResultSet rs = getDatabaseData("SELECT * FROM Student INNER JOIN User ON Login.userID = User.userID;");
		ArrayList<Student> studentArray = new ArrayList<Student>();
		
		while(rs.next()){
			int userID = rs.getInt("userID");
			String name = rs.getString("Name");
			String gender = rs.getString("Gender");
			String dOB = rs.getString("DOB");
			String contactNo = rs.getString("ContactNo");
			String email = rs.getString("Email");
			String schoolClass = rs.getString("Class");
			String address = rs.getString("Address");
			User user = new User(userID,name,gender,dOB,contactNo,email,schoolClass,address);
			
			String nRIC = rs.getString("NRIC");
			String cCA = rs.getString("CCA");
			Student student = new Student(nRIC, cCA, user);
			
			studentArray.add(student);
		}
		
		rs.close();
		return studentArray;
	}
	
	public ArrayList<Teacher> getDatabaseTeacher() throws SQLException{
		ResultSet rs = getDatabaseData("SELECT * FROM Teacher INNER JOIN User ON Login.userID = User.userID;");
		ArrayList<Teacher> teacherArray = new ArrayList<Teacher>();
		
		while(rs.next()){
			int userID = rs.getInt("userID");
			String name = rs.getString("Name");
			String gender = rs.getString("Gender");
			String dOB = rs.getString("DOB");
			String contactNo = rs.getString("ContactNo");
			String email = rs.getString("Email");
			String schoolClass = rs.getString("Class");
			String address = rs.getString("Address");
			
			User user = new User(userID,name,gender,dOB,contactNo,email,schoolClass,address);
			int teacherID = rs.getInt("TeacherID");
			String department = rs.getString("Department");
			Teacher teacher = new Teacher(teacherID, department, user);
			teacherArray.add(teacher);
		}
		
		rs.close();
		return teacherArray;
	}
	
	public void updateDatabaseData(String sql) throws SQLException{
		int count = stmt.executeUpdate(sql);
        if(count ==0){
        	System.out.println("!!! Update failed !!!\n");
        }else{
    	    System.out.println("!!! Update successful !!!\n");
        }
	}
	
	public void close() throws SQLException{
		conn.close();
		stmt.close();
	}
	
	/*
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		DatabaseDAO dao = new DatabaseDAO();
		ArrayList<UserAll> userAllArray= dao.getDatabaseUserAll();
		
		for(UserAll u:userAllArray){
			u.printInfo();
		}
		
		dao.close();
	}
	*/
}
