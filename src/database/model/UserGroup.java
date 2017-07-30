package database.model;

import java.util.ArrayList;
import java.util.Scanner;

public class UserGroup {
	private int idUserGroup;
	private String groupName;
	private String members;
	private String owner;
	
	public UserGroup(int idUserGroup, String groupName, String members, String owner) {
		super();
		this.idUserGroup = idUserGroup;
		this.groupName = groupName;
		this.members = members;
		this.owner = owner;
	}

	public int getIdUserGroup() {
		return idUserGroup;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getMembers() {
		return members;
	}
	public String getOwner() {
		return owner;
	}
	public void setIdUserGroup(int idUserGroup) {
		this.idUserGroup = idUserGroup;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public ArrayList<String> convertToArrayList(boolean m){ //If getting member
		ArrayList<String> stringArray = new ArrayList<String>();
		Scanner sc;
		if(m){
			sc = new Scanner(members);
		}else{
			sc = new Scanner(owner);
		}
		sc.useDelimiter(",");
		while(sc.hasNext()){
			stringArray.add(sc.next());
		}
		sc.close();
		
		return stringArray;
	}
}
