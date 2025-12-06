package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.CoustmerDTO;
import com.aan.LetsRide.entity.Coustmer;
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
	
	
	
	
//	@GetMapping("/coustmer/Find")
	
//	rakshitha
	
	
//	@GetMapping("/coustmer/SeeAllVEchiles")
	
//	vamshi
	
	
//	@DeleteMapping("/coustmer/DeleteCoustemr")
	
//	vishnu

}
