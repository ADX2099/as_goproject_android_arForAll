package com.alpha.productscanner.model;

import android.content.Context;
import android.util.Log;

import com.alpha.productscanner.api.remote.APIService;
import com.alpha.productscanner.api.remote.ApiUtils;
import com.alpha.productscanner.dto.request.RequestLogin;
import com.alpha.productscanner.dto.response.ResponseLogin;
import com.alpha.productscanner.dto.response.ResponseLoginError;
import com.alpha.productscanner.dto.response.ResponseProducts;
import com.alpha.productscanner.preference.AlphaPreference;
import com.alpha.productscanner.ui.list_products.ListProductPresenter;
import com.alpha.productscanner.ui.login.LoginPresenter;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.alpha.productscanner.constant.AlphaConstant.PLATFORM;

public class LoginModel {

    private APIService mAPIService;
    LoginPresenter presenter;
    Context con;


    public LoginModel(Context con, LoginPresenter presenter){
        //coneccin con Modelo
        this.presenter=presenter;
        this.con=con;

        //crear instancia de retrofit con microservicios
        mAPIService =  ApiUtils.getAPIService();

    }





    public void Access(String user, String pwd, String token){

        //logica de acceso a ROOM, REST, datafile, ftp, etc.

        RequestLogin login = new RequestLogin();
        login.setPlatform(PLATFORM);
        login.setFbtoken(token);
        login.setUser(user);
        login.setPass(pwd);

        // RxJava
        mAPIService.access(login).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseLogin>() {
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

                                ResponseLoginError rerror= new ResponseLoginError();
                                rerror = json.fromJson(responseBody.string(),ResponseLoginError.class);


                                ResponseLogin res = new ResponseLogin();
                                res.setCode(rerror.getResponse().getCode());
                                res.setMessage(rerror.getResponse().getMessage());

                                presenter.ResponseProcessModel(res);


                            }catch(Exception ex){

                                Log.e("OkHttp", "Error .: " +  ex.getMessage());
                            }

  /*                          ResponseLogin res = new ResponseLogin();
                            Gson json =

                            res
                            responseBody.string()

*/




                        }


                    }

                    @Override
                    public void onNext(ResponseLogin response) {

                       

                        Log.e("OkHttp","================> EXITO SERVICIO");



                        if (response.getCode()==0){


                            AlphaPreference pre = new AlphaPreference(con);

                           pre.setStringData("TOKEN_WS",  response.getToken());

                            // guardar en preferences shared el token

                            response.getToken();

                        }

                        presenter.ResponseProcessModel(response);





                    }
                });


    }



}
