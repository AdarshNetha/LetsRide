package com.aan.LetsRide.DTO;

public class RideDTO {
	
	private String fromLocation;
	private String toLocation;
	private double distance;
	private double fare;
	public RideDTO(String fromLocation, String toLocation, double d, double fare) {
		super();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.distance = d;
		this.fare = fare;
	}
	public RideDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	@Override
	public String toString() {
		return "RideDTO [fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", distance=" + distance
				+ ", fare=" + fare + "]";
	}
	
	

}
