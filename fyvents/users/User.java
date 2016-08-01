package com.cedric.fyvents.users;

/**
 * Created by Cedric on 06/07/2015.
 */
public class User{

        String name, lname, email, password, status, gender;

        public User(String name, String lname, String email, String password, String status, String gender){
            this.name = name;
            this.lname = lname;
            this.email = email;
            this.password = password;
            this.status = status;
            this.gender = gender;
        }

    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.name = "";
        this.status = "";
        this.gender = "";
    }
}
