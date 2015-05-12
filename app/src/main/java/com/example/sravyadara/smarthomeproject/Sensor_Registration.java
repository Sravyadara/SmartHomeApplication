package com.example.sravyadara.smarthomeproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;


/**
 * Created by sravyadara on 4/21/15.
 */
public class Sensor_Registration extends ActionBarActivity {

    Button Register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_registration_layout);

        Register = (Button)findViewById(R.id.registerone);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();


            }
        });

    }


}
