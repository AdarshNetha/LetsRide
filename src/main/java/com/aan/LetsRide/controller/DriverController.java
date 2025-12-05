package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.aan.LetsRide.service.DriverService;

@Controller
public class DriverController {
@Autowired
private DriverService driverservice;

@PostMapping("/driver/registerDriver")

//rakshitha

@DeleteMapping("/driver/deleteDriver")

//adarsh

@GetMapping("/driver/findDribver")


//vishnu

@PutMapping("/drivr/upadateCurrentVechileLocation")

//vamshi

}
