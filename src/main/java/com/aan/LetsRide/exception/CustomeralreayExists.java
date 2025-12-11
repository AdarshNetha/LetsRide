package com.aan.LetsRide.exception;

public class CustomeralreayExists extends RuntimeException {

	public CustomeralreayExists(String string) {
		super("CustomeralreayExists"+string);
	}

}
