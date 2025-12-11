package com.aan.LetsRide.exception;

public class CustomerNotFoundWithMobile extends RuntimeException{


	public CustomerNotFoundWithMobile(String string) {
		super("CustomerNotFoundWithMobile"+string);

	}

}
