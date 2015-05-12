package com.example.sravyadara.smarthomeproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import info.hoang8f.widget.FButton;

/**
 * Created by sravyadara on 4/19/15.
 */
public class Registration extends ActionBarActivity {

      FButton userReg;
      FButton sensorReg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        


        userReg = (FButton)findViewById(R.id.user_reg_button);
        sensorReg = (FButton)findViewById(R.id.sensor_reg_button);


       userReg.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Intent j = new Intent(Registration.this,User_Registration.class);
               startActivity(j);
               //actionBar.setTitle("UserRegistration");
           }
       });


        sensorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(Registration.this,Sensor_Registration.class);
                startActivity(b);
                //actionBar.setTitle("Sensor Registration");
            }
        });
    }



}
