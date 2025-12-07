package com.aan.LetsRide.entity;

public class LocationIQResponse {
	 private String display_name;
	    private String lat;
	    private String lon;
		public String getDisplay_name() {
			return display_name;
		}
		public void setDisplay_name(String display_name) {
			this.display_name = display_name;
		}
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
		@Override
		public String toString() {
			return "LocationIQResponse [display_name=" + display_name + ", lat=" + lat + ", lon=" + lon + "]";
		}
		public LocationIQResponse() {
			super();
		}
		public LocationIQResponse(String display_name, String lat, String lon) {
			super();
			this.display_name = display_name;
			this.lat = lat;
			this.lon = lon;
		}

}
