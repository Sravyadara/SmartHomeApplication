package com.example.sravyadara.smarthomeproject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sravyadara on 4/28/15.
 */
public class Sensors {

    private List<SensorDAO> sensorsList;

    public List<SensorDAO> getSensorsList() {
        return sensorsList;
    }

    public void setSensorsList(List<SensorDAO> sensorsList) {
        this.sensorsList = sensorsList;
    }

    public void generateSensorList(){
        sensorsList = new ArrayList<SensorDAO>();

        String[] networks = {"Flora Vista", "ShadowBrook", "MarinaPlayo", "MansionGrove", "MorelandGrove"};
        String[] latLng = {"37.351787, -121.991797", "37.380054, -122.056096", "37.348914, -121.994894", "37.398613, -121.944499", "37.396431, -121.944799"};
        String[] zipCodes = {"95051", "94086", "95051", "95054", "95054"};
        String[] address = {"SantaClara,CA", "Sunnyvale,CA", "SantaClara,CA", "SantaClara,CA", "SantaClara,CA"};

        for(int i=0; i< networks.length; i++){
            SensorDAO sensor = new SensorDAO();
            sensor.setNetworkName(networks[i]);
            sensor.setAddress(address[i]);
            sensor.setZipCode(zipCodes[i]);
         //   String cords = latLng[i];
            sensor.setCoordinates(latLng[i]);
            sensorsList.add(sensor);
        }
    }

    public JSONArray getJsonArray(){
        generateSensorList();
        JSONArray jsonArray = new JSONArray(sensorsList);
        return jsonArray;
    }

    /*public ArrayList<JSONObject> getSensorObject() {

        ArrayList<JSONObject> sensorNetwork = new ArrayList<JSONObject>();
        String[] Networks = {"Flora Vista", "ShadowBrook", "MarinaPlayo", "MansionGrove", "MorelandGrove"};
        String[] LatLng = {"37.351787, -121.991797", "37.380054, -122.056096", "37.348914, -121.994894", "37.398613, -121.944499", "37.396431, -121.944799"};
        String[] ZipCodes = {"95051", "94086", "95051", "95054", "95054"};
        String[] Address = {"SantaClara,CA", "Sunnyvale,CA", "SantaClara,CA", "SantaClara,CA", "SantaClara,CA"};

        try {


        for (int i = 1; i <= Networks.length; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Name", Networks[i]);
            jsonObject.put("LatLng", LatLng[i]);
            jsonObject.put("Address", Address[i]);
            jsonObject.put("ZipCode", ZipCodes[i]);

            sensorNetwork.add(jsonObject);

        }

    }catch (Exception e){
            e.printStackTrace();
        }


        return sensorNetwork;
    }*/
}
