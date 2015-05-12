package com.example.sravyadara.smarthomeproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;



public class MainActivity extends ActionBarActivity {

    ImageButton imageButton;
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageButton imageButton5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton1 =(ImageButton)findViewById(R.id.imageButton2);
        imageButton2 =(ImageButton)findViewById(R.id.imageButton3);
        imageButton3 =(ImageButton)findViewById(R.id.imageButton4);
        imageButton4 =(ImageButton)findViewById(R.id.imageButton5);
        imageButton5 =(ImageButton)findViewById(R.id.imageButton6);



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,Registration.class);

                startActivity(i);
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this,Sensor_Network.class);
                startActivity(j);
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(MainActivity.this,LocationView.class);
                startActivity(k);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(MainActivity.this,SensorControl.class);
                startActivity(l);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(MainActivity.this,DataMonitor.class);
                startActivity(m);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MainActivity.this,DataCollection.class);
                startActivity(n);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
