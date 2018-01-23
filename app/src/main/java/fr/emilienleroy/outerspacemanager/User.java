package fr.emilienleroy.outerspacemanager;

import android.util.Log;

/**
 * Created by eleroy on 16/01/2018.
 */

class User {
    private String username;
    private String password;
    private  String email;

    public  User(String e, String n ,String p){
        this.email = e;
        this.username = n;
        this.password = p;
    }

    public void log(){
        Log.e("name",this.username);
        Log.e("pass",this.password);
        Log.e("mail",this.email);
    }
}
