package com.alpha.productscanner.model;

import android.content.Context;
import android.util.Log;

import com.alpha.productscanner.api.remote.APIService;
import com.alpha.productscanner.api.remote.ApiUtils;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.alpha.productscanner.dto.request.RequestRegistro;
import com.alpha.productscanner.dto.response.ResponseRegistro;
import com.alpha.productscanner.dto.response.ResponseRegistroError;
import com.alpha.productscanner.ui.Registro.RegistroPresenter;
import com.google.gson.Gson;
import static com.alpha.productscanner.constant.AlphaConstant.PLATFORM;

public class RegistroModel {

    private APIService mAPIService;
    RegistroPresenter presenter;
    Context con;



    public RegistroModel(Context con, RegistroPresenter presenter){
        //coneccin con Modelo
        this.presenter=presenter;
        this.con=con;

        //crear instancia de retrofit con microservicios
        mAPIService =  ApiUtils.getAPIService();

    }


    public void Transfer (String name, String apellido, String phone, String email, String pwd){

        RequestRegistro registro = new RequestRegistro();
        registro.setPlatform(PLATFORM);
        registro.setName(name+apellido);
        registro.setPhone(phone);
        registro.setPwd(pwd);
        registro.setEmail(email);


        // RxJava
        mAPIService.transfer(registro).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseRegistro>() {
                    @Override
                    public void onCompleted() {
                        Log.e("OkHttp", "onCompleted: " );

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("OkHttp", "Error .: " +  e.getMessage());
                        e.printStackTrace();


                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException)e).response().errorBody();

                            try {


                                Gson json = new Gson();

                                ResponseRegistroError rerror= new ResponseRegistroError();
                                rerror = json.fromJson(responseBody.string(),ResponseRegistroError.class);

                                ResponseRegistro resd =new ResponseRegistro();

                                resd.setCode(rerror.getResponse().getCode());
                                resd.setMessage(rerror.getResponse().getMessage());

                                presenter.ResponseProcessModel(resd);


                            }catch(Exception ex){

                                Log.e("OkHttp", "Error .: " +  ex.getMessage());
                            }





                        }


                    }

                    @Override
                    public void onNext(ResponseRegistro responsed) {

                        Log.e("OkHttp","================> EXITO SERVICIO");

                        if (responsed.getCode()==0){


                        }

                        presenter.ResponseProcessModel(responsed);





                    }
                });





    }



}
