package com.example.sravyadara.smarthomeproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by sravyadara on 4/24/15.
 */
public class SensorControl extends ActionBarActivity {

    String[] list ={"Flora Vista","ShadowBrook","MarinaPlayo","MansionGrove","SharktoothPeak"};
    ListView listViewSensors;
    EditText searchText;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_list_layout);
        listViewSensors = (ListView)findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.listitem_layout,R.id.sensorcontroltext,list);
        listViewSensors.setAdapter(arrayAdapter);

        searchText = (EditText)findViewById(R.id.inputSearch);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //filter the list based on input
                SensorControl.this.arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}
