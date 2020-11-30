package com.alpha.productscanner.model;

public interface PayPalModelInterface {

    void sendToken(String token);
    void onFailedToken(String msg);

    void checkoutSussess(String token);
    void onFailedCheckOut(String msg);

}
