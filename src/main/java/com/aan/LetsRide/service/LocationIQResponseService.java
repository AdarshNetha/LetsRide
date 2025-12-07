package com.aan.LetsRide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.entity.LocationIQResponse;
@Service
public class LocationIQResponseService {
	@Autowired
	private RestTemplate restTemplate;

	

	    private String LOCATIONIQ_API = "https://us1.locationiq.com/v1/search";

	    public String validateCityFromAPI(String cityName) {
	        try {
	            String url = LOCATIONIQ_API + "?key=YOUR_API_KEY&q=" + cityName + "&format=json";

	            ResponseEntity<LocationIQResponse[]> response =
	                    restTemplate.getForEntity(url, LocationIQResponse[].class);

	            if (response.getBody() != null && response.getBody().length > 0) {
	                return response.getBody()[0].getDisplay_name();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return cityName;
	    }
	}




