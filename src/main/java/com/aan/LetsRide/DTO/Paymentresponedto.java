package com.aan.LetsRide.DTO;

public class Paymentresponedto {
	private int paymentId;
    private double amount;
    private String paymentType;
    private String paymentStatus;
    private String bookingStatus;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Paymentresponedto(int paymentId, double amount, String paymentType, String paymentStatus,
			String bookingStatus) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.bookingStatus = bookingStatus;
	}
	
	public Paymentresponedto() {
		super();
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
    

}
