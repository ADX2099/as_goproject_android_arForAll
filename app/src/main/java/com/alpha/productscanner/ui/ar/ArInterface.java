package com.alpha.productscanner.ui.ar;

import com.alpha.productscanner.dto.Brand;
import com.alpha.productscanner.dto.response.ResponseBrand;
import com.alpha.productscanner.dto.response.ResponseServer;

public interface ArInterface {

    interface View{
        void onSuccess(Brand brandData);
        void onFailure(String msg);
    }

    interface Presenter{
        void onSuccess(ResponseBrand response);
        void onFailure(ResponseServer rerror);
    }


}
