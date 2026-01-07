package com.aan.LetsRide.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



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
    private double penalty = 0.0;
    private boolean activeBookingFlag;

    @OneToMany(mappedBy = "cust", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Booking> bookinglist = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private Userr userr;
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
	public String getCurrentLoc() {
		return currentLoc;
	}
	public void setCurrentLoc(String currentLoc) {
		this.currentLoc = currentLoc;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	public List<Booking> getBookinglist() {
		return bookinglist;
	}
	public void setBookinglist(List<Booking> bookinglist) {
		this.bookinglist = bookinglist;
	}
	public boolean isActiveBookingFlag() {
		return activeBookingFlag;
	}
	public void setActiveBookingFlag(boolean activeBookingFlag) {
		this.activeBookingFlag = activeBookingFlag;
	}
	public Userr getUserr() {
		return userr;
	}
	public void setUserr(Userr userr) {
		this.userr = userr;
	}
	public Customer(int id, String name, int age, String gender, long mobileno, String mail, String currentLoc,
			double penalty, List<Booking> bookinglist, boolean activeBookingFlag, Userr userr) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileno = mobileno;
		this.mail = mail;
		this.currentLoc = currentLoc;
		this.penalty = penalty;
		this.bookinglist = bookinglist;
		this.activeBookingFlag = activeBookingFlag;
		this.userr = userr;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
