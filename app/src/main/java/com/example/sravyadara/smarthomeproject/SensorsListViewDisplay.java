package com.example.sravyadara.smarthomeproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sravyadara on 4/23/15.
 */
/*public class SensorsList extends Activity {

    ListView listViewSensors;
    EditText searchText;
    SensorListAdapter adapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_list_layout);


        listViewSensors = (ListView) findViewById(R.id.listView);
        adapter = new SensorListAdapter();
        listViewSensors.setAdapter(adapter);

         /*  arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item_layout,R.id.listItem,list);
            listViewSensors.setAdapter(arrayAdapter);

            searchText = (EditText)findViewById(R.id.inputSearch);

            searchText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //filter the list based on input
                    SensorsList.this.arrayAdapter.getFilter().filter(s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            listViewSensors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent listItemNavigation = new Intent(SensorsList.this,SensorLocation.class);
                    startActivity(listItemNavigation);
                }
            });*/

/*
    }

}*/

public class SensorsListViewDisplay extends Activity {
    ListView listViewSensors;
    SensorListAdapter adapter;
    public SensorsListViewDisplay sensorListDisplay = null;
    public List<SensorDAO> sensorList = new ArrayList<SensorDAO>();
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_list_layout);

        sensorListDisplay = this;
        listViewSensors = (ListView) findViewById(R.id.listView);

        Sensors sensors = new Sensors();
        // generate the sensor list details from hard coded values
        sensors.generateSensorList();
        Resources res =getResources();

        sensorList = sensors.getSensorsList();

        adapter=new SensorListAdapter(sensorListDisplay, sensorList, res );
        listViewSensors.setAdapter(adapter);

        listViewSensors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SensorsListViewDisplay.this,SensorLocation.class);
                startActivity(i);


            }
        });

        searchText = (EditText)findViewById(R.id.inputSearch);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //filter the list based on input
                adapter.filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}