package database.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Announcement {
	private int idAnnouncement;
	private int userFrom;
	private String message;
	private Date expireDate;
	private ArrayList<String> groupName;
	private UserGroup userGroup;
	
	public Announcement(int idAnnouncement, int userFrom, String message, Date expireDate, UserGroup userGroup, String recipient) {
		super();
		this.idAnnouncement = idAnnouncement;
		this.userFrom = userFrom;
		this.message = message;
		this.expireDate = expireDate;
		this.groupName = convertUserGroup(userGroup, recipient);
	}

	public int getIdAnnouncement() {
		return idAnnouncement;
	}
	public int getUserFrom() {
		return userFrom;
	}
	public String getMessage() {
		return message;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public ArrayList<String> getGroupName() {
		return groupName;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setIdAnnouncement(int idAnnouncement) {
		this.idAnnouncement = idAnnouncement;
	}
	public void setUserFrom(int userFrom) {
		this.userFrom = userFrom;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public void setGroupName(ArrayList<String> groupName) {
		this.groupName = groupName;
	}
	public void setGroupName(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	
	public ArrayList<String> convertUserGroup(UserGroup userGroup, String Recipient){
		ArrayList<String> stringArray = new ArrayList<String>();
		Scanner sc;
		if(userGroup.equals(null)){
			sc = new Scanner(Recipient);
		}else{
			sc = new Scanner(userGroup.getMembers());
		}
		sc.useDelimiter(",");
		
		while(sc.hasNext()){
			stringArray.add(sc.next());
		}
		sc.close();
		
		return stringArray;
	}
}
