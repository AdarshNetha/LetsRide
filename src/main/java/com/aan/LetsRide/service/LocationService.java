package com.aan.LetsRide.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    // --------------------------------------------------
    // Get city from latitude & longitude
    // --------------------------------------------------
    public String getCityFromCoordinates(double lattitude, double longitude) {

        String url = "https://us1.locationiq.com/v1/reverse.php?key=" + API_KEY
                + "&lat=" + lattitude
                + "&lon=" + longitude
                + "&format=json";

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Object> address = (Map<String, Object>) response.get("address");

        return (String) address.get("city");
    }

    // --------------------------------------------------
    // Validate city exists
    // --------------------------------------------------
    public boolean validatingCity(String city) {

        String url = "https://us1.locationiq.com/v1/search?key=" + API_KEY
                + "&q=" + city + ",%20India&format=json";

        ValidatingDestination[] validatingCity =
                restTemplate.getForObject(url, ValidatingDestination[].class);

        if (validatingCity == null || validatingCity.length == 0) {
            return false;
        }

        String apiCity = validatingCity[0].getDisplay_name()
                .substring(0, city.length());

        return city.equalsIgnoreCase(apiCity);
    }

    // --------------------------------------------------
    // Get coordinates for source & destination
    // --------------------------------------------------
    public LocationRangeDTO getFromAndToCoordinates(String source, String destination) {

        String sourceUrl = "https://us1.locationiq.com/v1/search?key=" + API_KEY
                + "&q=" + source + ",%20India&format=json";

        ValidatingDestination[] sourceData =
                restTemplate.getForObject(sourceUrl, ValidatingDestination[].class);

        String destUrl = "https://us1.locationiq.com/v1/search?key=" + API_KEY
                + "&q=" + destination + ",%20India&format=json";

        ValidatingDestination[] destData =
                restTemplate.getForObject(destUrl, ValidatingDestination[].class);

        LocationRangeDTO dto = new LocationRangeDTO();

        dto.setFromLatitude(Double.parseDouble(sourceData[0].getLat()));
        dto.setFromLongitude(Double.parseDouble(sourceData[0].getLon()));
        dto.setToLatitude(Double.parseDouble(destData[0].getLat()));
        dto.setToLongitude(Double.parseDouble(destData[0].getLon()));

        return dto;
    }

   
    // ROAD DISTANCE (CORRECT & FIXED)
   
    public double getDistanceInKM(String source, String destination) {

        try {
            // Encode city names
            source = URLEncoder.encode(source + ", India", StandardCharsets.UTF_8);
            destination = URLEncoder.encode(destination + ", India", StandardCharsets.UTF_8);

            // ---------- SOURCE ----------
            String srcUrl = String.format(
                    "https://us1.locationiq.com/v1/search?key=%s&q=%s&format=json",
                    API_KEY, source
            );

            JsonNode srcJson = objectMapper.readTree(
                    restTemplate.getForObject(srcUrl, String.class)
            );

            if (srcJson == null || srcJson.isEmpty()) {
                throw new InvaildLocationException("Source location not found");
            }

            double srcLat = srcJson.get(0).get("lat").asDouble();
            double srcLon = srcJson.get(0).get("lon").asDouble();

            // ---------- DESTINATION ----------
            String destUrl = String.format(
                    "https://us1.locationiq.com/v1/search?key=%s&q=%s&format=json",
                    API_KEY, destination
            );

            JsonNode destJson = objectMapper.readTree(
                    restTemplate.getForObject(destUrl, String.class)
            );

            if (destJson == null || destJson.isEmpty()) {
                throw new InvaildLocationException("Destination location not found");
            }

            double destLat = destJson.get(0).get("lat").asDouble();
            double destLon = destJson.get(0).get("lon").asDouble();

            // 🚨 IMPORTANT: Directions API expects (lon,lat)
            String directionUrl = String.format(
                    "https://us1.locationiq.com/v1/directions/driving/%f,%f;%f,%f?key=%s&overview=false",
                    srcLon, srcLat, destLon, destLat, API_KEY
            );

            JsonNode dirJson = objectMapper.readTree(
                    restTemplate.getForObject(directionUrl, String.class)
            );

            if (!dirJson.has("code") || !"Ok".equals(dirJson.get("code").asText())) {
                throw new RuntimeException("No route found");
            }

            double distanceMeters =
                    dirJson.get("routes").get(0).get("distance").asDouble();

            return distanceMeters / 1000; // meters → KM

        } catch (Exception e) {
            throw new RuntimeException("Error calculating road distance", e);
        }
    }
}

