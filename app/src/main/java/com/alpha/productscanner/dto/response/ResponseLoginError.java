package com.alpha.productscanner.dto.response;


public class ResponseLoginError {

    ResponseServer response = new ResponseServer();
    String token;

    public ResponseServer getResponse() {
        return response;
    }

    public void setResponse(ResponseServer response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
