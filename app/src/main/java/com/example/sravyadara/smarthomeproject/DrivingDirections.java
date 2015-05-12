package com.example.sravyadara.smarthomeproject;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DrivingDirections extends ActionBarActivity {
    private MapView directionsMapView;
    private String API_KEY = "AIzaSyCtB0nI5T2x04QOTROuAS44bPAeX14664c";
    private String currentPositionLatLngString;
    private String destinationLatLngString;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driving_directions_layout);

         ActionBar actionBar = getActionBar();
         //actionBar.setTitle("Directions to Flora Vista");

        currentPositionLatLngString = "37.361006,-121.962144";
        destinationLatLngString = getIntent().getExtras().getString("destLatLng");
        String[] values = destinationLatLngString.split(",");
        //Toast.makeText(getApplicationContext(), destinationLatLngString, Toast.LENGTH_LONG).show();

        directionsMapView = (MapView) findViewById(R.id.directionsMap);
        directionsMapView.onCreate(savedInstanceState);

        directionsMapView.onResume();
        // Initialize map
        try {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        gMap = directionsMapView.getMap();
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.setMyLocationEnabled(true);


        final LatLng currentPositionLatLng = new LatLng(37.361006, -121.962144);
        final LatLng destinationLatLng = new LatLng(Double.parseDouble(values[0]),Double.parseDouble(values[1]));

        GetDirections getDirections = new GetDirections();
        getDirections.execute();

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(currentPositionLatLng);
        builder.include(destinationLatLng);
        LatLngBounds bounds = builder.build();
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 100);
        gMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.flag);
                gMap.addMarker(new MarkerOptions().position(currentPositionLatLng).title("Current Location"));
                gMap.addMarker(new MarkerOptions().position(destinationLatLng).title(getIntent().getExtras().getString("title")).icon(bitmapDescriptor));
                gMap.animateCamera(cameraUpdate);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_directions, menu);
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

    public class GetDirections extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... String) {
            String data=null;
            try {
                String url = "https://maps.googleapis.com/maps/api/directions/json" + "?key=" + API_KEY + "&origin=" + currentPositionLatLngString + "&destination=" + destinationLatLngString;

                URL googleDirections = new URL(url);
                URLConnection urlConnection = googleDirections.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;
                StringBuffer sb = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            DirectionsJSONParser directionsJSONParser = new DirectionsJSONParser();
            directionsJSONParser.execute(result);

        }
    }

    public class DirectionsJSONParser extends AsyncTask<String,Void,List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... params) {
            List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String, String>>>();
            JSONArray legsArray, stepsArray, routesArray;

            try {

                JSONObject jsonObject = new JSONObject(params[0]);
                routesArray = jsonObject.getJSONArray("routes");

                for(int i =0; i< routesArray.length(); i++) {
                    legsArray = ((JSONObject) routesArray.get(i)).getJSONArray("legs");
                    List<HashMap<String,String>> path = new ArrayList<HashMap<String, String>>();

                    for(int j=0; j<legsArray.length(); j++) {
                        stepsArray = ((JSONObject) legsArray.get(j)).getJSONArray("steps");

                        for(int k=0; k<stepsArray.length(); k++) {
                            String polyline = "";
                            polyline = (String) ((JSONObject) ((JSONObject) stepsArray.get(k)).get("polyline")).get("points");
                            List<LatLng> pointsList = decodePoly(polyline);

                            for(int l=0; l<pointsList.size(); l++) {
                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                hashMap.put("lat", Double.toString(((LatLng) pointsList.get(l)).latitude));
                                hashMap.put("lng", Double.toString(((LatLng) pointsList.get(l)).longitude));
                                path.add(hashMap);
                            }
                        }

                        routes.add(path);

                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(10);
                polyLineOptions.color(Color.BLUE);
            }

            gMap.addPolyline(polyLineOptions);
        }

        private List<LatLng> decodePoly(String encoded) {

            List<LatLng> poly = new ArrayList<LatLng>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),
                        (((double) lng / 1E5)));
                poly.add(p);
            }
            return poly;
        }

    }
}
