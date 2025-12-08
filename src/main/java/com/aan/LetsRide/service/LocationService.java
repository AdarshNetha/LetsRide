package com.aan.LetsRide.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.DTO.LocationCoordinatesdto;
import com.aan.LetsRide.entity.LocationIQResponse;

@Service
public class LocationService {

	
		 @Autowired
		    private RestTemplate restTemplate;

		    private final String API_KEY = "pk.80284aba0cfb865f9e909292ea859f9f";

		    public String getCityFromCoordinates(double lattitude, double longitude) {

		        String url = 
		        		"https://us1.locationiq.com/v1/reverse.php?key=" + API_KEY +

	                     "&lat=" + lattitude +

	                     "&lon=" + longitude +

	                     "&format=json"; 
		        


		        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

		        Map<String, Object> address = (Map<String, Object>) response.get("address");

		        return (String) address.get("city");
		        
		    }

			

			
		    
			

			
	}
	
	


