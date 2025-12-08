package com.aan.LetsRide.entity;

public class LocationIQResponse {
	
	    private String lat;
	    private String lon;
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getLon() {
			return lon;
		}
		public void setLon(String lon) {
			this.lon = lon;
		}
		public LocationIQResponse(String lat, String lon) {
			super();
			this.lat = lat;
			this.lon = lon;
		}
		public LocationIQResponse() {
			super();
		}
		@Override
		public String toString() {
			return "LocationIQResponse [lat=" + lat + ", lon=" + lon + "]";
		}
}
	    
		