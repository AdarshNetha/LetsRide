package com.aan.LetsRide.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Vehicle {
@Id
private int id;
private String vehilename;
private String vehileno;
private String type;
private String model;
private String capacity;
private String currentcity;
private String availabilityStatus="Available";
private double priceperKM;
@OneToOne
@MapsId  
@JoinColumn(name = "id")
private Driver driver;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getVehilename() {
	return vehilename;
}
public void setVehilename(String vehilename) {
	this.vehilename = vehilename;
}
public String getVehileno() {
	return vehileno;
}
public void setVehileno(String vehileno) {
	this.vehileno = vehileno;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getCapacity() {
	return capacity;
}
public void setCapacity(String capacity) {
	this.capacity = capacity;
}
public String getCurrentcity() {
	return currentcity;
}
public void setCurrentcity(String currentcity) {
	this.currentcity = currentcity;
}
public String getAvailabilityStatus() {
	return availabilityStatus;
}
public void setAvailabilityStatus(String availabilityStatus) {
	this.availabilityStatus = availabilityStatus;
}
public double getPriceperKM() {
	return priceperKM;
}
public void setPriceperKM(double priceperKM) {
	this.priceperKM = priceperKM;
}
public Driver getDriver() {
	return driver;
}
public void setDriver(Driver driver) {
	this.driver = driver;
}
public Vehicle(int id, String vehilename, String vehileno, String type, String model, String capacity,
		String currentcity, String availabilityStatus, double priceperKM, Driver driver) {
	super();
	this.id = id;
	this.vehilename = vehilename;
	this.vehileno = vehileno;
	this.type = type;
	this.model = model;
	this.capacity = capacity;
	this.currentcity = currentcity;
	this.availabilityStatus = availabilityStatus;
	this.priceperKM = priceperKM;
	this.driver = driver;
}
public Vehicle() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Vehicle [id=" + id + ", vehilename=" + vehilename + ", vehileno=" + vehileno + ", type=" + type + ", model="
			+ model + ", capacity=" + capacity + ", currentcity=" + currentcity + ", availabilityStatus="
			+ availabilityStatus + ", priceperKM=" + priceperKM + ", driver=" + driver + "]";
}




}
