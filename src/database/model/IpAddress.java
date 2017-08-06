package database.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IpAddress {
	String ipAddress;

	public IpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	static public ArrayList<IpAddress> getBlackList() throws IOException, ClassNotFoundException, SQLException{
		WhiteBlackListDAO dao = new WhiteBlackListDAO();
		ArrayList<IpAddress> ipArray = new ArrayList<IpAddress>();
		for(String s:dao.getBlackListArray()){
			IpAddress ip = new IpAddress(s);
			ipArray.add(ip);
		}
		return ipArray;
	}
}
