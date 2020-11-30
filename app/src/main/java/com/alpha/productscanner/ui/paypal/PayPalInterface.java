package com.alpha.productscanner.ui.paypal;

public interface PayPalInterface {

    interface View {
        void onSussessPayPal(String token);
        void onFailedPayPal(String msg);
        void checkoutSussess(String token);
        void onFailedCheckOut(String msg);
    }

    interface Presenter{
        void getToken();
        void onSussessToken(String token);
        void onFailedToken(String msg);
        void checkoutSussess(String token);
        void onFailedCheckOut(String msg);
    }
}
