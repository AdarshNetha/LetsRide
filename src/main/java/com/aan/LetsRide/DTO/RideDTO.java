package com.aan.LetsRide.DTO;

public class RideDTO {
	private int bookingId;
	
	private String fromLocation;
	private String toLocation;
	private double distance;
	private double fare;
	
	public RideDTO(int bookingId, String fromLocation, String toLocation, double distance, double fare) {
		super();
		this.bookingId = bookingId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.distance = distance;
		this.fare = fare;
	}
	public RideDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public void setDistance(double distance) {
		this.distance = distance;
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
		return "RideDTO [bookingId=" + bookingId + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation
				+ ", distance=" + distance + ", fare=" + fare + "]";
	}
	
	
	

}
