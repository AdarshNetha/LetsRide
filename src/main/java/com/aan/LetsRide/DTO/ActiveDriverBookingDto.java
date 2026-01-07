package com.aan.LetsRide.DTO;

public class ActiveDriverBookingDto {
	private int bookingid;
	private String customerName;
	private long mobileNo;
	private String source;
	private String destination;
	private double distance;
	private double cost;
	private String bookingstatus;
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(String bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	public ActiveDriverBookingDto(int bookingid, String customerName, long mobileNo, String source, String destination,
			double distance, double cost, String bookingstatus) {
		super();
		this.bookingid = bookingid;
		this.customerName = customerName;
		this.mobileNo = mobileNo;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.cost = cost;
		this.bookingstatus = bookingstatus;
	}
	public ActiveDriverBookingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ActiveDriverBookingDto [bookingid=" + bookingid + ", customerName=" + customerName + ", mobileNo="
				+ mobileNo + ", source=" + source + ", destination=" + destination + ", distance=" + distance
				+ ", cost=" + cost + ", bookingstatus=" + bookingstatus + "]";
	}
	

}
