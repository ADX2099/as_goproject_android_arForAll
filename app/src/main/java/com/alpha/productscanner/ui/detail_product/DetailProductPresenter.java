package com.alpha.productscanner.ui.detail_product;

import android.app.Activity;

import com.alpha.productscanner.R;
import com.alpha.productscanner.dto.Product;
import com.alpha.productscanner.dto.response.ResponseProduct;
import com.alpha.productscanner.dto.response.ResponsePurchase;
import com.alpha.productscanner.model.DetailProductModel;
import com.alpha.productscanner.model.ListProductModel;
import com.alpha.productscanner.ui.list_products.ListProductFragment;

public class DetailProductPresenter {
    DetailProductFragment view;

    DetailProductModel model;
    Activity activity;





    public DetailProductPresenter(Activity activity,DetailProductFragment view){
        this.view=view;
        model = new DetailProductModel(this);
        this.activity=activity;

    }



    public void getProduct(int idProduct){
        //model.
        model.getProduct(idProduct);

    }


    public void purchase(int type, double total){

        model.purchase(type,total);


    }

    public void endPurchase(ResponsePurchase response){

        if (response.getCode()==0){

                view.endPurchaseAlert(activity.getString(R.string.lbl_endPurchase) );

        }else{
            view.onFailed(response.getMessage());

        }

    }

    public void showDetail(ResponseProduct response){

        if (response.getCode()==0){

            if (response.getProducts().size()== 0){
                view.onFailed(activity.getString(R.string.lbl_msg_no_data_products));

            }else{
                view.showDetail(response.getProducts().get(0) );
            }

        }else{
            view.onFailed(response.getMessage());

        }


    }

}
