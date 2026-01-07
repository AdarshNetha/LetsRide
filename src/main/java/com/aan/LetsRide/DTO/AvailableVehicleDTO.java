package com.aan.LetsRide.DTO;

import java.util.List;

import com.aan.LetsRide.entity.Customer;

public class AvailableVehicleDTO {
private int cid;

private double distance;
private String sourceLocation;
private String destinationLocation;
private List<Vehicledetails> availablevehicles;
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public double getDistance() {
	return distance;
}
public void setDistance(double distance) {
	this.distance = distance;
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
public List<Vehicledetails> getAvailablevehicles() {
	return availablevehicles;
}
public void setAvailablevehicles(List<Vehicledetails> availablevehicles) {
	this.availablevehicles = availablevehicles;
}
public AvailableVehicleDTO(int cid, double distance, String sourceLocation, String destinationLocation,
		List<Vehicledetails> availablevehicles) {
	super();
	this.cid = cid;
	this.distance = distance;
	this.sourceLocation = sourceLocation;
	this.destinationLocation = destinationLocation;
	this.availablevehicles = availablevehicles;
}
public AvailableVehicleDTO() {
	super();
	// TODO Auto-generated constructor stub
}


}