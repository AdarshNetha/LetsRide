package com.aan.LetsRide.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.DTO.api.LocationRangeDTO;
import com.aan.LetsRide.DTO.api.ValidatingDestination;
import com.aan.LetsRide.exception.InvaildLocationException;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class LocationService {
	  @Autowired
	    private ObjectMapper objectMapper;

	
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

		    public double getDistanceInKM(String source, String destination) {
		        try {
		            // Get source coordinates
		            String srcUrl = String.format(
		                    "https://us1.locationiq.com/v1/search?key=%s&q=%s&format=json",
		                    API_KEY, source
		            );
		            JsonNode srcJson = objectMapper.readTree(restTemplate.getForObject(srcUrl, String.class));

		            if (srcJson == null || srcJson.isEmpty()) {
		                throw new InvaildLocationException("Source location not found: " + source);
		            }

		            double srcLat = srcJson.get(0).get("lat").asDouble();
		            double srcLon = srcJson.get(0).get("lon").asDouble();

		            // Get destination coordinates
		            String destUrl = String.format(
		                    "https://us1.locationiq.com/v1/search?key=%s&q=%s&format=json",
		                    API_KEY, destination
		            );
		            JsonNode destJson = objectMapper.readTree(restTemplate.getForObject(destUrl, String.class));

		            if (destJson == null || destJson.isEmpty()) {
		                throw new InvaildLocationException("Destination location not found: " + destination);
		            }

		            double destLat = destJson.get(0).get("lat").asDouble();
		            double destLon = destJson.get(0).get("lon").asDouble();

		            // Directions API (lat,lon order)
		            String directionUrl = String.format(
		                    "https://us1.locationiq.com/v1/directions/driving/%f,%f;%f,%f?key=%s",
		                    srcLat, srcLon, destLat, destLon, API_KEY
		            );

		            JsonNode dirJson = objectMapper.readTree(restTemplate.getForObject(directionUrl, String.class));

		            if (dirJson == null || !dirJson.has("routes") || dirJson.get("routes").isEmpty()) {
		                throw new RuntimeException("No route found between " + source + " and " + destination);
		            }

		            double distanceInMeters = dirJson.get("routes").get(0).get("distance").asDouble();

		            return distanceInMeters / 1000; // convert meters → km

		        } catch (InvaildLocationException e) {
		            throw e; // propagate invalid location exception
		        } catch (Exception e) {
		            e.printStackTrace(); // log full exception
		            throw new RuntimeException("Error fetching distance from LocationIQ: " + e.getMessage(), e);
		        }
		    }


			        
			
	}    
	
	


