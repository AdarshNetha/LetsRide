package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.BookingHistoryDto;
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

@GetMapping("/driver/{mobileNo}")
public  ResponseStructure<Driver>findDriver(@PathVariable long mobileNo){
	return driverservice.findDriver(mobileNo);	
}

@PostMapping("/update")
public ResponseStructure<Driver> updatemobilebylocation(@RequestParam double lattitude,@RequestParam double longitude,@RequestParam("mobileNo") Long mobileNo) {
	
	return driverservice.updateDriver(lattitude,longitude,mobileNo);
	
}

@DeleteMapping("/deleteByPhoneNO")
public ResponseStructure<Driver> delete(@RequestParam long mobileNo)
{
	return driverservice.deleteById(mobileNo);
}


//adarsh
@PostMapping("/driver/seeAllbooking")
public ResponseStructure<BookingHistoryDto> seeBookingHistory(@RequestParam long mobileNo)
{
	return driverservice.seeBookingHistory(mobileNo);
}
//vamshi






//rakshitha





}






