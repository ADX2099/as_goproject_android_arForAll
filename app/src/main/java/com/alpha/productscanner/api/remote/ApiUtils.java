package com.alpha.productscanner.api.remote;

import android.util.Log;

import static com.alpha.productscanner.constant.AlphaConstant.BASE_URL;
import static com.alpha.productscanner.constant.AlphaConstant.BASE_URL_PAYPAL;

public class ApiUtils {

    private ApiUtils() {}



    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);

     //   return ClientApi.getClient().create(APIService.class);

    }

    public static APIService getAPIServicePayPal() {

        Log.d("OkHttp", "================================> APISERVICE PAYPAL: " );
        return RetrofitClient.getClient(BASE_URL_PAYPAL).create(APIService.class);

        //   return ClientApi.getClient().create(APIService.class);

    }




}
