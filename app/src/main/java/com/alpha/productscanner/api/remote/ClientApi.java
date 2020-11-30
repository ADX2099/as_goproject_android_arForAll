package com.alpha.productscanner.api.remote;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.alpha.productscanner.api.remote.ApiUtils.getAPIService;
import static com.alpha.productscanner.constant.AlphaConstant.BASE_URL;
import static com.alpha.productscanner.constant.AlphaConstant.BASE_URL_PAYPAL;

public class ClientApi {

    private static final String TAG = ClientApi.class.getSimpleName();
    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder httpClient  = new OkHttpClient.Builder();
    //private static Gson customGson;

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient
                /*.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)*/
                .addInterceptor(interceptor);

        OkHttpClient client = httpClient.build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   // .addConverterFactory(GsonConverterFactory.create(customGson()))
                    //.addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }

        return retrofit;
    }


    public static Retrofit getClientPayPal() {


        Log.d("OkHttp", "================================> ENTRANDO APIS ERVICE PAYPAL: "  );
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_PAYPAL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



       return retrofit;


/*



        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient();

*/

          /*
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient

                .addInterceptor(interceptor);

        OkHttpClient client = httpClient.build();
        */
/*
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_PAYPAL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //.addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .addConverterFactory(new ToStringConverterFactory())


                    .client(client)
                    .build();
        }

        return retrofit;

        */
    }

/*

    protected static Gson customGson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BaseResponse.class, new BaseResponseDeserialize())
                .create();
        return gson;
    }
*/
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier((hostname, session) -> true);
            OkHttpClient okHttpClient = builder.build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
