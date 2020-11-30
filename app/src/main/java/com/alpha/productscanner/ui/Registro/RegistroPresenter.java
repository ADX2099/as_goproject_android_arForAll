package com.alpha.productscanner.ui.Registro;

import android.content.Context;
import android.util.Log;

import com.alpha.productscanner.dto.response.ResponseLogin;
import com.alpha.productscanner.dto.response.ResponseRegistro;
import com.alpha.productscanner.model.RegistroModel;

public class RegistroPresenter {

    RegistroActivity view;
    RegistroModel model;
    Context con;


    public RegistroPresenter(Context con, RegistroActivity view){
        this.view=view;
        this.con=con;

        //coneccion a modelo
        model = new RegistroModel(con,this);

    }


    public void Transfer(String name, String apellido, String phone, String email, String pwd  ){

        //otras validaciones de negocio

        model.Transfer(name, apellido, phone, email, pwd);
    }



    public void ResponseProcessModel(ResponseRegistro response){

        Log.d("OkHttp", "=======================> ERROR CODE: " + response.getCode());
        if (response.getCode()==0){


            view.onSucces();
        }else{

            view.onFailed(response.getMessage());
        }


    }

}
