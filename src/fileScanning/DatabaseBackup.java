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
	
	public void backupDatabase() throws IOException{
		System.out.println("Started...");
		String userHome = System.getProperty("user.home");
		String cmd = "cmd.exe /c \"cd " + userHome.replace('\\', '/') + "/Documents/GitHub/Bas/\"";
		String cmd2 = "cmd.exe /c \"mysqldump --opt -u root -p nspj_database > nspj_databaseBackup.sql\"";
		String cmd3 = "cmd.exe /c \"SassyPenguin@123\"";
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
		Process pr2 = rt.exec(cmd2);
		Process pr3 = rt.exec(cmd3);
		rt.exit(0);
	}
	
}
