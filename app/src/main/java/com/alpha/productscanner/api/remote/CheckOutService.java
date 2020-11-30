package com.alpha.productscanner.api.remote;

import android.os.AsyncTask;
import android.util.Log;

import com.alpha.productscanner.model.PayPalModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import static com.alpha.productscanner.constant.AlphaConstant.BASE_URL_PAYPAL;

public class CheckOutService extends AsyncTask<String, String, String>  {

    PayPalModel model;
    String payment_method_nonce;
    String amount;
    String type;
    String idProduct;
    String user;

    public CheckOutService(PayPalModel model, String payment_method_nonce, String amount, String type, String idProduct, String user){
        this.model = model;
        this.payment_method_nonce=payment_method_nonce;
        this.amount=amount;
        this.type=type;
        this.idProduct=idProduct;
        this.user=user;

    }


    @Override
    protected String doInBackground(String... params) {

        try {

            Log.d("OkHttp", "================================> WEBSERVICE CHECKOUT: " );
            // Creating & connection Connection with url and required Header.
            URL url = new URL(BASE_URL_PAYPAL+"checkout");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setRequestMethod("POST");   //POST or GET
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);






            try {
                HashMap<String, String> paramsData= new HashMap<>();

                paramsData.put("payment_method_nonce" , payment_method_nonce);
                paramsData.put("amount" , amount);
                paramsData.put("user" , user);
                paramsData.put("type" , type);
                paramsData.put("idProduct" , idProduct);


                StringBuilder sbParams = new StringBuilder();
                int i = 0;
                for (String key : paramsData.keySet()) {
                    try {
                        if (i != 0){
                            sbParams.append("&");
                        }
                        sbParams.append(key).append("=")
                                .append(URLEncoder.encode(paramsData.get(key), "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                urlConnection.connect();


                String paramsString = sbParams.toString();
                Log.d("OkHttp", "================================> CADENA WEBSERVICE POST: " + paramsString);

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(paramsString);
                wr.flush();
                wr.close();



            } catch (Exception ex) {
                ex.printStackTrace();
            }









            // Check the connection status.
            int statusCode = urlConnection.getResponseCode();
            String statusMsg = urlConnection.getResponseMessage();


            // Connection success. Proceed to fetch the response.
            if (statusCode == 200) {
                InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                InputStreamReader read = new InputStreamReader(it);
                BufferedReader buff = new BufferedReader(read);
                StringBuilder dta = new StringBuilder();
                String chunks;
                while ((chunks = buff.readLine()) != null) {
                    dta.append(chunks);
                }
                String returndata = dta.toString();



                return returndata;
            } else {
                //Handle else case
                model.onFailedCheckOut("Excepción: " + statusCode + " : " + statusMsg);
            }
        } catch (ProtocolException e) {
            model.onFailedCheckOut("Excepción: Error 10001" );
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            model.onFailedCheckOut("Excepción: Error 10002" );
        } catch (IOException e) {
            e.printStackTrace();
            model.onFailedCheckOut("Excepción: Error 10003" );
        } catch (Exception e) {
            e.printStackTrace();
            model.onFailedCheckOut("Excepción: Error 10004" );
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {

        Log.d("OkHttp", "================================> result: " + result);
        model.checkoutSussess(result);
    }




}
