package com.alpha.productscanner.ui.paypal;

import com.alpha.productscanner.dto.response.ResponseServer;
import com.alpha.productscanner.model.PayPalModel;
import com.google.gson.Gson;

public class PayPalPresenter implements PayPalInterface.Presenter {

    PayPalFragment paypalFragment;

    PayPalModel model;


    public PayPalPresenter(PayPalFragment paypalFragment){
        this.paypalFragment=paypalFragment;
        model = new PayPalModel(this);

    }


    public void sendCheckout(String noce,String amount, String type, String idProduct, String user){
        model.sendCheckOut(noce, amount, type, idProduct, user);
    }
    @Override
    public void getToken() {

        model.getToken();
    }

    @Override
    public void onSussessToken(String token) {
        paypalFragment.onSussessPayPal(token);

    }

    @Override
    public void onFailedToken(String msg) {

        paypalFragment.onFailedPayPal(msg);
    }

    @Override
    public void checkoutSussess(String msg) {

        Gson gson = new Gson();

        ResponseServer res = gson.fromJson(msg,ResponseServer.class);

        if (res.getCode()==0){
            paypalFragment.checkoutSussess("La compra se realizó con éxito.");
        }else{
            paypalFragment.onFailedCheckOut(res.getMessage());

        }



    }

    @Override
    public void onFailedCheckOut(String msg) {

        paypalFragment.onFailedCheckOut(msg);
    }
}
