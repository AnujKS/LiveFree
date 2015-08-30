package com.app.livefree.livefree;



import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class LocationService extends Service {


    public LocationManager locationManager;
    public MyLocationListener mlocationListner;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mlocationListner=new MyLocationListener();
        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0,  mlocationListner);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000,0, mlocationListner);

    }




    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            Log.d("Location", "Location changed has been recorded Anuj");
            Log.d("Location",location.toString());
           Toast.makeText(getApplicationContext(),"Location changed",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}