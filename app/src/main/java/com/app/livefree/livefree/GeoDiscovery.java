package com.app.livefree.livefree;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.app.livefree.livefree.exception.LiveFreeErrors;
import com.app.livefree.livefree.exception.LiveFreeException;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ANJUM on 29-Aug-15.
 */
public class GeoDiscovery {

    private Geocoder geocoder;
    private static final int MAX_RESULTS = 5;

    public GeoDiscovery (Context context, Locale locale) {
        geocoder = new Geocoder(context,locale);
    }

    public List<Address> getFromLocation (LatLng latLng, int maxResults) throws LiveFreeException {
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,maxResults);
        } catch (IOException e) {
            throw new LiveFreeException(LiveFreeErrors.NO_INTERNET);
        }
        return addresses;
    }

    /**
     * Get location by location name. Return max 5 results. Constant MAX_RESULTS
     * @param locationName
     * @return
     * @throws LiveFreeException
     */
    public List<Address> getFromLocationName (String locationName) throws LiveFreeException {
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(locationName,MAX_RESULTS);
        } catch(IOException e) {
            throw new LiveFreeException(LiveFreeErrors.NO_INTERNET);
        }
        return addresses;
    }
}
