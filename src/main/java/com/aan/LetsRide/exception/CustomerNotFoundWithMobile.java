package com.aan.LetsRide.exception;

public class CustomerNotFoundWithMobile extends RuntimeException{

	public CustomerNotFoundWithMobile(long mobileno) {
		super("CustomerNotFoundWithMobile"+mobileno);
	}

}
