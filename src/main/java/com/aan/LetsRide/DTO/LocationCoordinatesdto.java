package com.aan.LetsRide.DTO;

public class LocationCoordinatesdto {
	private String displayName;
    private double lat;
    private double lon;
	private double double1;
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public LocationCoordinatesdto() {
		super();
	}
	public LocationCoordinatesdto(String displayName, double lat, double lon) {
		super();
		this.displayName = displayName;
		this.lat = lat;
		this.lon = lon;
	}
	public void setLat(double double1) {
		this.double1=double1;
		
	}
	
	
	}
    


