package com.alpha.productscanner.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alpha.productscanner.ui.login.LoginActivity;
import com.alpha.productscanner.ui.splash.SplashActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.alpha.productscanner.R;
import com.alpha.productscanner.Singleton;
import com.alpha.productscanner.base.ActivityBase;
import com.alpha.productscanner.constant.AlphaConstant;
import com.alpha.productscanner.manager.navigation.NavigatorManager;

import butterknife.Unbinder;

public class MainActivity extends ActivityBase implements NavigatorManager.NavigationListener {

    public Unbinder unbinder;
    private  boolean navDecide;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = this.getIntent().getBundleExtra("arListProduct");
        if(b != null){
            Log.d("Main-XX", "Hola: ");

            navigatorManager.initNavigation(getApplicationContext(), getSupportFragmentManager(), null, getSupportActionBar(), bottomNavigationView, MainActivity.this);
            navigatorManager.navigation(NavigatorManager.F_LIST_PRODUCT, b);
        }else {

            navigatorManager.initNavigation(getApplicationContext(), getSupportFragmentManager(), null, getSupportActionBar(), bottomNavigationView, MainActivity.this);
            navigatorManager.navigation(NavigatorManager.F_HOME, null);
        }





    }


    @Override
    protected void onResume() {
        super.onResume();
     //   navigatorManager.initNavigation(getApplicationContext(), getSupportFragmentManager(), null, getSupportActionBar(), bottomNavigationView, MainActivity.this);

    }



    @Override
    public void onBackstackChange() {

    }


    public void closeSesion(){
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
