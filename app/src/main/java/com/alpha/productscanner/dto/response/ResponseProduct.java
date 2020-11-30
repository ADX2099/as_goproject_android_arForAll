package com.alpha.productscanner.dto.response;

import com.alpha.productscanner.dto.Product;

import java.util.ArrayList;

public class ResponseProduct extends ResponseServer {

    ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}

