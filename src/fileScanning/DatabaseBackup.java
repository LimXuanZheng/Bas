package fileScanning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DatabaseBackup{
	
	public void restoreFromBackup() throws IOException{
		String userHome = System.getProperty("user.home");
		String cmd = "cmd.exe /c \"cd " + userHome.replace('\\', '/') + "/Documents/GitHub/Bas/\"";
		String cmd2 = "cmd.exe /c \"mysql -u root -p nspj_database < nspj_databaseBackup.sql\"";
		String cmd3 = "cmd.exe /c \"SassyPenguin@123\"";
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
		Process pr2 = rt.exec(cmd2);
		Process pr3 = rt.exec(cmd3);
		rt.exit(0);
	}
	
	public void backupDatabaseFile() throws IOException{
		System.out.println("Started...");
		String userHome = System.getProperty("user.home");
		String cmd = "cmd.exe /c \"cd " + userHome.replace('\\', '/') + "/Documents/GitHub/Bas/UserData\"";
		String cmd2 = "cmd.exe /c \"mysqldump --opt -u root -p nspj_database file> nspj_UserData.sql\"";
		String cmd3 = "cmd.exe /c \"SassyPenguin@123\"";
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
		Process pr2 = rt.exec(cmd2);
		Process pr3 = rt.exec(cmd3);
		rt.exit(0);
		System.out.println("Ended...");
	}
	
	public void backupDatabase() throws IOException{
		System.out.println("Started...");
		String userHome = System.getProperty("user.home");
		String cmd = "cmd.exe /c \"cd " + userHome.replace('\\', '/') + "/Documents/GitHub/Bas/CloudFiles\"";
		String cmd2 = "cmd.exe /c \"mysqldump --opt -u root -p nspj_database announcement blacklist lesson login student teacher user usergroup> nspj_CloudFiles.sql\"";
		String cmd3 = "cmd.exe /c \"SassyPenguin@123\"";
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
		Process pr2 = rt.exec(cmd2);
		Process pr3 = rt.exec(cmd3);
		rt.exit(0);
		System.out.println("Ended...");
	}
	
	public static void main(String[] args) throws IOException{
		DatabaseBackup dbb = new DatabaseBackup();
		//dbb.backupDatabaseFile();
		dbb.backupDatabase();
	}
	
}
