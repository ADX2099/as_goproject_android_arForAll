package com.alpha.productscanner.ui.detail_product;

import com.alpha.productscanner.dto.Product;

public interface DetailProductInterface {

    interface View{
        void onFailed(String msg);
        void showDetail(Product p);
    }

    interface Presenter{



    }

}
