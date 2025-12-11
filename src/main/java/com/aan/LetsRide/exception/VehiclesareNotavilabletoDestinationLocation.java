package com.aan.LetsRide.exception;

public class VehiclesareNotavilabletoDestinationLocation extends RuntimeException {


	public VehiclesareNotavilabletoDestinationLocation(String Source) {
		super("VehiclesareNotavilabletoDestinationLocation :"+Source);
	}

}
