package com.example.tellmeaboutit;


import android.app.Application;
//import android.content.Intent;

import com.parse.Parse;

public class MyApplication extends Application {
	
	@Override
    
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "appID", "client-Key");
      
        
        
        
    }
}
