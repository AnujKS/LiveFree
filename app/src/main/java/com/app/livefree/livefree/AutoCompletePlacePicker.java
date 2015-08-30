package com.app.livefree.livefree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.livefree.livefree.constants.ApplicationConstants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;


public class AutoCompletePlacePicker extends FragmentActivity  implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;

    private AutoCompleteTextView mAutocompleteView;

    private AutoCompletePlacePickerAdapter mAdapter;

    private ImageView searchButton;

    private static final LatLngBounds BOUNDS_BANGALORE = new LatLngBounds(
            new LatLng(12.887876, 77.445519), new LatLng(13.136406, 77.748614));
    private Intent callingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 , this)
                .addApi(Places.GEO_DATA_API)
                .build();
        setContentView(R.layout.activity_auto_complete_place_picker);
        callingIntent = getIntent();
        mAutocompleteView = (AutoCompleteTextView) findViewById(R.id.autocomplete_places);

        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        mAdapter = new AutoCompletePlacePickerAdapter(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_BANGALORE, null);
        mAutocompleteView.setAdapter(mAdapter);
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
              */
            final AutoCompletePlacePickerAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(TAG, "Autocomplete item selected: " + item.description);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            Toast.makeText(getApplicationContext(), "Clicked: " + item.description,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auto_complete_place_picker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);

            // Format details of the place for display and show it in a TextView

            // Display the third party attributions if set.
            final CharSequence thirdPartyAttribution = places.getAttributions();
            if (thirdPartyAttribution == null) {
              Log.e(TAG,"null place attribution");

            } else {
                Log.e(TAG, String.valueOf(Html.fromHtml(thirdPartyAttribution.toString())));
                takeBackThePlace(place);
            }

            Log.i(TAG, "Place details received: " + place.getName());

            places.release();
        }
    };

    private void takeBackThePlace(Place place) {

    callingIntent.putExtra(ApplicationConstants.CALLING_ACTIVITY,ApplicationConstants.AUTO_COMPLETE_GEO_CODE);
    callingIntent.putExtra(ApplicationConstants.PLACE_FROM_AUTOCOMPLETE,(new Gson().toJson(place)));
    startActivity(callingIntent);
    finish();
    }



    /**
     * Called when the Activity could not connect to Google Play services and the auto manager
     * could resolve the error automatically.
     * In this case the API is not available and notify the user.
     *
     * @param connectionResult can be inspected to determine the cause of the failure
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }

    private static final String TAG = "AutoCompletePlacePicker";
}
