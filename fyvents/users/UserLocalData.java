package com.cedric.fyvents.users;

import android.content.Context;
import android.content.SharedPreferences;

import com.cedric.fyvents.users.User;

/**
 * Created by Cedric on 06/07/2015.
 */
public class UserLocalData {

    public static final String SP_NAME  = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalData(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }
    //Store the user data.
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("lname", user.lname);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.putString("status", user.status);
        spEditor.putString("gender", user.gender);
        spEditor.commit();
    }
    //Get the user Logging.
    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name", "");
        String lname = userLocalDatabase.getString("lname", "");
        String email = userLocalDatabase.getString("email", "");
        String password = userLocalDatabase.getString("password", "");
        String status = userLocalDatabase.getString("status", "");
        String gender = userLocalDatabase.getString("gender", "");

        User storedUser = new User(name, lname, email, password, status, gender);
        return storedUser;
    }
    //Check if the user has logged in.
    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }
    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn", false)== false){
            //If the user is logged in return true. if not. return false.
            return true;
        }else{
            return false;
        }
    }
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
