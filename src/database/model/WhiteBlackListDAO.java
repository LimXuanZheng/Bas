package database.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseAccess;

public class WhiteBlackListDAO {
	//final private String filePath = "src/basicFirewall/WhiteBlackList.txt"; //Add the database
	//final private String fileCommitPath = "C:/Users/Wei Xuan/workspace/Servers/Tomcat v9.0 Server at localhost-config/server.xml";
	//final private String fileCommitPath2 = "C:/Apache24/conf/whiteblacklist/whitelist.conf";
	private DatabaseAccess dao;
	
	public WhiteBlackListDAO() throws IOException, ClassNotFoundException, SQLException{
		dao = new DatabaseAccess(1);
		
	}
	
	public ArrayList<String> getBlackListArray() throws FileNotFoundException, SQLException{
		ArrayList<String> blackListArray = new ArrayList<String>();
		ResultSet rs = dao.getDatabaseData("SELECT ipAddress FROM blacklist;");
		while(rs.next()){
			String s = rs.getString("ipAddress");
			blackListArray.add(s);
		}
		return blackListArray;
	}
	
	public void commitRule() throws ClassNotFoundException, SQLException, FileNotFoundException{
		DatabaseAccess dao = new DatabaseAccess(0);
		ArrayList<String> as = new ArrayList<String>();
		for(IpAddress a:dao.getDatabaseBlacklist()){
			as.add(a.getIpAddress());
		}
		ArrayList<String> blackList = getBlackListArray();
		if(as.size() == blackList.size()){
			for(int i = 0; i < blackList.size(); i++){
				String sql = "UPDATE Blacklist SET IpAddress = " + blackList.get(i) + " WHERE ID = " + i +";";
				dao.updateDatabaseData(sql);
			}
		}else{
			int number = as.size() - blackList.size();
			if(number < 1){
				for(int i = 0; i < blackList.size() + number; i++){
					String sql = "UPDATE Blacklist SET IpAddress = " + blackList.get(i) + " WHERE ID = " + i +";";
					dao.updateDatabaseData(sql);
				}
				for(int i = blackList.size() + number; i < blackList.size(); i++){
					String sql = "INSERT INTO Blacklist(IpAddress) VALUES (\"" + blackList.get(i) + "\");";
					dao.updateDatabaseData(sql);
				}
			}
			else{
				for(int i = 0; i < blackList.size() - number; i++){
					String sql = "UPDATE Blacklist SET IpAddress = " + blackList.get(i) + " WHERE ID = " + i +";";
					dao.updateDatabaseData(sql);
				}
				for(int i = blackList.size() - number; i < blackList.size(); i++){
					String sql = "DELETE FROM BlackList WHERE ID = " + (i - 1) + ";";
					dao.updateDatabaseData(sql);
				}
			}
		}
		dao.close();
		
	}
}
