package com.alpha.productscanner.ui.list_products;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.alpha.productscanner.R;
import com.alpha.productscanner.dto.Product;
import com.alpha.productscanner.dto.response.ResponseProducts;
import com.alpha.productscanner.model.ListProductModel;
import com.alpha.productscanner.ui.ar.ArActivity;

import java.util.List;

import butterknife.OnClick;

public class ListProductPresenter  {

    ListProductFragment view;

    ListProductModel model;
    Activity activity;





    public ListProductPresenter(Activity activity,ListProductFragment listProductFragment){
        this.view=listProductFragment;
        model = new ListProductModel(this);
        this.activity=activity;

    }


    public void GetProductsByIdBrand(int idBrand){


        model.GetProductsByBrand(idBrand);

    }



    public void ShowProducts(ResponseProducts response){

        if (response.getCode()==0){
            if (response.getProducts().size()==0){
                view.onFailed(activity.getString(R.string.lbl_msg_no_data_products));

            }else{
                view.showProducts(response.getProducts() );
            }

        }else{
            view.onFailed(response.getMessage());

        }

    }

}
