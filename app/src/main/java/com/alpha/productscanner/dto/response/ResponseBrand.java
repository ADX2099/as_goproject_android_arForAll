package com.alpha.productscanner.dto.response;

import com.alpha.productscanner.dto.Brand;
import com.alpha.productscanner.dto.Product;

import java.util.ArrayList;

public class ResponseBrand extends  ResponseServer{
    ArrayList<Brand> brands = new ArrayList<>();

    public ArrayList<Brand> getBrands() {
        return brands;
    }

    public void setBrands(ArrayList<Brand> brands) {
        this.brands = brands;
    }
}

