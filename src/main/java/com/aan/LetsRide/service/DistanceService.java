package com.aan.LetsRide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class DistanceService {
	

	    @Autowired
	    private RestTemplate restTemplate;

	    private static final String ORS_URL =
	            "https://api.openrouteservice.org/v2/directions/driving-car";

	    private static final String API_KEY = "eyJvcmciOiI1YjNjZTM1OTc4NTExMTAwMDFjZjYyNDgiLCJpZCI6IjA3ZGFjYjc2ODY1MzQzMDJhYzFjMjRkZmRjN2EyY2E2IiwiaCI6Im11cm11cjY0In0=";

	    public double getDrivingDistance(double startLon, double startLat,
	                                     double endLon, double endLat) {

	        try {
	            String url = ORS_URL
	                    + "?api_key=" + API_KEY
	                    + "&start=" + startLon + "," + startLat
	                    + "&end=" + endLon + "," + endLat;

	            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

	            // JSON structure: features → properties → segments → distance (meters)
	            Map routes = ((Map)((List)response.getBody().get("features")).get(0));
	            Map props = (Map) routes.get("properties");
	            List segments = (List) props.get("segments");

	            Map firstSegment = (Map) segments.get(0);

	            double distanceMeters = Double.parseDouble(firstSegment.get("distance").toString());

	            return distanceMeters / 1000.0; // convert to KM

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return 0;
	    }
	}


