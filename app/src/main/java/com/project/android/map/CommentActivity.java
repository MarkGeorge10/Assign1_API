package com.project.android.map;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class CommentActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = CommentActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);






    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double longtitude,attitude;

        longtitude = Double.valueOf(getIntent().getExtras().getString("leng"));
        attitude = Double.valueOf(getIntent().getExtras().getString("wid"));

        Log.d(TAG, String.valueOf(longtitude+" , "+attitude));

        LatLng sydney = new LatLng(longtitude, attitude);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.+
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        // Position the map's camera near Sydney, Australia.

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(longtitude, attitude), 16));






    }
}
