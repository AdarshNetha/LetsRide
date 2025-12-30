package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveBookingDTO;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingDto;
import com.aan.LetsRide.DTO.BookingHistoryDto;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Payment;

import com.aan.LetsRide.service.DriverService;

@RestController
@RequestMapping("/coustmer")
public class CoustomerController {
	
	@Autowired
	private DriverService ds;
	
	
	
	
	@GetMapping("/coustomer/Find")
    public ResponseStructure<Customer> findcustomer(@RequestParam long mobileno){
		return ds.findCustomer(mobileno);
		
	}
	
	
	@GetMapping("/coustomer/available")
	public ResponseStructure<AvailableVehicleDTO> getAvailableVehicles(@RequestParam long mobileno ,@RequestParam String distinationLocation) {
		return ds.getAvailableVehiclesByCity(mobileno,distinationLocation);
		
	}
	

	
	

	@DeleteMapping("/coustomer/deletebymobileNo")
	public ResponseStructure<Customer> deleteCoustmer(@RequestParam long mobileno){
		return ds.deleteBymbno(mobileno);
		
	}

	@PutMapping("/coustomer/bookVehicle/{mobno}")
	public ResponseStructure<Booking> bookVehicle(@PathVariable long mobno,@RequestBody BookingDto bookingdto) {
		 return ds.bookVehicle(mobno,bookingdto);
	}
	

	  
	@GetMapping("/customer/seeactivebooking/{mobileno}")
	public ResponseStructure<ActiveBookingDTO> Seeactivebooking(@PathVariable long mobileno) {
		return ds.Seeactivebooking(mobileno);
	}

	

		

	@GetMapping("/coustomer/seeBookingHistory")
	public ResponseStructure<BookingHistoryDto> seeBookingHistory(@RequestParam long mobileNO)
	{
		return ds.seeBookingHistoryOfCustmer(mobileNO);
	}





	@PostMapping("/customer/cancelbooking")
	public ResponseStructure<Customer> Customercancelbooking(@RequestParam int bookingid,@RequestParam int customerid)
	{
		  return ds.CustomerCancelBooking(bookingid,customerid);
		  
		  
	 }
    
	 
	@GetMapping("/customer/pickup")
	public ResponseStructure<Customer> Pickup(@RequestParam int bookingid) {
		return ds.SendotpToTheCustomer(bookingid);
	}
	
	

}

