package com.app.livefree.livefree.model;

/**
 * Created by anujkumars on 8/29/2015.
 */
public class InterestLocation {
    int id;
    public String Latitude;
    public String Longitude;
    public String Area;
    public String AreaFriendlyName;

    public InterestLocation(String latitude, String longitude, String area, String areaFriendlyName) {
        Latitude = latitude;
        Longitude = longitude;
        Area = area;
        AreaFriendlyName = areaFriendlyName;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAreaFriendlyName() {
        return AreaFriendlyName;
    }

    public void setAreaFriendlyName(String areaFriendlyName) {
        AreaFriendlyName = areaFriendlyName;
    }
}
