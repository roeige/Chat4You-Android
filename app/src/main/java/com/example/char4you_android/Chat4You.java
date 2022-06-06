package com.example.char4you_android;

import android.app.Application;
import android.content.Context;

public class Chat4You extends Application{
    public static Context context;
    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
    }
}
