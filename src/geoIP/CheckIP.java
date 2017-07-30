package geoIP;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import database.model.IpAddress;
import database.model.UserLocation;

public class CheckIP {
	private String ipAddress;

	public CheckIP(HttpServletRequest request){
		ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
			ipAddress = request.getRemoteAddr();  
		}
		System.out.println("IP Address: " + ipAddress);
	}

	private boolean check() throws IOException, ClassNotFoundException, SQLException{
		for(IpAddress s:IpAddress.getBlackList()){
			if(ipAddress.equals(s.getIpAddress())){
				return true;
			}
		}
		return false;
	}

	public void redirect(HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException{
		if(check()){
			response.sendRedirect("Error403");
		}
	}

	public UserLocation getLocation() throws IOException, GeoIp2Exception{
		GeoIPDao dao = new GeoIPDao();
		UserLocation location;

		if(ipAddress.substring(0, 3).equals("192")){
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			System.out.println(ip);
			location = dao.getLocation(ip);
		}else{
			location = dao.getLocation(ipAddress);
		}

		return location;
	}

	public void showLocationOnGoogle(HttpServletResponse response) throws IOException, GeoIp2Exception, ParseException{
		UserLocation location = getLocation();
		String userHome = System.getProperty("user.home");
		File file2 = new File(userHome, "/Documents/GitHub/Bas/WebContent/script/mapScript.js");
		ArrayList<String> lines = new ArrayList<String>(Files.readAllLines(file2.toPath()));
		lines.set(3, "\t\tcenter: {lat: " + location.getLatitude() + ", lng: " + location.getLongitude() + "},");
		lines.set(27, "\t\t\t\tcenter: {lat: " + location.getLatitude() + ", lng: " + location.getLongitude() + "},");
		Files.write(file2.toPath(), lines);

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ 	"<head>"
				+ 	"</head>"
				+ 	"<body onload=\"window.open('/Bas/html/Map.html', '_blank')\">"
				+ 	"</body>"
				+ 	"</html>");
	}

	public String getJSONByGoogle(String fullAddress) throws IOException {
		URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json" + "?address=" + URLEncoder.encode(fullAddress, "UTF-8")+ "&sensor=false");
		URLConnection conn = url.openConnection();
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
		IOUtils.copy(conn.getInputStream(), output);
		output.close();

		return output.toString();
	}

	public String getLatLongByGoogle(String search) throws ParseException, IOException{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(getJSONByGoogle(search));
		System.out.println(json);
		return search;
	}
}
