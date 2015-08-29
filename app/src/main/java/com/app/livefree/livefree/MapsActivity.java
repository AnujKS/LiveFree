package com.app.livefree.livefree;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.app.livefree.livefree.constants.ApplicationConstants;
import com.app.livefree.livefree.exception.LiveFreeErrors;
import com.app.livefree.livefree.exception.LiveFreeException;
import com.app.livefree.livefree.filter.MapsFilter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Location mCurrentLocation;
    private LatLng mLastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Intent callingIntent = getIntent();
        int callingActivity = callingIntent.getIntExtra(ApplicationConstants.CALLING_ACTIVITY,0);
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }

        buildGoogleApiClient();
        setUpMapIfNeeded();
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    /********************************************Begin: Maps************************************/
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(mLastKnownLocation).title("Your Last Location"));
    }

    private void showMarker(MapsFilter mapsFilter) throws LiveFreeException {

        if(mapsFilter == null) {
            throw new LiveFreeException(LiveFreeErrors.MAP_FILTER_NULL);
        }

        mMap.addMarker(new MarkerOptions().position(mapsFilter.getLatLng())
                .snippet(mapsFilter.getSnippet())
                .title(mapsFilter.getTitle())
                .icon(BitmapDescriptorFactory.fromBitmap(getBitmapForMarker(mapsFilter.getTheBitmap())))
                .draggable(false)
                .anchor(0.5f, 1));
        CameraUpdate center =
                CameraUpdateFactory.newLatLng(mapsFilter.getLatLng());
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

    private Bitmap getBitmapForMarker(Bitmap theInjectedBitmap) {
        Bitmap.Config conf  = Bitmap.Config.ARGB_8888;
        Bitmap theBitmap = null;
        try {
            theBitmap = Bitmap.createBitmap(80, 80, conf);
            Canvas canvas = new Canvas(theBitmap);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(Bitmap.createScaledBitmap(theInjectedBitmap, 80, 80, false), 0, 0, null);
        } catch (Exception e) {
            Log.e(TAG,"Exception while creating bitmap for marker",e);
        }
        return theBitmap;
    }

    /**********************************************End: Maps ************************************/

    /********************************************Begin: Alert dialogs************************************/

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

    /********************************************End: Alert dialogs************************************/

    /**************************Begin: Google API Client configuration and listeners******************/
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
    }

    @Override
    public void onConnectionSuspended(int i) {
        mLastKnownLocation = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    /****************************End: Google API Client configuration and listeners******************/


    /************************************Begin: Constants************************************/
    private static final String TAG = "MapsActivity";
    public static final Locale ENGLISH = Locale.ENGLISH;
    /************************************End: Constants************************************/
}
