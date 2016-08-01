package com.cedric.fyvents;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.cedric.fyvents.users.User;
import com.cedric.fyvents.users.UserLocalData;


public class Splash_Screen extends ActionBarActivity {

    Dialog dialog;
    EditText email;
    EditText pass;
    Button logging, cancel;
    UserLocalData userLocalData;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash__screen);

        userLocalData = new UserLocalData(this);

        int myTimer = 5000; //Set the time length before starting the MainActivity.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Start a new activity class "MainActivity java class".
                Intent i = new Intent(Splash_Screen.this, Login_Registration.class);
                startActivity(i);
                finish();
            }
        }, myTimer);
    }

    @Override
    protected void onStart() {
        super.onStart();
       if (authenticate() == true){
            //displayUserDetails();
        }
    }

    private boolean authenticate(){
        return userLocalData.getUserLoggedIn();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("Cedric", "Orientation changed");
    }

}
