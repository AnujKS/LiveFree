package com.app.livefree.livefree;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.livefree.livefree.model.SimpleGeofence;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.api.ResultCallback;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeofencingMapActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status>{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofencing_map);
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
        buildGoogleApiClient();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                cameraUpdate(0.0,0.0);
            }
        }
    }


    private void cameraUpdate(Double lat,Double lon) {
        CameraPosition INIT =
                new CameraPosition.Builder()
                        .target(new LatLng(lat, lon))
                        .zoom( 17.5F )
                        .bearing( 300F) // orientation
                        .tilt( 50F) // viewing angle
                        .build();
        // use GooggleMap mMap to move camera into position
        mMap.animateCamera( CameraUpdateFactory.newCameraPosition(INIT) );
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if(mCurrentLocation != null) {
            cameraUpdate(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
            SimpleGeofence s = new SimpleGeofence();
            s.setId(1);
            s.setLatitude(mCurrentLocation.getLatitude()-2.0);
            s.setLongitude(mCurrentLocation.getLongitude()-4.0);
            s.setRadius(5000);
            addMarkerForFence(s);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onResult(Status status) {

    }

    public void addMarkerForFence(SimpleGeofence fence){
        if(fence == null){
            // display en error message and return
            return;
        }
        mMap.addMarker( new MarkerOptions()
                .position( new LatLng(fence.getLatitude(), fence.getLongitude()) )
                .title("Fence " + fence.getId())
                .snippet("Radius: " + fence.getRadius()) ).showInfoWindow();

//Instantiates a new CircleOptions object +  center/radius
        CircleOptions circleOptions = new CircleOptions()
                .center( new LatLng(fence.getLatitude(), fence.getLongitude()) )
                .radius( fence.getRadius() )
                .fillColor(0x40ff0000)
                .strokeColor(Color.TRANSPARENT)
                .strokeWidth(2);

// Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);
// more operations on the circle...

    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused")
                                        final DialogInterface dialog, @SuppressWarnings("unused")
                                        final int id) {
                        startActivity(new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused")
                    final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private static final String TAG = "GeofencingMapActivity";

}
