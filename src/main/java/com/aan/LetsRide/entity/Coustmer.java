package com.aan.LetsRide.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Coustmer {
	@Id
	private int id;
	private String name;
	private int age;
	private String gender;
	private long mobno;
	private String currentLoc;
	@OneToMany(cascade = CascadeType.ALL)
	List<Booking> bookinglist;
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
		return mobno;
	}
	public void setMobno(long mobno) {
		this.mobno = mobno;
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
	public Coustmer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Coustmer [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", mobno=" + mobno
				+ ", currentLoc=" + currentLoc + ", bookinglist=" + bookinglist + "]";
	}
	
		

}
