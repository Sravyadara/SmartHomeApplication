package com.example.sravyadara.smarthomeproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sravyadara on 4/23/15.
 */
public class SensorsMapList extends Activity implements OnMapReadyCallback {
    private CameraUpdate cameraUpdateTotal;
    GoogleMap myMap;
    EditText searchMap;
    Button searchNetworks;
    List<SensorDAO> sensorList = new ArrayList<SensorDAO>();


    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sensors_maplist_layout);

            myMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            onMapReady(myMap);

        Sensors sensors = new Sensors();
        sensors.generateSensorList();
       // sensorList = sensors.getSensorsList();
        sensorList.addAll(sensors.getSensorsList());

        searchMap = (EditText)findViewById(R.id.zipCodeInput);
        searchNetworks =(Button)findViewById(R.id.findNetworks);






        searchNetworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zipCode = searchMap.getText().toString();
                LatLngBounds.Builder builder1 = new LatLngBounds.Builder();
                int flag =0;

               for(int i=0;i<sensorList.size();i++){
                   if(sensorList.get(i).getZipCode().equals(zipCode)){
                       flag=1;
                       String[] temp = sensorList.get(i).getCoordinates().split(",");
                       LatLng templatlng = new LatLng(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]));
                       builder1.include(templatlng);

                   }

               }
                if(flag == 0){
                    Toast.makeText(getApplicationContext(), "Sorry, no networks found around " + zipCode, Toast.LENGTH_LONG).show();
                      myMap.moveCamera(cameraUpdateTotal);

                }else {
                    LatLngBounds bounds1 = builder1.build();
                     CameraUpdate cameraUpdate1 = CameraUpdateFactory.newLatLngBounds(bounds1, 90);
                     myMap.moveCamera(cameraUpdate1);

                }

            }
        });


        }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latlng1 = new LatLng(37.351787, -121.991797);
        myMap.addMarker(new MarkerOptions()
                        .position(latlng1)
                        .title("Flora Vista Ave, SantaClara")


        );
        myMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent i = new Intent(SensorsMapList.this,DrivingDirections.class);
                i.putExtra("destLatLng","37.351787,-121.991797");
                i.putExtra("title","Flora Vista");
                startActivity(i);
                return false;
            }
        });
        LatLng latlng2 = new LatLng(37.380054, -122.056096);
        myMap.addMarker(new MarkerOptions()
                .position(latlng2)
                .title("ShawdowBrook Apts, Sunnyvale"));

        LatLng latlng3 = new LatLng(
                37.348914, -121.994894);

        myMap.addMarker(new MarkerOptions()
                .position(latlng3)
                .title("Marina Playa Apts , Santa Clara"));

        LatLng latlng4 = new LatLng(
                37.398613, -121.944499
        );
        myMap.addMarker(new MarkerOptions()
                .position(latlng4)
                .title("MansionGrove Apts, Santa Clara"));

        LatLng latlng5 = new LatLng(
                37.396431, -121.944799);
        myMap.addMarker(new MarkerOptions()
                .position(latlng5)
                .title("MorelandGrove Apts, Sunnyvale"));
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng1, 11));
    }






}
