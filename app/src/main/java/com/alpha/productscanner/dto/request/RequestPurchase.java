package com.alpha.productscanner.dto.request;

public class RequestPurchase {
    String user;
    String amount;
    int type;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
