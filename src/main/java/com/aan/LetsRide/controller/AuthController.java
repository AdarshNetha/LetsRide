package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.DTO.CustomerDTO;
import com.aan.LetsRide.DTO.LoginDTO;
import com.aan.LetsRide.DTO.RegDriverVehicleDTO;
import com.aan.LetsRide.entity.Customer;
import com.aan.LetsRide.entity.Driver;
import com.aan.LetsRide.service.DriverService;
import com.aan.LetsRide.service.LoginService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/register/customer")
    public ResponseStructure<Customer> registerCustomer(
            @RequestBody CustomerDTO cdto) {
        return driverService.registerCustomer(cdto);
    }

    @PostMapping("/register/driver")
    public ResponseStructure<Driver> registerDriver(
            @RequestBody RegDriverVehicleDTO dto) {
        return driverService.saveRegDriver(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<String>> login(
            @RequestBody LoginDTO dto) {
        return loginService.login(dto);
    }
}




