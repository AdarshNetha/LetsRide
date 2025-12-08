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

			
		    public LocationCoordinatesdto getCoordinates(String cityName) {

		        try {
		            String url = "https://api.openrouteservice.org/v2/directions/driving-car"+ "?key=YOUR_API_KEY&q=" + cityName + "&format=json";

		            ResponseEntity<LocationIQResponse[]> response =
		                    restTemplate.getForEntity(url, LocationIQResponse[].class);

		            if (response.getBody() != null && response.getBody().length > 0) {

		                LocationIQResponse data = response.getBody()[0];
		                LocationCoordinatesdto lc = new LocationCoordinatesdto();

		                lc.setDisplayName(data.getDisplay_name());
		                lc.setLat(Double.parseDouble(data.getLat()));
		                lc.setLon(Double.parseDouble(data.getLon()));

		                return lc;
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		        return null;
		    }

			

			
	}
	
	


