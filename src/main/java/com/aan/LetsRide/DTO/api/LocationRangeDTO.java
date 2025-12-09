package com.aan.LetsRide.DTO.api;

public class LocationRangeDTO {

    private double fromLatitude;
    private double fromLongitude;
    private double toLatitude;
    private double toLongitude;

    public double getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(double fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public double getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(double fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public double getToLatitude() {
        return toLatitude;
    }

    public void setToLatitude(double toLatitude) {
        this.toLatitude = toLatitude;
    }

    public double getToLongitude() {
        return toLongitude;
    }

    public void setToLongitude(double toLongitude) {
        this.toLongitude = toLongitude;
    }

    public LocationRangeDTO(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
        super();
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
    }

    public LocationRangeDTO() {
        super();
    }

    @Override
    public String toString() {
        return "LocationRangeDTO [fromLatitude=" + fromLatitude + ", fromLongitude=" + fromLongitude
                + ", toLatitude=" + toLatitude + ", toLongitude=" + toLongitude + "]";
    }
}
