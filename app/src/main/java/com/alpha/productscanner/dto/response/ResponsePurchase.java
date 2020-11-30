package com.alpha.productscanner.dto.response;

public class ResponsePurchase extends ResponseServer {

    String status;
    String transaction_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
