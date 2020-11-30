package com.alpha.productscanner.api.remote;

import android.os.AsyncTask;
import android.util.Log;


import com.alpha.productscanner.model.PayPalModel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static com.alpha.productscanner.constant.AlphaConstant.BASE_URL_PAYPAL;

public class TokenService extends AsyncTask<String, String, String>  {

    PayPalModel model;


    public TokenService(PayPalModel model){
        this.model = model;
    }


    @Override
    protected String doInBackground(String... params) {

        try {
            Log.d("OkHttp", "================================> TOKEN SERVICE: " + BASE_URL_PAYPAL+"tk");
            // Creating & connection Connection with url and required Header.
            URL url = new URL(BASE_URL_PAYPAL+"tk");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "text/plain");
            urlConnection.setRequestMethod("GET");   //POST or GET
            urlConnection.connect();


            // Write Request to output stream to server.
            /*OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write("");
            out.close();*/

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
                model.onFailedToken("Excepción: " + statusCode + " : " + statusMsg);
            }
        } catch (ProtocolException e) {
            model.onFailedToken("Excepción: Error 10001" );
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            model.onFailedToken("Excepción: Error 10002" );
        } catch (IOException e) {
            e.printStackTrace();
            model.onFailedToken("Excepción: Error 10003" );
        } catch (Exception e) {
            e.printStackTrace();
            model.onFailedToken("Excepción: Error 10004" );
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {

        Log.d("OkHttp", "================================> result: " + result);
        model.sendToken(result);
    }

}
