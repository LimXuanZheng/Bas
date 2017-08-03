package database.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class File {
	int fileID;
	int UserID;
	String fileName;
	int fileSize;
	byte[] fileData;
	String recipient;
	Date date;
	int shareType;
	User user;


	public File(int fileID, User user, String fileName, int fileSize, byte[] fileData, String recipient, Date date, int shareType) {
		this.fileID = fileID;
		UserID = user.getUserID();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileData = fileData;
		if(!recipient.isEmpty()){
			this.recipient = recipient;
		}else{
			this.recipient = null;
		}
		this.date = date;
		this.shareType = shareType;
		this.user = user;
	}

	public int getShareType() {
		return shareType;
	}

	public void setShareType(int shareType) {
		this.shareType = shareType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<String>convertRecipient(){
		ArrayList<String> arrayString = new ArrayList<String>();
		Scanner sc = new Scanner(this.recipient);
		sc.useDelimiter(",");
		while(sc.hasNext()){
			arrayString.add(sc.next());
		}
		return arrayString;
	}
}
