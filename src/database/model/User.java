package database.model;

public class User {
	int userID;
	String nRIC;
	String name;
	String gender;
	String dOB;
	String contactNo;
	String email;
	String schoolClass;
	String address;
	byte[] keys;
	
	public User(int userID, String nRIC, String name, String gender, String dOB, String contactNo, String email, String schoolClass, String address, byte[] keys) {
		this.nRIC = nRIC;
		this.userID = userID;
		this.name = name;
		this.gender = gender;
		this.dOB = dOB;
		this.contactNo = contactNo;
		this.email = email;
		this.schoolClass = schoolClass;
		this.address = address;
		this.keys = keys;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getnRIC() {
		return nRIC;
	}
	public void setnRIC(String nRIC) {
		this.nRIC = nRIC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getdOB() {
		return dOB;
	}
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public byte[] getKeys() {
		return keys;
	}
	public void setKeys(byte[] keys) {
		this.keys = keys;
	}
	public void printInfo(){
		System.out.println("UserID: " + userID + ", Name: " + name + ", Gender: " + gender + ", Date Of Birth: " + dOB + ", Contact Number: " + contactNo + ", Email: " + email + ", Class: " + schoolClass + ", Address: " + address);
	}
	
}
