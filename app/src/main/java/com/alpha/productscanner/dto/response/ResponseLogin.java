package com.alpha.productscanner.dto.response;

public class ResponseLogin extends ResponseServer {

    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
