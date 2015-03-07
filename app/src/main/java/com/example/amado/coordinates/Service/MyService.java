package com.example.amado.coordinates.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Amado on 05/03/2015.
 */
public class MyService extends Service implements LocationListener{
    private Context mContext;
    double latitude;
    double longitude;
    Location location;
    boolean GPSActivated;
    TextView text;
    LocationManager locationManager;

    public MyService(){
        super();
        this.mContext= this.getApplicationContext();
    }

    public MyService(Context context){
        super();
        this.mContext=context;
        getLocation();
    }

    public double getLongitude() {
        return longitude;
    }

    public void setView(View v){
        text=(TextView)v;
        text.setText("Coordinates are "+latitude+", "+ longitude);
    }

    public void getLocation() {
        try {
        locationManager = (LocationManager) (this.mContext.getSystemService(LOCATION_SERVICE));
        GPSActivated = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("MainActivity", "prueba");
            if (GPSActivated) {
                Log.d("MainActivity", "primer if");
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER
                        , 1000 * 60, 10, this);
                if (locationManager != null) {
                    Log.d("MainActivity", "segundo if");
                    location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
    }catch(Exception e) {}
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

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
