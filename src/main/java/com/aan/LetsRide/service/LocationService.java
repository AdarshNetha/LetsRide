package com.aan.LetsRide.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.DTO.api.LocationRangeDTO;
import com.aan.LetsRide.DTO.api.ValidatingDestination;
import com.aan.LetsRide.exception.InvaildLocationException;

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

		    public boolean validatingCity(String city)
		    {
		    	
		    	String url="https://us1.locationiq.com/v1/search?key="+API_KEY+"&q="+city+",%20India&format=json";
		    	
		    	ValidatingDestination[] validatingCity=restTemplate.getForObject(url, ValidatingDestination[].class);
		    	
		    	String apiCity=validatingCity[0].getDisplay_name();
		    	apiCity=apiCity.substring(0, city.length());		    	
		    	if(city.toUpperCase().equals(apiCity.toUpperCase())){
		    		return true;
		    	}
		    	else
		    	{
		    		return false;
		    	}
		    }
		    
		    public LocationRangeDTO getFromAndToCoordinates(String source,String destinatio )
		    {

		    	String sourceurl="https://us1.locationiq.com/v1/search?key="+API_KEY+"&q="+source+",%20India&format=json";
		    	
		    	ValidatingDestination[] sourcevalidatingCity=restTemplate.getForObject(sourceurl, ValidatingDestination[].class);
		    	
		    	double sourceLongutude=Double.parseDouble(sourcevalidatingCity[0].getLon());
		    	double sourceLatudue=Double.parseDouble(sourcevalidatingCity[0].getLat());
		    	
		    	String url="https://us1.locationiq.com/v1/search?key="+API_KEY+"&q="+destinatio+",%20India&format=json";
		    	
		    	ValidatingDestination[] validatingCity=restTemplate.getForObject(url, ValidatingDestination[].class);
		    	
		    	double destlongute=Double.parseDouble(validatingCity[0].getLon());
		    	double destLatudue=Double.parseDouble(validatingCity[0].getLat());
		    	
		    	LocationRangeDTO locationRangeDTO=new LocationRangeDTO();
		    	
		    	locationRangeDTO.setFromLatitude(sourceLatudue);	
		    	locationRangeDTO.setFromLongitude(sourceLongutude);
		    	locationRangeDTO.setToLatitude(destLatudue);
		    	locationRangeDTO.setToLongitude(destlongute);
		    	return locationRangeDTO;
		    	
		    	
		    }
			        
			
	}    
	
	


