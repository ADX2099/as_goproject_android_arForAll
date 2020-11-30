package com.alpha.productscanner;

import android.app.Application;


import androidx.appcompat.app.AppCompatActivity;


import com.alpha.productscanner.ui.main.MainActivity;


public class Singleton extends Application {
    private static AppCompatActivity currentActivity;
    private static MainActivity mainActivity;
    public static void setCurrenActivity(AppCompatActivity arg){
        currentActivity = arg;
    }
    public static AppCompatActivity getCurrentActivity(){
        return currentActivity;
    }
    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        Singleton.mainActivity = mainActivity;
    }
}
