package com.aan.LetsRide.DTO;

import com.aan.LetsRide.entity.Vehicle;

public class Vehicledetails {
private Vehicle v;
private double fare;
private double estimationtime;
public Vehicle getV() {
	return v;
}
public void setV(Vehicle v) {
	this.v = v;
}
public double getFare() {
	return fare;
}
public void setFare(double fare) {
	this.fare = fare;
}
public double getEstimationtime() {
	return estimationtime;
}
public void setEstimationtime(double estimationtime) {
	this.estimationtime = estimationtime;
}

public Vehicledetails(Vehicle v, double fare, double estimationtime) {
	super();
	this.v = v;
	this.fare = fare;
	this.estimationtime = estimationtime;
}
public Vehicledetails() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Vehicledetails [v=" + v + ", fare=" + fare + ", estimationtime=" + estimationtime + "]";
}

}
