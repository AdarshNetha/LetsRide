package com.aan.LetsRide.DTO;

public class BookingDto {
	private  int vehicleid;
	private String sourceLocation;
	private String destinationLocation;
	private double distanceTravelled;
	private double fare;
	private String estimationTravelTime;
	public int getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	public double getDistanceTravelled() {
		return distanceTravelled;
	}
	public void setDistanceTravelled(double distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	
	public String getEstimationTravelTime() {
		return estimationTravelTime;
	}
	public void setEstimationTravelTime(String estimationTravelTime) {
		this.estimationTravelTime = estimationTravelTime;
	}
	public BookingDto(int vehicleid, String sourceLocation, String destinationLocation, double distanceTravelled,
			double fare, String estimationTravelTime) {
		super();
		this.vehicleid = vehicleid;
		this.sourceLocation = sourceLocation;
		this.destinationLocation = destinationLocation;
		this.distanceTravelled = distanceTravelled;
		this.fare = fare;
		this.estimationTravelTime = estimationTravelTime;
	}
	@Override
	public String toString() {
		return "BookingDto [vehicleid=" + vehicleid + ", sourceLocation=" + sourceLocation + ", destinationLocation="
				+ destinationLocation + ", distanceTravelled=" + distanceTravelled + ", fare=" + fare
				+ ", estimationTravelTime=" + estimationTravelTime + "]";
	}
	
	
	
}
