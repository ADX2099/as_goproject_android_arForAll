package com.alpha.productscanner.model;

import android.util.Log;

import com.alpha.productscanner.api.remote.APIService;
import com.alpha.productscanner.api.remote.ApiUtils;
import com.alpha.productscanner.dto.response.ResponseBrand;
import com.alpha.productscanner.dto.response.ResponseServer;
import com.alpha.productscanner.ui.ar.ArPresenter;
import com.alpha.productscanner.ui.ar.CustomArFragment;
import com.alpha.productscanner.ui.ar.CustomArFragmentPresenter;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import rx.schedulers.Schedulers;

public class BrandModel {
    ArPresenter presenter;
    private APIService mAPIService;
    //---------------------------------------------------------------------------------------------
    public BrandModel(ArPresenter presenter) {
        this.presenter = presenter;
        mAPIService =  ApiUtils.getAPIService();
    }
    //---------------------------------------------------------------------------------------------
    public void GetBrandInfoById(int idBrand){
        // RxJava
        mAPIService.callBrand("cad_seguridad", idBrand)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBrand>() {
                    @Override
                    public void onCompleted() {
                        Log.d("okHttp", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();

                            try {


                                Gson json = new Gson();

                                ResponseServer rerror= new ResponseServer();
                                rerror = json.fromJson(responseBody.string(),ResponseServer.class);



                                presenter.onFailure(rerror);


                            } catch (Exception ex) {

                                Log.e("OkHttp", "Error .: " + ex.getMessage());
                            }

                        }
                    }
                    @Override
                    public void onNext(ResponseBrand response) {
                        Log.e("OkHttp", "RESUL .: "+response.getBrands().size() );
                        presenter.onSuccess(response);
                    }
                });

    }


    //---------------------------------------------------------------------------------------------




}
