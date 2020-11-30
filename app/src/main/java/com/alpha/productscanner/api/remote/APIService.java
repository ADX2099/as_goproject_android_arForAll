package com.alpha.productscanner.api.remote;


import com.alpha.productscanner.dto.request.RequestGetProduct;
import com.alpha.productscanner.dto.request.RequestLogin;
import com.alpha.productscanner.dto.request.RequestPurchase;
import com.alpha.productscanner.dto.response.ResponseBrand;
import com.alpha.productscanner.dto.request.RequestRegistro;
import com.alpha.productscanner.dto.response.ResponseLogin;
import com.alpha.productscanner.dto.response.ResponseProduct;
import com.alpha.productscanner.dto.response.ResponseProducts;
import com.alpha.productscanner.dto.response.ResponsePurchase;
import com.alpha.productscanner.dto.response.ResponseRegistro;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static com.alpha.productscanner.constant.AlphaConstant.HEADER_AUTH;

public interface APIService {

    @POST("/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseLogin> access(@Body RequestLogin login);


    @POST("/register")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseRegistro> transfer(@Body RequestRegistro registro);


    @GET("/products/{idbrand}")
    //@FormUrlEncoded
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseProducts> callProducts(@Header(HEADER_AUTH) String auth,@Path("idbrand")   int idbrand);

  /*  @GET("/tk")
    @Headers({ "Content-Type: text/plain;charset=UTF-8"})
    Observable<String> callToken();
*/
    @GET("/tk")
    Call<String> callToken();

  
    @POST("/product")
    //@FormUrlEncoded
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseProduct> callProduct(@Header(HEADER_AUTH) String auth, @Body RequestGetProduct product);


  @POST("/purchase")
  //@FormUrlEncoded
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Observable<ResponsePurchase> callPurchase(@Header(HEADER_AUTH) String auth, @Body RequestPurchase purchase);

  @GET("/brand/{idbrand}")
  //@FormUrlEncoded
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Observable<ResponseBrand> callBrand(@Header(HEADER_AUTH) String auth, @Path("idbrand") int idbrand);

}