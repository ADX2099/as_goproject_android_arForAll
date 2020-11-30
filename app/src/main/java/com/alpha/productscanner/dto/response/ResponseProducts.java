package com.alpha.productscanner.dto.response;

import com.alpha.productscanner.dto.Product;
import com.alpha.productscanner.dto.Products;

import java.util.ArrayList;

public class ResponseProducts extends ResponseServer {

    ArrayList<Products> products = new ArrayList<>();


    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }
}
