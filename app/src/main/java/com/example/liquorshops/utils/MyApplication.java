package com.example.liquorshops.utils;

import android.app.Application;
import android.content.Context;

import tk.jamun.volley.main.VolleySingleton;


public class MyApplication extends Application {
    private static  Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //FirebaseApp.initializeApp(this);
        context = getApplicationContext();
        VolleySingleton.setInstance(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       //manife MultiDex.install(this);
    }
    public  static Context getContext(){
        return context;
    }
  }
