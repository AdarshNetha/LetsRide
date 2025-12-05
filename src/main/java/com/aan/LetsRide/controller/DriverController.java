package com.aan.LetsRide.controller;

import java.net.ResponseCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.service.DriverService;

@RestController
public class DriverController {
@Autowired
private DriverService driverservice;

@PostMapping("/registerDriver")
public ResponseStructure<Driver> saveregdriver(@RequestBody RegDriverVehicleDTO dto) {
    return driverservice.saveRegDriver(dto);
}
	

// vishnu


//@PutMapping("/update")

//vamshi


@DeleteMapping("/delete")
public ResponseStructure<Driver> deleteById(@RequestHeader int id)
{
	return driverservice.deleteById(id);
}

@GetMapping("/driver/{mobileNo}")
public  ResponseStructure<Driver>findDriver(@PathVariable long mobileNo){
	return driverservice.findDriver(mobileNo);
	
}


}






