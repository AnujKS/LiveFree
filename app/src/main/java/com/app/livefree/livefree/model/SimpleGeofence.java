package com.app.livefree.livefree.model;

/**
 * Created by ANJUM on 30-Aug-15.
 */
public class SimpleGeofence {

    private Double latitude;
    private Double longitude;
    private int id;
    private int radius;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
