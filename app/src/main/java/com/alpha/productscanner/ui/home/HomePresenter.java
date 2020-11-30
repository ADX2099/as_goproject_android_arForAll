package com.alpha.productscanner.ui.home;

import android.content.Intent;

import com.alpha.productscanner.model.HomeModel;
import com.alpha.productscanner.ui.ar.ArActivity;

public class HomePresenter implements HomeInterface.Presenter {

    HomeFragment homeFragment;

    HomeModel model;


    public HomePresenter(HomeFragment homeFragment){
        this.homeFragment=homeFragment;
        model = new HomeModel(this);

    }


    public void camAR(){

        model.CameraProcess();

    }


    public void callList(int idBrand){

        homeFragment.callList(idBrand);
    }


}
