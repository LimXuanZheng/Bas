package fileScanning;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ScanningThread extends Thread{
	File fileToBeScanned = new File("C:/Users/Wei Xuan/Desktop/autoplaylist.txt");
	static boolean n;
	
	public ScanningThread(){
		System.out.println("Thread have been created");
	}
	
	public ScanningThread(File fileToBeScanned){
		super();
		this.fileToBeScanned = fileToBeScanned;
		
    }
    
    public void run(){
    	
    	try{
    		//File temp = new File("C:/Users/Wei Xuan/Desktop/autoplaylist.txt");
    		//String filePath = "C:/Users/Wei Xuan/Desktop/School Work";
    		String cmd = "\"C:/Program Files/Windows Defender/MpCmdRun.exe\" SignatureUpdate -MMPC \"" + fileToBeScanned.getAbsolutePath() + "\"";
    		String cmd2 = "\"C:/Program Files/Windows Defender/MpCmdRun.exe\" -Scan -Scantype 3 -File \"" + fileToBeScanned.getAbsolutePath() + "\"";
    		
    		ProcessBuilder pb = new ProcessBuilder(cmd);

    	    Process p = pb.start();
    	    InputStream is = p.getInputStream();
    	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    	    String line = null;
    	    String outputText = "";
    	    while ((line = br.readLine()) != null) {
    	    	outputText += line + "\n";
    	    }
    	    System.out.println(outputText);
    	    
    	    p.waitFor();
    		ProcessBuilder pb2 = new ProcessBuilder(cmd2);

    		Process p2 = pb2.start();
    		InputStream is2 = p2.getInputStream();
    		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
    		String line2 = null;
		    ArrayList<String> outputText2 = new ArrayList<String>();
		    while ((line2 = br2.readLine()) != null) {
		      outputText2.add(line2);
		    }
		    for(String s:outputText2)
		    	System.out.println(s);
    	    
		    if(outputText2.size() > 3){
		    	cancelupload(true);
		    	System.out.println("Virus Detected");
		    }
		    else{
		    	cancelupload(false);
		    }
    	    System.out.println("File Location: " + fileToBeScanned.getAbsolutePath());
		    Thread.sleep(1000);
      }
      catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
      System.out.println("End of Scanning Thread" );
    }
    public void cancelupload(boolean n){
    	this.n = n;
    	
    }
    public boolean cancelupload(){
    	return this.n;
    }
}
