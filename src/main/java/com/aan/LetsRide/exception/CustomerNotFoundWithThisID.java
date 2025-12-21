package com.aan.LetsRide.exception;

public class CustomerNotFoundWithThisID extends RuntimeException {

	public CustomerNotFoundWithThisID(String string) {
		super("CustomerNotFoundWithThisID"+string);
		// TODO Auto-generated constructor stub
	}

}
