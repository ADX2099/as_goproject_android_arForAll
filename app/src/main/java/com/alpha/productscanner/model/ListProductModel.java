package com.alpha.productscanner.model;

import android.util.Log;

import com.alpha.productscanner.api.remote.APIService;
import com.alpha.productscanner.api.remote.ApiUtils;
import com.alpha.productscanner.api.remote.ClientApi;
import com.alpha.productscanner.dto.request.RequestGetProducts;
import com.alpha.productscanner.dto.response.ResponseProducts;
import com.alpha.productscanner.ui.list_products.ListProductPresenter;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ListProductModel {

    ListProductPresenter presenter;
    private APIService mAPIService;

    // crear instancias de manager de camara
    // llamadas a db
    // reglas de negocio
    //webservices


    public ListProductModel(ListProductPresenter presenter){

        this.presenter=presenter;
        mAPIService =  ApiUtils.getAPIService();
    }



    public void GetProductsByBrand(int idBrand){


   //     RequestGetProducts data = new RequestGetProducts();
     //   data.setIdBrand(idBrand);



        // RxJava
        mAPIService.callProducts("cad_seguridad",idBrand).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseProducts>() {
                    @Override
                    public void onCompleted() {

                        Log.e("OkHttp", "onCompleted: " );

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("OkHttp", "Error .: " +  e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseProducts response) {

                        Log.e("OkHttp", "RESUL .: "+response.getProducts().size() );

                        presenter.ShowProducts(response);

                        //showResponse(response.getResponse().getCode() + "  -  "+ response.getResponse().getMessage());
                    }
                });










    }
}
