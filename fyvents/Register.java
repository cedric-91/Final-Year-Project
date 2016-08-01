package com.cedric.fyvents;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cedric.fyvents.users.User;


public class Register extends ActionBarActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private EditText fname, lname, email,pass, cpass;
    private Button register;
    private Spinner gender, status;
    private ProgressDialog pDialog;
    private Toolbar toolbar;
    //UserDatabaseAdapter userDatabaseAdapter;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fname = (EditText)findViewById(R.id.Rfname);
        lname = (EditText)findViewById(R.id.Rlname);
        email = (EditText)findViewById(R.id.Remail);
        pass = (EditText)findViewById(R.id.Rpass);
        cpass = (EditText)findViewById(R.id.Rpass2);
        register = (Button)findViewById(R.id.register);

        //Drop down menu for gender
        gender = (Spinner) findViewById(R.id.spinner_gender);
        //Set the arrayAdapter
        ArrayAdapter adapterG = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_list_item_1);
        //set the adapter
        gender.setAdapter(adapterG);
        gender.setOnItemSelectedListener(this);

        //Drop down menu for status
        status = (Spinner) findViewById(R.id.spinner_status);
        //Set the arrayAdapter
        ArrayAdapter adapterS = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_list_item_1);
        //set the adapter
        status.setAdapter(adapterS);
        status.setOnItemSelectedListener(this);

        //Set the onclick function.
        register.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String name = fname.getText().toString();
        String sname = lname.getText().toString();
        String mail = email.getText().toString();
        String password = pass.getText().toString();
        String rpassword = cpass.getText().toString();
        String genders = gender.getSelectedItem().toString();
        String material = status.getSelectedItem().toString();

        User registered = new User(name, sname, mail, password, genders, material);
        //Check if all the field has been entered correctly.
        if (!password.equals(rpassword)) {
            Toast.makeText(Register.this, "Password do not match!", Toast.LENGTH_LONG).show();
        }else if (sname.isEmpty()){
            lname.requestFocus();
             Toast.makeText(this, "Enter your Last Name", Toast.LENGTH_SHORT).show();
         } else if (name.isEmpty()){
             fname.requestFocus();
             Toast.makeText(this, "Enter your First Name", Toast.LENGTH_SHORT).show();
         }else if(mail.isEmpty()){
             Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
             email.requestFocus();
         }else if (gender.getSelectedItemPosition() == 0 ){
             Toast.makeText(this, "Gender is not Valid!", Toast.LENGTH_SHORT).show();

         }else if (status.getSelectedItemPosition() == 0){
             Toast.makeText(this, "Status is not Valid!", Toast.LENGTH_SHORT).show();

         } else{
             startActivity(new Intent(this, Login.class));
         }
    }

}
