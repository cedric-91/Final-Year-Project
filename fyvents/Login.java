package com.cedric.fyvents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cedric.fyvents.users.UserLocalData;
import com.cedric.fyvents.users.User;

public class Login extends ActionBarActivity implements View.OnClickListener {

    private EditText email, pass;
    private Button login;
    private TextView signUp, fpass;
    private Toolbar toolbar;
    UserLocalData userLocalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Testing the User database.
        userLocalData = new UserLocalData(this);

        email = (EditText)findViewById(R.id.eMail);
        pass  = (EditText)findViewById(R.id.ePass);
        fpass = (TextView)findViewById(R.id.forgot_pass);
        login = (Button)findViewById(R.id.login);
        signUp = (TextView)findViewById(R.id.register);
        //Setting the onClick listener.
        login.setOnClickListener(this);
        fpass.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                //Getting text from the mail, pass and converting into a string.
                final String mail = email.getText().toString();
                final String password = pass.getText().toString();
                //Checking if both the email and password has been entered.
                if(mail.isEmpty()){
                    Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                } else if(password.isEmpty()){
                    Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    pass.requestFocus();
                }else{
                    User user = new User(null, null);
                    userLocalData.storeUserData(user);
                    userLocalData.setUserLoggedIn(true);
                    startActivity(new Intent(this, ProfileUser.class));
                }
                break;

            case R.id.register:
                startActivity(new Intent(Login.this, Register.class));
                break;
            case R.id.forgot_pass:
                startActivity(new Intent(Login.this, ForgotPassword.class));
                break;
        }
    }
}
