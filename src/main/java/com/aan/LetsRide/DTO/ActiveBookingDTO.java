package com.aan.LetsRide.DTO;

import com.aan.LetsRide.entity.Booking;

public class ActiveBookingDTO {
   private String custname;
   private long custmobileno;
   private Booking booking;
   private String currentlocation;
   public String getCustname() {
	return custname;
   }
   public void setCustname(String custname) {
	this.custname = custname;
   }
   public long getCustmobileno() {
	return custmobileno;
   }
   public void setCustmobileno(long custmobileno) {
	this.custmobileno = custmobileno;
   }
   public Booking getBooking() {
	return booking;
   }
   public void setBooking(Booking booking) {
	this.booking = booking;
   }
   public String getCurrentlocation() {
	return currentlocation;
   }
   public void setCurrentlocation(String currentlocation) {
	this.currentlocation = currentlocation;
   }
   public ActiveBookingDTO(String custname, long custmobileno, Booking booking, String currentlocation) {
	super();
	this.custname = custname;
	this.custmobileno = custmobileno;
	this.booking = booking;
	this.currentlocation = currentlocation;
   }
   public ActiveBookingDTO() {
	super();
	// TODO Auto-generated constructor stub
   }
   @Override
   public String toString() {
	return "ActiveBookingDTO [custname=" + custname + ", custmobileno=" + custmobileno + ", booking=" + booking
			+ ", currentlocation=" + currentlocation + "]";
   }
   
   
}
