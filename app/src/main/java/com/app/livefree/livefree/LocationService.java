package com.app.livefree.livefree;


import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.location.LocationListener;

public class LocationService extends Service implements LocationListener {


    LocationManager locationManager;
    @Override
    public void onLocationChanged(Location location) {
        Log.d("Location", "Location changed has been recorded Anuj");
        Log.d("Location",location.toString());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0, (android.location.LocationListener) this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000,0, (android.location.LocationListener) this);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}