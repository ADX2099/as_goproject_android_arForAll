package com.alpha.productscanner.model;

import android.util.Log;

import com.alpha.productscanner.api.remote.APIService;
import com.alpha.productscanner.api.remote.ApiUtils;
import com.alpha.productscanner.dto.Product;
import com.alpha.productscanner.dto.request.RequestGetProduct;
import com.alpha.productscanner.dto.request.RequestPurchase;
import com.alpha.productscanner.dto.response.ResponseProduct;
import com.alpha.productscanner.dto.response.ResponseProducts;
import com.alpha.productscanner.dto.response.ResponsePurchase;
import com.alpha.productscanner.ui.detail_product.DetailProductPresenter;
import com.alpha.productscanner.ui.list_products.ListProductPresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailProductModel {

    DetailProductPresenter presenter;
    private APIService mAPIService;

    public DetailProductModel(DetailProductPresenter presenter){

        this.presenter=presenter;
        mAPIService =  ApiUtils.getAPIService();
    }


    public void getProduct(int idProduct){

        RequestGetProduct p = new RequestGetProduct();
        p.setProductid(idProduct);


        // RxJava
        mAPIService.callProduct("cad_seguridad",p).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseProduct>() {
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
                    public void onNext(ResponseProduct response) {

                        Log.e("OkHttp", "RESUL .: "+response.getProducts().size() );

                        presenter.showDetail(response);

                        //showResponse(response.getResponse().getCode() + "  -  "+ response.getResponse().getMessage());
                    }
                });

    }


    public void purchase(int type, double amount){

        RequestPurchase p = new RequestPurchase();
        p.setUser("admin@gmail.com");
        p.setType(type);
        p.setAmount(String.valueOf( amount));


        // RxJava
        mAPIService.callPurchase("cad_seguridad",p).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponsePurchase>() {
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
                    public void onNext(ResponsePurchase response) {

                        Log.e("OkHttp", "RESUL .: "+response.getStatus() );

                        presenter.endPurchase(response);

                        //showResponse(response.getResponse().getCode() + "  -  "+ response.getResponse().getMessage());
                    }
                });

    }








}
