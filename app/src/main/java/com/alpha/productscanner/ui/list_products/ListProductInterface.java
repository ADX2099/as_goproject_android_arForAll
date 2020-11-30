package com.alpha.productscanner.ui.list_products;

import com.alpha.productscanner.dto.Product;
import com.alpha.productscanner.dto.Products;

import java.util.List;

public interface ListProductInterface {

    interface View{

        void showProducts(List<Products> products);
        void onFailed(String msg);


    }


    interface Presenter{

    }

}
