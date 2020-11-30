package com.alpha.productscanner.ui.ar;

import com.alpha.productscanner.dto.response.ResponseBrand;
import com.alpha.productscanner.dto.response.ResponseServer;
import com.alpha.productscanner.model.BrandModel;

public class ArPresenter implements ArInterface.Presenter {

    BrandModel model;
    ArActivity view;

    public ArPresenter(ArActivity view){
        model = new BrandModel(this);
        this.view = view;

    }

    public void getBrand(int idBrand){

        //validaciones de negocio

        model.GetBrandInfoById(idBrand);

    }


    @Override
    public void onSuccess(ResponseBrand response) {

        if (response.getCode()==0){
            if (response.getBrands().size()>0){
            view.onSuccess(response.getBrands().get(0));
            }else{
                view.onFailure("Marca no encontrada.");

            }
        }else{
            view.onFailure(response.getMessage());
        }

    }

    @Override
    public void onFailure(ResponseServer rerror) {
        view.onFailure(rerror.getMessage());
    }
}

