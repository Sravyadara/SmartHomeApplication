package com.example.sravyadara.smarthomeproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

/**
 * Created by sravyadara on 4/23/15.
 */
public class SensorLocation extends ActionBarActivity {
   GoogleMap googleMapLocation;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_layout);
        MapFragment mapfragment = (MapFragment)getFragmentManager().findFragmentById(R.id.maplocation);
        googleMapLocation = mapfragment.getMap();
        googleMapLocation.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMapLocation.setMyLocationEnabled(true);

        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
        final ArrayList<LatLng> latLngsList = new ArrayList<LatLng>();

      //  latLngsList.add(new LatLng(37.374969,-121.992254));
        latLngsList.add(new LatLng(37.374920,-121.992053));
        latLngsList.add(new LatLng(37.374777, -121.992300));
        latLngsList.add(new LatLng(37.374725, -121.992072));
      //  latLngsList.add(new LatLng(37.374745, -121.992127));
        latLngsList.add(new LatLng(37.374737, -121.991956));
        latLngsList.add(new LatLng(37.374579, -121.992265));
       // latLngsList.add(new LatLng(37.374633, -121.992045));


        for(LatLng point : latLngsList){
            googleMapLocation.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
        }

         /* Getting latlngs to where the image has to be placed. */

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.one);
        LatLng southWest = new LatLng(37.374527,-121.992440);
        LatLng northEast = new LatLng(37.375013,-121.991886);
        LatLngBounds latLngBounds = new LatLngBounds(southWest, northEast);

        /* Preparing the ground overlay */

        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
        groundOverlayOptions.positionFromBounds(latLngBounds);
        groundOverlayOptions.image(bitmapDescriptor);
        groundOverlayOptions.transparency(0.5f);

        googleMapLocation.addGroundOverlay(groundOverlayOptions);

        googleMapLocation.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                for(LatLng marker : latLngsList) {
                    builder.include(marker);
                }
                LatLngBounds bounds = builder.build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 50);
                googleMapLocation.animateCamera(cameraUpdate);
            }
        });





    }

}
