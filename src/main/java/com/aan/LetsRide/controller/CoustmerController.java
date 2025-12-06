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
import com.aan.LetsRide.DTO.CoustmerDTO;
import com.aan.LetsRide.entity.Coustmer;
import com.aan.LetsRide.entity.Vehicle;
import com.aan.LetsRide.service.DriverService;

@RestController
public class CoustmerController {
	
	@Autowired
	private DriverService ds;
	
	@PostMapping("/coustmer/register")
//	adarsh
	public ResponseStructure<Coustmer> registerCoustmer(@RequestBody CoustmerDTO cdto)
	{
		return ds.registerCoustmer(cdto);
	}
	
	@GetMapping("/coustmer/Find")
	
	
//	rakshitha
public ResponseStructure<Coustmer> findcustomer(@RequestParam long mobileno){
		return ds.findCustomer(mobileno);
		
	}
	
	
	@GetMapping("/available")
	public ResponseStructure<List<Vehicle>> getAvailableVehicles(@RequestParam String city) {
		return ds.getAvailableVehiclesByCity(city);
		
	}
	
//	vamshi
	
	
//	@DeleteMapping("/coustmer/DeleteCoustemr")
	@DeleteMapping("/deletebymobileNo")
	public ResponseStructure<Coustmer> deleteCoustmer(@RequestParam long mobileno){
		return ds.deleteBymbno(mobileno);
		
	}
//	vishnu

}
