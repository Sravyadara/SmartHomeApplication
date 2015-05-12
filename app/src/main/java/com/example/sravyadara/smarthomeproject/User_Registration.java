package com.example.sravyadara.smarthomeproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import android.telephony.TelephonyManager;
import android.content.Context;

import info.hoang8f.widget.FButton;

/**
 * Created by sravyadara on 4/20/15.
 */
public class User_Registration  extends ActionBarActivity {


      Button Register;
      EditText firstName;
      EditText lastName;
      EditText email;
      EditText userName;
      EditText password;
      EditText address;
      EditText phoneNumber;
      String IMEI;


       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_registration_layout);

        Parse.initialize(this, "EI9H1iRkW0VTzFKcO3UvYWgNOhMRJZuXX3K78w64", "wVJSBBkc2JHUBk7Ip6dxShRqzBp5KhUgQqmUhrfP");






         firstName = (EditText)findViewById(R.id.fname);
         lastName =   (EditText)findViewById(R.id.lname);
         email=(EditText)findViewById(R.id.email);
         userName =(EditText)findViewById(R.id.uname);
         password = (EditText)findViewById(R.id.up);
         address =(EditText)findViewById(R.id.address);
         phoneNumber =(EditText)findViewById(R.id.contactnum);
         Register  =(Button)findViewById(R.id.register);

           TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
           IMEI = telephonyManager.getDeviceId();




           /*userDAO user = new userDAO();
           user.setFirstName(firstName.getText().toString());
           user.setLastName(lastName.getText().toString());
           user.setEmailId(email.getText().toString());
           user.setUserName(userName.getText().toString());
           user.setPassword(password.getText().toString());
           user.setAddress(address.getText().toString());
           user.setPhoneNum(phoneNumber.getText().toString());*/


           Register.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {


                  /*ParseObject testObject = new ParseObject("TestObject");
                   testObject.put("foo", "bar");
                   testObject.saveInBackground();*/



                 /*  userDAO user = new userDAO();
                   user.setFirstName(firstName.getText().toString());
                   user.setLastName(lastName.getText().toString());
                   user.setEmailId(email.getText().toString());
                   user.setUserName(userName.getText().toString());
                   user.setPassword(password.getText().toString());
                   user.setAddress(address.getText().toString());
                   user.setPhoneNum(phoneNumber.getText().toString());*/

                   ParseObject testing = new ParseObject("Testing");

                   testing.put("FirstName",firstName.getText().toString());
                   testing.put("LastName",lastName.getText().toString());
                   testing.put("Email",email.getText().toString());
                   testing.put("UserName",userName.getText().toString());
                  // testing.put("Password",password.getText().toString());
                   testing.put("Address",address.getText().toString());
                   testing.put("PhoneNumber",phoneNumber.getText().toString());
                   testing.put("IMEI",IMEI);

                   testing.saveInBackground();
                   Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();



               }
           });



    }

}
