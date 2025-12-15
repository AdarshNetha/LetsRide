package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.ActiveBookingDTO;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.BookingDto;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.entity.Booking;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Payment;
import com.aan.LetsRide.service.DriverService;

@RestController
public class CoustmerController {
	
	@Autowired
	private DriverService ds;
	 
	
	@PostMapping("/coustmer/register")
//	adarsh
	public ResponseStructure<Customer> registerCoustmer(@RequestBody CustomerDTO cdto)
	{
		return ds.registerCustomer(cdto);
	}
	
	@GetMapping("/coustmer/Find")
//	rakshitha
public ResponseStructure<Customer> findcustomer(@RequestParam long mobileno){
		return ds.findCustomer(mobileno);
		
	}
	
	
	@GetMapping("/available")
	public ResponseStructure<AvailableVehicleDTO> getAvailableVehicles(@RequestParam Long mobileno ,@RequestParam String distinationLocation) {
		return ds.getAvailableVehiclesByCity(mobileno,distinationLocation);
		
	}
	
//	vamshi
	
	
//	@DeleteMapping("/customer/DeleteCoustemr")
	@DeleteMapping("/deletebymobileNo")
	public ResponseStructure<Customer> deleteCoustmer(@RequestParam long mobileno){
		return ds.deleteBymbno(mobileno);
		
	}
//	vishnu
	@PutMapping("/bookVehicle/{mobno}")
	public ResponseStructure<Booking> bookVehicle(@PathVariable Long mobno,@RequestBody BookingDto bookingdto) {
		 return ds.bookVehicle(mobno,bookingdto);
	}
	

	  
	@GetMapping("/customer/seeactivebooking/{mobileno}")
	public ResponseStructure<ActiveBookingDTO> Seeactivebooking(@PathVariable long mobileno) {
		return ds.Seeactivebooking(mobileno);
	}
	
//	vamshi
	
	@PostMapping("/paybyupi")
	public ResponseStructure<byte[]> PaybyUPI(@RequestParam int bookingid) {
		return ds.Saveupi(bookingid);
	}
	
	
	
//	rakshitha
	@PostMapping("/driver/completedride/paybycash")
	public ResponseStructure<Payment> bookingCompleted(@RequestParam int id, @RequestParam String paymentType) {
		 return ds.confirmPaymentbycash(id,paymentType);
	}
	
	
	
	

}

