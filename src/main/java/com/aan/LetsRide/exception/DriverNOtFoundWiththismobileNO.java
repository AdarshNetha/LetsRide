package com.aan.LetsRide.exception;

public class DriverNOtFoundWiththismobileNO extends RuntimeException {

	public DriverNOtFoundWiththismobileNO(long mobileNo) {
		super("No Driver found with mobile number: " + mobileNo);
	}


}
