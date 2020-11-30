package com.alpha.productscanner.ui.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alpha.productscanner.dto.response.ResponseLogin;
import com.alpha.productscanner.model.LoginModel;

public class LoginPresenter {

    LoginActivity view;

    LoginModel model;

    Context con;





    public LoginPresenter(Context con, LoginActivity view){
        this.view=view;
        this.con=con;

        //coneccion a modelo
        model = new LoginModel(con, this);

    }



    //pasa los datos del presenatdor a modelo

    public void Access(String user, String pwd, String token){

        //otras validaciones de negocio

       model.Access(user,pwd, token);
    }


    public void ResponseProcessModel(ResponseLogin response){

        Log.d("OkHttp", "=======================> ERROR CODE: " + response.getCode());
        if (response.getCode()==0){


            view.onSucces();
        }else{

            view.onFailed(response.getMessage());
        }


    }



    }
