package com.aan.LetsRide.exception;

public class CustomeralreadyExists extends RuntimeException {

	public CustomeralreadyExists( long mobileno) {
		super("customeralreadyExists"+mobileno);
	}
	

}
