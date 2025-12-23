package com.aan.LetsRide.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
private long licenceNo;
private String upiid;
private String name;
private String status="AVAILABLE";
private int age;
private long mobileNo;
private String gender;
private String mail;
@JsonIgnore
@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
private Vehicle vehicle;
@OneToMany(cascade = CascadeType.ALL)
private List<Booking> bookinglist;
@OneToOne
private Userr userr;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public long getLicenceNo() {
	return licenceNo;
}
public void setLicenceNo(long licenceNo) {
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
public long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(long mobileNo) {
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
public List<Booking> getBookinglist() {
	return bookinglist;
}
public void setBookinglist(List<Booking> bookinglist) {
	this.bookinglist = bookinglist;
}
public Userr getUserr() {
	return userr;
}
public void setUserr(Userr userr) {
	this.userr = userr;
}
public Driver(int id, long licenceNo, String upiid, String name, String status, int age, long mobileNo, String gender,
		String mail, Vehicle vehicle, List<Booking> bookinglist, Userr userr) {
	super();
	this.id = id;
	this.licenceNo = licenceNo;
	this.upiid = upiid;
	this.name = name;
	this.status = status;
	this.age = age;
	this.mobileNo = mobileNo;
	this.gender = gender;
	this.mail = mail;
	this.vehicle = vehicle;
	this.bookinglist = bookinglist;
	this.userr = userr;
}
public Driver() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Driver [id=" + id + ", licenceNo=" + licenceNo + ", upiid=" + upiid + ", name=" + name + ", status="
			+ status + ", age=" + age + ", mobileNo=" + mobileNo + ", gender=" + gender + ", mail=" + mail
			+ ", vehicle=" + vehicle + ", bookinglist=" + bookinglist + ", userr=" + userr + "]";
}





}
