package com.alpha.productscanner.model;

import android.os.AsyncTask;
import android.util.Log;

import com.alpha.productscanner.api.remote.APIService;
import com.alpha.productscanner.api.remote.ApiUtils;
import com.alpha.productscanner.api.remote.CheckOutService;
import com.alpha.productscanner.api.remote.TokenService;
import com.alpha.productscanner.dto.response.ResponseProducts;
import com.alpha.productscanner.ui.paypal.PayPalPresenter;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PayPalModel implements PayPalModelInterface {


    @Override
    public void sendToken(String token) {

        payPalPresenter.onSussessToken(token);

    }

    @Override
    public void onFailedToken(String msg) {
        payPalPresenter.onFailedToken(msg);
    }

    @Override
    public void checkoutSussess(String token) {

        payPalPresenter.checkoutSussess(token);
    }

    @Override
    public void onFailedCheckOut(String msg) {
        payPalPresenter.onFailedCheckOut("Excepción; No se pudo hacer la compra. Intente mas tarde." );

    }


    PayPalPresenter payPalPresenter;
    private APIService mAPIService;

    public PayPalModel (PayPalPresenter payPalPresenter){
        this.payPalPresenter=payPalPresenter;
        mAPIService =  ApiUtils.getAPIServicePayPal();

    }



    public void sendCheckOut(String nouce, String amount, String type, String idProduct, String user){
        CheckOutService t = new CheckOutService(this,nouce, amount, type, idProduct, user);
        t.execute();

    }
    public void getToken(){


        TokenService t = new TokenService(this);
        t.execute();




  /*      Call<String> stringCall = mAPIService.callToken();


        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d("OkHttp", "================================> RESPONSE: " );

                Log.e("OkHttp", "RESUL .: "+response.body() );

                payPalPresenter.onSussessToken(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable e) {

                Log.e("OkHttp", "Error .: " +  e.getMessage());
                e.printStackTrace();
                payPalPresenter.onFailedToken();



            }
        });

*/

        // RxJava
       /* mAPIService.callToken().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                        Log.e("OkHttp", "onCompleted: " );

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("OkHttp", "Error .: " +  e.getMessage());
                        e.printStackTrace();
                        payPalPresenter.onFailedToken();
                    }

                    @Override
                    public void onNext(String response) {


                        Log.e("OkHttp", "RESUL .: "+response );

                        payPalPresenter.onSussessToken(response);


                    }
                });
        */
    }





}
