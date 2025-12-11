package com.aan.LetsRide.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aan.LetsRide.ResponseStructure;


@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(DriverNOtFoundWiththismobileNO.class)
	public ResponseStructure<String> DriverNOtFoundmobile(DriverNOtFoundWiththismobileNO dr){
		

		        ResponseStructure<String> rs = new ResponseStructure<>();
		        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		        rs.setMessage(dr.getMessage());
		        rs.setData(null);

		        return rs;
		
	}
	
	@ExceptionHandler(VehicleOtFoundWiththismobileNO.class)
	public ResponseStructure<String> VehicleNOtFoundmobile(VehicleOtFoundWiththismobileNO vh){
		

		        ResponseStructure<String> rs = new ResponseStructure<>();
		        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		        rs.setMessage(vh.getMessage());
		        rs.setData(null);

		        return rs;
		
	}
	@ExceptionHandler(CustomerNotFoundWithMobile.class)
	public ResponseStructure<String>  CustomerNotFoundWithMobile( CustomerNotFoundWithMobile custmob){
		

        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(custmob.getMessage());
        rs.setData(null);

        return rs;

}

	@ExceptionHandler(VehicleAlreadyBookedException.class)
	public ResponseStructure<String> VehicleAlreadyBooked(VehicleAlreadyBookedException vehiclebooked){
		
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(vehiclebooked.getMessage());
		responseStructure.setData(null);
		
	   return responseStructure;
	}
	
	
	
	
	
	
	
}
