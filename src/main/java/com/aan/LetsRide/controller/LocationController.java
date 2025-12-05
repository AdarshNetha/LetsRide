package com.aan.LetsRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aan.LetsRide.ResponseStructure;
import com.aan.LetsRide.service.LocationService;


@RestController
public class LocationController {
	@Autowired
    private LocationService locationService;

	

	    @GetMapping("/getLocation")
	    public ResponseEntity<ResponseStructure<String>> getLocation(
	            @RequestParam double latitude,
	            @RequestParam double longitude) {

	        String city = locationService.getCityFromCoordinates(latitude, longitude);

	        ResponseStructure<String> rs = new ResponseStructure<>();
	        rs.setStatuscode(200);
	        rs.setMessage("City fetched successfully");
	        rs.setData(city);

	        return ResponseEntity.ok(rs);
	    }

}
