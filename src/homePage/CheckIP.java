package homePage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.IpAddress;

public class CheckIP {
	private static boolean check(HttpServletRequest request) throws IOException{
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
		   ipAddress = request.getRemoteAddr();  
		}
		System.out.println("IP Address: " + ipAddress);
		for(IpAddress s:IpAddress.getWhiteList()){
			if(ipAddress == s.getIpAddress()){
				return true;
			}
		}
		return false;
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(check(request)){
			response.sendRedirect("Login");
		}
	}
}
