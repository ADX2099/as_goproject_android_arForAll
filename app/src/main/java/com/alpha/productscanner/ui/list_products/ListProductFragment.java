package com.alpha.productscanner.ui.list_products;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alpha.productscanner.R;
import com.alpha.productscanner.adapter.ProductAdapter;
import com.alpha.productscanner.constant.AlphaConstant;
import com.alpha.productscanner.custom.AlertaDialogAccept;
import com.alpha.productscanner.dto.Products;
import com.alpha.productscanner.manager.navigation.NavigatorManager;
import com.alpha.productscanner.ui.ar.ArActivity;
import com.alpha.productscanner.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListProductFragment extends Fragment implements ProductAdapter.ProductListener, ListProductInterface.View {
    public Unbinder unbinder;
    public View rootView;
    public ListProductPresenter presenter;
    private ProductAdapter adapter;


    private int idBrand;

    @BindView(R.id.imgBrand)
    ImageView imgBrand;

    @BindView(R.id.listProducts)
    RecyclerView listProducts;


    public static ListProductFragment newInstance(Bundle args) {
        ListProductFragment fragment = new ListProductFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_list_product, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {

                    ((MainActivity)getActivity()).navigatorManager.navigation(NavigatorManager.F_HOME,null);

                    return true;
                }
                return false;
            }
        } );



        presenter = new ListProductPresenter(getActivity(), this);

        idBrand= getArguments().getInt(AlphaConstant.BUNDLE_ID_BRAND);
        Log.d("ListProd-fragment", "el id: " + idBrand);
        presenter.GetProductsByIdBrand(idBrand);


    }

/*
    @Override
    public void onResume() {
        super.onResume();

        presenter = new ListProductPresenter(getActivity(), this);

        idBrand= getArguments().getInt(AlphaConstant.BUNDLE_ID_BRAND);

        Log.d("OkHttp", "================================> ON RESUME LIST: "+idBrand  );

       presenter.GetProductsByIdBrand(idBrand);
    }
*/
    @Override
    public void onDetail(Products m) {

        Bundle bundle = new Bundle();
        bundle.putInt(AlphaConstant.BUNDLE_ID_PRODUCT, m.getId());
        ((MainActivity)getActivity()).navigatorManager.navigation(NavigatorManager.F_DETAIL_PRODUCT,bundle);

    }


    @Override
    public void showProducts(List<Products> products) {

        Picasso.get().load("http://alphasoluciones.net:9010/images/cocacola.png").into(imgBrand);


        adapter = new ProductAdapter(getActivity(), products,  this);
        listProducts.setAdapter(adapter);
        listProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    @Override
    public void onFailed(String msg) {
        AlertaDialogAccept dialog = new AlertaDialogAccept(getActivity(), "", msg, null, false);
        dialog.show();

    }

    @OnClick({R.id.btnScan})
    public synchronized void onClickActions(View view) {

        switch (view.getId()) {
            case R.id.btnScan:

                Intent arIntent = new Intent(getContext(), ArActivity.class);
                startActivity(arIntent);

                break;
            default:
                break;
        }
    }


}
