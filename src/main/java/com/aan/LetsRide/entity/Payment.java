package com.aan.LetsRide.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	@Id
	private int paymentid;
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	@ManyToOne(cascade = CascadeType.ALL)
	private Vehicle vehicle;
	@OneToOne
	private Booking booking;
	private double amount;
	private String paymentType;
	
	
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public Payment() {
		super();
	}
	@Override
	public String toString() {
		return "Payment [paymentid=" + paymentid + ", customer=" + customer + ", vehicle=" + vehicle + ", booking="
				+ booking + ", amount=" + amount + ", paymentType=" + paymentType + "]";
	}
	public Payment(int paymentid, Customer customer, Vehicle vehicle, Booking booking, double amount,
			String paymentType) {
		super();
		this.paymentid = paymentid;
		this.customer = customer;
		this.vehicle = vehicle;
		this.booking = booking;
		this.amount = amount;
		this.paymentType = paymentType;
	}
	
	

}
