package com.aan.LetsRide.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private String gender;
	private long mobileno;
	private String mail;
	private String currentLoc;
	@OneToMany(cascade = CascadeType.ALL)
	List<Booking> bookinglist;
	private boolean activeBookingFlag;
	
	
	public boolean isActiveBookingFlag() {
		return activeBookingFlag;
	}
	public void setActiveBookingFlag(boolean activeBookingFlag) {
		this.activeBookingFlag = activeBookingFlag;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getMobno() {
		return mobileno;
	}
	public void setMobno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getCurrentLoc() {
		return currentLoc;
	}
	public void setCurrentLoc(String currentLoc) {
		this.currentLoc = currentLoc;
	}
	public List<Booking> getBookinglist() {
		return bookinglist;
	}
	public void setBookinglist(List<Booking> bookinglist) {
		this.bookinglist = bookinglist;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int id, String name, int age, String gender, long mobileno, String mail, String currentLoc,
			List<Booking> bookinglist, boolean activeBookingFlag) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileno = mobileno;
		this.mail = mail;
		this.currentLoc = currentLoc;
		this.bookinglist = bookinglist;
		this.activeBookingFlag = activeBookingFlag;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", mobileno="
				+ mobileno + ", mail=" + mail + ", currentLoc=" + currentLoc + ", bookinglist=" + bookinglist
				+ ", activeBookingFlag=" + activeBookingFlag + "]";
	}
	
	
	
	
	
		

}
