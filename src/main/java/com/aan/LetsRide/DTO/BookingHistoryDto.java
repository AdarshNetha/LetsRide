package com.aan.LetsRide.DTO;

import java.util.List;

public class BookingHistoryDto {
	List<RideDTO> history;
	double totalAmount;
	public List<RideDTO> getHistory() {
		return history;
	}
	public void setHistory(List<RideDTO> history) {
		this.history = history;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BookingHistoryDto(List<RideDTO> history, double totalAmount) {
		super();
		this.history = history;
		this.totalAmount = totalAmount;
	}
	public BookingHistoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BookingHistoryDto [history=" + history + ", totalAmount=" + totalAmount + "]";
	}
	
	

}
