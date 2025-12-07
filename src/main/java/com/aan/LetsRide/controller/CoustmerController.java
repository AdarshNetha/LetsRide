package com.aan.LetsRide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.AvailableVehicleDTO;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Vehicle;
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
	public ResponseStructure<AvailableVehicleDTO> getAvailableVehicles(@RequestParam Long mobileno ,@RequestParam String destinationLocation) {
		return ds.getAvailableVehiclesByCity(mobileno,destinationLocation);
		
	}
	
//	vamshi
	
	
//	@DeleteMapping("/customer/DeleteCoustemr")
	@DeleteMapping("/deletebymobileNo")
	public ResponseStructure<Customer> deleteCoustmer(@RequestParam long mobileno){
		return ds.deleteBymbno(mobileno);
		
	}
//	vishnu

}
