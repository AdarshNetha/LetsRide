package com.aan.LetsRide.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private Long licenceNo;
private String upiid;
private String name;
private String status="Available";
private int age;
private Long mobileNo;
private String gender;
private String mail;
@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
private Vehicle vehicle;
@OneToMany(mappedBy = "driver")
private List<Booking> bookings;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Long getLicenceNo() {
	return licenceNo;
}
public void setLicenceNo(Long licenceNo) {
	this.licenceNo = licenceNo;
}
public String getUpiid() {
	return upiid;
}
public void setUpiid(String upiid) {
	this.upiid = upiid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public Long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(Long mobileNo) {
	this.mobileNo = mobileNo;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public Vehicle getVehicle() {
	return vehicle;
}
public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
}

public Driver() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Driver [id=" + id + ", licenceNo=" + licenceNo + ", upiid=" + upiid + ", name=" + name + ", status="
			+ status + ", age=" + age + ", mobileNo=" + mobileNo + ", gender=" + gender + ", mail=" + mail
			+  "]";
}



}
