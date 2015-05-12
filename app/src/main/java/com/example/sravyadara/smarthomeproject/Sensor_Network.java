package com.example.sravyadara.smarthomeproject;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


/**
 * Created by sravyadara on 4/23/15.
 */
public class Sensor_Network extends TabActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_network_layout);


        TabHost tabHost = getTabHost();
        //Sensor List Tab
        TabSpec listSpec = tabHost.newTabSpec("List");
        listSpec.setIndicator("List");
        Intent listTab = new Intent(this,SensorsListViewDisplay.class);
        listSpec.setContent(listTab);

        //Sensor Map List Tab
        TabSpec mapListSpec = tabHost.newTabSpec("MapView");
        mapListSpec.setIndicator("MapView");
        Intent mapListTab = new Intent(this,SensorsMapList.class);
        mapListSpec.setContent(mapListTab);

        // Adding all TabSpec to TabHost
        tabHost.addTab(listSpec);
        tabHost.addTab(mapListSpec);

    }
}
