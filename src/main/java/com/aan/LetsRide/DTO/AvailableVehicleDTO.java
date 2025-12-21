package com.aan.LetsRide.DTO;

import java.util.List;

import com.aan.LetsRide.entity.Customer;

public class AvailableVehicleDTO {
private Customer c;
private double distance;
private String sourceLocation;
private String destinationLocation;
private List<Vehicledetails> availablevehicles;
public Customer getC() {
	return c;
}
public void setC(Customer c) {
	this.c = c;
}
public double getDistance() {
	return distance;
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

public void setDistance(double distance) {
	this.distance = distance;
}
public AvailableVehicleDTO() {
	super();

	
	
}





public AvailableVehicleDTO(Customer c, double distance, String sourceLocation, String destinationLocation,
		List<Vehicledetails> availablevehicles) {
	super();
	this.c = c;
	this.distance = distance;
	this.sourceLocation = sourceLocation;
	this.destinationLocation = destinationLocation;
	this.availablevehicles = availablevehicles;
}
@Override
public String toString() {
	return "AvailableVehicleDTO [c=" + c + ", distance=" + distance + ", sourceLocation=" + sourceLocation
			+ ", destinationLocation=" + destinationLocation + ", availablevehicles=" + availablevehicles + "]";
}


}
