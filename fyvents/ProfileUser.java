package com.cedric.fyvents;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cedric.fyvents.users.User;
import com.cedric.fyvents.users.UserLocalData;

public class ProfileUser extends ActionBarActivity implements View.OnClickListener{
    TextView userText;
    Button loggout;
    User user;
    Toolbar toolbar;
    UserLocalData userLocalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_user);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userText = (TextView)findViewById(R.id.userName);
        loggout = (Button)findViewById(R.id.bLogout);
        loggout.setOnClickListener(this);
        userLocalData = new UserLocalData(this);
    }
    private void displayUserDetails() {
        user = userLocalData.getLoggedInUser();
        userText.setText("Welcome, "+user.getClass().getName());
    }

    @Override
    public void onClick(View v) {
        userLocalData.clearUserData();
        userLocalData.setUserLoggedIn(false);
        startActivity(new Intent(this, Login.class));
    }
}
