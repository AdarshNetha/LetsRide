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
	
//	customeralreadyExits
	@ExceptionHandler(CustomeralreadyExists.class)
public ResponseStructure<String> CustomeralreadyExists( CustomeralreadyExists ex){
		
        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(ex.getMessage());
        rs.setData(null);

        return rs;
	
	
	}
	
	
	//vehicalesareNotavaliabletoDestinationLocation
	@ExceptionHandler(VehiclesareNotavilabletoDestinationLocation.class)
public ResponseStructure<String> VehiclesareNotavilabletoDestinationLocation( VehiclesareNotavilabletoDestinationLocation ex){
		
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        responseStructure .setStatuscode(HttpStatus.NOT_FOUND.value());
        responseStructure .setMessage(ex.getMessage());
        responseStructure .setData(null);

        return responseStructure ;
	}
	
	
	
	
	
	
	
	
}
