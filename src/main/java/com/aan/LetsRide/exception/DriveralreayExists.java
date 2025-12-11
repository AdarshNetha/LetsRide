package com.aan.LetsRide.exception;

public class DriveralreayExists extends RuntimeException {

	public DriveralreayExists(String string) {
		super("DriveralreayExists"+string);
	}

}
