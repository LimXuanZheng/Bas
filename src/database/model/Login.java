package database.model;

public class Login {
	String username;
	String password;
	int userID;
	
	public Login(String username, String password, User user) {
		this.username = username;
		this.password = password;
		this.userID = user.getUserID();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
