package com.app.livefree.livefree.filter;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;


/**
 * Created by ANJUM on 29-Aug-15.
 */
public class MapsFilter implements Parcelable {

    private LatLng latLng;
    private String title;
    private String snippet;
    private Bitmap theBitmap;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Bitmap getTheBitmap() {
        return theBitmap;
    }

    public void setTheBitmap(Bitmap theBitmap) {
        this.theBitmap = theBitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
       parcel.writeParcelable(this.latLng,i);
        parcel.writeParcelable(this.theBitmap,i);
        parcel.writeString(this.title);
        parcel.writeString(this.snippet);
    }

    public static MapsFilter readFromParcel(Parcel in) {
        MapsFilter mapsFilter = new MapsFilter();
        LatLng latLng = in.readParcelable(LatLng.class.getClassLoader());
        mapsFilter.setLatLng(latLng);
        Bitmap bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        mapsFilter.setTheBitmap(bitmap);
        mapsFilter.setTitle(in.readString());
        mapsFilter.setSnippet(in.readString());
        return mapsFilter;
    }
}
