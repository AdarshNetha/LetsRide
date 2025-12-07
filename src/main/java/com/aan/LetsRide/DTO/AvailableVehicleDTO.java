package com.aan.LetsRide.DTO;

import java.util.List;

import com.aan.LetsRide.entity.Customer;

public class AvailableVehicleDTO {
private Customer c;
private int distance;
private String sourceLocation;
private String destinationLocation;
private List<Vehicledetails> availablevehicles;
public Customer getC() {
	return c;
}
public void setC(Customer c) {
	this.c = c;
}
public int getDistance() {
	return distance;
}
public void setDistance(int distance) {
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
public AvailableVehicleDTO() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "AvailableVehicleDTO [c=" + c + ", distance=" + distance + ", sourceLocation=" + sourceLocation
			+ ", destinationLocation=" + destinationLocation + ", availablevehicles=" + availablevehicles + "]";
}


}
