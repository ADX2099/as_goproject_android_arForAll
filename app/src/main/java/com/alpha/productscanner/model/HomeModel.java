package com.alpha.productscanner.model;

import android.content.Intent;

import com.alpha.productscanner.ui.ar.ArActivity;
import com.alpha.productscanner.ui.home.HomePresenter;

public class HomeModel {

    HomePresenter presenter;

    // crear instancias de manager de camara
    // llamadas a db
    // reglas de negocio
    //webservices


    public HomeModel(HomePresenter presenter){
        this.presenter=presenter;
    }

    public void CameraProcess(){

        int idBrand=1;
        presenter.callList(idBrand);


    }

}
