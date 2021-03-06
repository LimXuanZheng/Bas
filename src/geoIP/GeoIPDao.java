package geoIP;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import database.model.UserLocation;

public class GeoIPDao {
	private DatabaseReader cityDbReader;

	public GeoIPDao() throws IOException {
		String userHome = System.getProperty("user.home");
		File cityDatabase = new File(userHome, "/Documents/GitHub/Bas/src/geoIP/GeoLite2-City.mmdb");
		cityDbReader = new DatabaseReader.Builder(cityDatabase).build();
	}

	public UserLocation getLocation(String ip) throws IOException, GeoIp2Exception {
		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = cityDbReader.city(ipAddress);

		String cityName = response.getCity().getName();
		String latitude = response.getLocation().getLatitude().toString();
		String longitude = response.getLocation().getLongitude().toString();
		return new UserLocation(ip, cityName, latitude, longitude);
	}
}
