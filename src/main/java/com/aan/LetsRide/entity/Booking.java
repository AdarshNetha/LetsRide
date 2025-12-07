package com.aan.LetsRide.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	  @ManyToOne
	   @JoinColumn(name = "customer_id")
	private Customer cust;
	  @ManyToOne
	    @JoinColumn(name = "driver_id")
	private Driver driver;
	private String sourceLocation;
	private String destinationLocation;
	private double distanceTravelled;
	private double fare;
	private String estimationTravelTime;
	private LocalDateTime bookingDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public Driver getDriv() {
		return driver;
	}
	public void setDriv(Driver driv) {
		this.driver = driv;
	}
	public String getSorceLocation() {
		return sourceLocation;
	}
	public void setSorceLocation(String sorceLocation) {
		this.sourceLocation = sorceLocation;
	}
	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	public double getDistanceTravelled() {
		return distanceTravelled;
	}
	public void setDistanceTravelled(double distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getEstimationTravelTime() {
		return estimationTravelTime;
	}
	public void setEstimationTravelTime(String estimationTravelTime) {
		this.estimationTravelTime = estimationTravelTime;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
