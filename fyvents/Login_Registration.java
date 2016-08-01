package com.cedric.fyvents;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Login_Registration extends Activity implements View.OnClickListener{

    private Button lPage, rPage;
    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login__registration);

        lPage = (Button)findViewById(R.id.loggingPage);
        rPage = (Button)findViewById(R.id.RegistrationPage);
        skip = (TextView)findViewById(R.id.skip);

        lPage.setOnClickListener(this);
        rPage.setOnClickListener(this);
        skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.RegistrationPage:
               v.setPressed(true);
                startActivity(new Intent(this, Register.class));
            break;

            case R.id.loggingPage:
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.skip:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("CEDRIC", "Welcome to Landscape mode");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("CEDRIC", "Welcome to Portrait mode");
        }

    }
}
