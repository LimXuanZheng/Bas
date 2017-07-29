package database.model;

public class UserLocation {
	private String ipAddress;
	private String city;
	private String latitude;
	private String longitude;

	public UserLocation(String ipAddress, String city, String latitude, String longitude) {
		super();
		this.ipAddress = ipAddress;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	public String getCity() {
		return city;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String toString(){
		String line = "IpAddress: " + ipAddress + "\nCity: " + city + "\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n";
		return line;
	}
}
