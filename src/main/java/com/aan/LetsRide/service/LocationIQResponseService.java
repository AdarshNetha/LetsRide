package com.aan.LetsRide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.aan.LetsRide.DTO.LocationCoordinatesdto;
import com.aan.LetsRide.entity.LocationIQResponse;
@Service
public class LocationIQResponseService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_URL =
            "https://us1.locationiq.com/v1/search?key={key}&q={location}&format=json";

    private static final String API_KEY =
            "eyJvcmciOiI1YjNjZTM1OTc4NTExMTAwMDFjZjYyNDgiLCJpZCI6IjA3ZGFjYjc2ODY1MzQzMDJhYzFjMjRkZmRjN2EyY2E2IiwiaCI6Im11cm11cjY0In0=";

    public LocationCoordinatesdto validateDestination(String destinationLocation) {

        ResponseEntity<LocationIQResponse[]> response = restTemplate.getForEntity(
                API_URL,
                LocationIQResponse[].class,
                API_KEY,
                destinationLocation
        );

        LocationIQResponse[] locations = response.getBody();

        if (locations == null || locations.length == 0) {
            throw new RuntimeException("Invalid Destination Location");
        }

        double lat = Double.parseDouble(locations[0].getLat());
        double lon = Double.parseDouble(locations[0].getLon());

        return new double[]{lat, lon};
    }
    public LocationCoordinatesdto getCoordinates(String location) {

        ResponseEntity<LocationIQResponse[]> response = restTemplate.getForEntity(
                API_URL,
                LocationIQResponse[].class,
                API_KEY,
                location
        );

        LocationIQResponse[] body = response.getBody();

        // VALIDATION
        if (body == null || body.length == 0) {
            throw new InvalidLocationException("Invalid Location: " + location);
        }

        LocationIQResponse loc = body[0];

        LocationCoordinatesdto dto = new LocationCoordinatesdto();
        dto.setLat(Double.parseDouble(loc.getLat()));
        dto.setLon(Double.parseDouble(loc.getLon()));
        dto.setDisplayName(location);

        return dto;
    }

}






