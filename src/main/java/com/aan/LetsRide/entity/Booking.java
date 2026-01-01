 package com.aan.LetsRide.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
    private Customer cust;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;

    private String sourceLocation;
    private String destinationLocation;
    private double distanceTravelled;
    private double fare;
    private String estimationTravelTime;
    private LocalDateTime bookingDate;
    private String cancellationstatus;
    private String paymentStatus = "NOT PAID";
    private int otp;
    private boolean otpverified = false;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Payment payment;

    private String bookingStatus = "BOOKED";

    // getters & setters only (NO toString)



	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public boolean isOtpverified() {
		return otpverified;
	}
	public void setOtpverified(boolean otpverified) {
		this.otpverified = otpverified;
	}
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
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
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
	
	public String getCancellationstatus() {
		return cancellationstatus;
	}
	public void setCancellationstatus(String cancellationstatus) {
		this.cancellationstatus = cancellationstatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	public Booking() {
		super();
	}
	public Booking(int id, Customer cust, Driver driver, String sourceLocation, String destinationLocation,
			double distanceTravelled, double fare, String estimationTravelTime, LocalDateTime bookingDate,
			String cancellationstatus, String paymentStatus, int otp, boolean otpverified, Payment payment,
			String bookingStatus) {
		super();
		this.id = id;
		this.cust = cust;
		this.driver = driver;
		this.sourceLocation = sourceLocation;
		this.destinationLocation = destinationLocation;
		this.distanceTravelled = distanceTravelled;
		this.fare = fare;
		this.estimationTravelTime = estimationTravelTime;
		this.bookingDate = bookingDate;
		this.cancellationstatus = cancellationstatus;
		this.paymentStatus = paymentStatus;
		this.otp = otp;
		this.otpverified = otpverified;
		this.payment = payment;
		this.bookingStatus = bookingStatus;
	}

	
	
	
}
