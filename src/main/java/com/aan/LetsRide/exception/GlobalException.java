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
	
	
	      @ExceptionHandler(DriveralreayExists.class)
	      public ResponseStructure<String> DriverAlreadyExist(DriveralreayExists dr){
	  		

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


	

        @ExceptionHandler(CustomeralreayExists.class)
        public ResponseStructure<String>CustomeralreayExists(CustomeralreayExists custmob){
        	 ResponseStructure<String> rs = new ResponseStructure<>();
             rs.setStatuscode(HttpStatus.NOT_FOUND.value());
             rs.setMessage(custmob.getMessage());
             rs.setData(null);

             return rs;
        }
        @ExceptionHandler(VehiclesareNotavilabletoDestinationLocation.class)
        public ResponseStructure<String>VehiclesareNotavilabletoDestinationLocation(VehiclesareNotavilabletoDestinationLocation custmob){
       	 ResponseStructure<String> rs = new ResponseStructure<>();
            rs.setStatuscode(HttpStatus.NOT_FOUND.value());
            rs.setMessage(custmob.getMessage());
            rs.setData(null);

            return rs;
       }
        
       @ExceptionHandler(CustomerNotFoundWithThisID.class)
       public ResponseStructure<String> CustomerNotFoundException(CustomerNotFoundWithThisID custid) {
    	   ResponseStructure<String> rs=new ResponseStructure<String>();
    	   rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	   rs.setMessage(custid.getMessage());
    	   rs.setData(null);
    	   return rs;
       }
	
       @ExceptionHandler(BookingNotFoungWithThisID.class)
       public ResponseStructure<String> BookingNotFoungException(BookingNotFoungWithThisID bookingid) {
    	   ResponseStructure<String> rs= new ResponseStructure<String>();
    	   rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	   rs.setMessage(bookingid.getMessage());
    	   rs.setData(null);
    	   return rs;
       }
       @ExceptionHandler(AlradyHasBooking.class)
       public ResponseStructure<String> alradyHasBooking(AlradyHasBooking alradyHasBooking) {
    	   ResponseStructure<String> rs= new ResponseStructure<String>();
    	   rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	   rs.setMessage(alradyHasBooking.getMessage());
    	   rs.setData(null);
    	   return rs; 
       }
       
       @ExceptionHandler(VechileNotAvailable.class)
       public ResponseStructure<String> vechileNotAvailable(VechileNotAvailable vechileNotAvailable) {
    	   ResponseStructure<String> rs= new ResponseStructure<String>();
    	   rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	   rs.setMessage(vechileNotAvailable.getMessage());
    	   rs.setData(null);
    	   return rs; 
       }
       
	
	}


	
	
	
	
	
	
	

