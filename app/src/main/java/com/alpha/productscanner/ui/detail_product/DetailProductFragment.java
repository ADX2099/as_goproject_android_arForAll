package com.alpha.productscanner.ui.detail_product;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.productscanner.R;
import com.alpha.productscanner.constant.AlphaConstant;
import com.alpha.productscanner.custom.AlertaDialogAccept;
import com.alpha.productscanner.custom.AlertaDialogWithActions;
import com.alpha.productscanner.dto.Product;
import com.alpha.productscanner.listener.DialogListener;
import com.alpha.productscanner.manager.navigation.NavigatorManager;
import com.alpha.productscanner.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.alpha.productscanner.constant.AlphaConstant.ID_PRODUCT_PURCHASE;
import static com.alpha.productscanner.constant.AlphaConstant.PRICE_PURCHASE;
import static com.alpha.productscanner.constant.AlphaConstant.TYPE_PURCHASE;
import static com.alpha.productscanner.constant.AlphaConstant.USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailProductFragment extends Fragment implements DetailProductInterface.View {

    public Unbinder unbinder;
    public View rootView;
    public DetailProductPresenter presenter;
    int idProduct;
    Product p;
    String tienda = "";
    double precio=0;
    int type=0;

    @BindView(R.id.imgProducto)
    ImageView imgProducto;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtPrecio1)
    TextView txtPrecio1;

    @BindView(R.id.txtData1)
    TextView txtData1;

    @BindView(R.id.txtPrecio2)
    TextView txtPrecio2;

    @BindView(R.id.txtDescription)
    TextView txtDescription;

    @BindView(R.id.precioWalmart)
    TextView precioWalmart;

    @BindView(R.id.precioSoriana)
    TextView precioSoriana;

    @BindView(R.id.precioSumesa)
    TextView precioSumesa;

    @BindView(R.id.precioChedraui)
    TextView precioChedraui;


    public static DetailProductFragment newInstance(Bundle args) {
        DetailProductFragment fragment = new DetailProductFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_detail_product, container, false);
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
                    Bundle bundle = new Bundle();

                    Log.d("OkHttp", "================================> IDBRAND ON DETAIL: " + ((MainActivity)getActivity()).mPreference.getStringData(AlphaConstant.BUNDLE_ID_BRAND));
                    bundle.putInt(AlphaConstant.BUNDLE_ID_BRAND, Integer.valueOf(((MainActivity)getActivity()).mPreference.getStringData(AlphaConstant.BUNDLE_ID_BRAND)));

                    ((MainActivity)getActivity()).navigatorManager.navigation(NavigatorManager.F_LIST_PRODUCT,bundle);

                    return true;
                }
                return false;
            }
        } );



        presenter = new DetailProductPresenter(getActivity(), this);

        idProduct= getArguments().getInt(AlphaConstant.BUNDLE_ID_PRODUCT);

        presenter.getProduct(idProduct);


    }



    @OnClick({R.id.layWalmart, R.id.laySoriana, R.id.laySumesa, R.id.layChedraui})
    public synchronized void onClickActions(View view) {



        switch (view.getId()) {
            case R.id.layWalmart:

                Log.d("OkHttp", "================================> WALMART: "  );
                type=1;
                precio=p.getWalmartprice();
                tienda="WALMART";



                break;
            case R.id.laySoriana:

                Log.d("OkHttp", "================================> SORIANA: "  );
                type=2;
                precio=p.getSorianaprice();
                tienda="SORIANA";
                break;
            case R.id.laySumesa:

                Log.d("OkHttp", "================================> SUMESA: "  );
                type=3;
                precio=p.getSuperamaprice();
                tienda="SUMESA";
                break;
            case R.id.layChedraui:

                Log.d("OkHttp", "================================> CHEDRAUI: "  );
                type=4;
                precio=p.getChedrauiprice();
                tienda="CHEDRAUI";
                break;
            default:
                break;
        }


        String cad = "¿Desea comprar en " + tienda + " el producto: " + p.getName() +" con un precio de: " + String.valueOf(precio);



        AlertaDialogWithActions dialog = new AlertaDialogWithActions(getActivity(), "Alphasoluciones", cad, new DialogListener() {
            @Override
            public void onAccept() {
                purchasePayPal(type,precio);
                //presenter.purchase(type,precio);
            }

            @Override
            public void onCancel() {
                return;

            }
        },        false);
        dialog.show();



    }


    private void sendPurchase(View view){
        switch (view.getId()) {
            case R.id.layWalmart:

                Log.d("OkHttp", "================================> WALMART: "  );
                purchasePayPal(1,p.getWalmartprice());


                break;
            case R.id.laySoriana:

                Log.d("OkHttp", "================================> SORIANA: "  );
                purchasePayPal(2,p.getSorianaprice());
                break;
            case R.id.laySumesa:

                Log.d("OkHttp", "================================> SUMESA: "  );
                purchasePayPal(3,p.getSuperamaprice());
                break;
            case R.id.layChedraui:

                Log.d("OkHttp", "================================> CHEDRAUI: "  );
                purchasePayPal(4,p.getChedrauiprice());
                break;
            default:
                break;
        }
    }


    void purchasePayPal(int type, double price){

        ((MainActivity)getActivity()).mPreference.setStringData(TYPE_PURCHASE,""+type);
        ((MainActivity)getActivity()).mPreference.setStringData(PRICE_PURCHASE,""+price);
        ((MainActivity)getActivity()).mPreference.setStringData(ID_PRODUCT_PURCHASE,""+p.getId());


        ((MainActivity)getActivity()).navigatorManager.navigation(NavigatorManager.F_PAYPAL,null);


    }


    public void endPurchaseAlert(String msg){

        AlertaDialogAccept dialog = new AlertaDialogAccept(getActivity(), "", msg, null, false);
        dialog.show();
    }

    @Override
    public void showDetail(Product p) {

        this.p=p;

        Log.d("OkHttp", "================================> ENTRO EN SHOW DETAIL: " + p.getImageName());

        double[] numbers = new double[]{p.getWalmartprice(), p.getSorianaprice(),p.getSuperamaprice(),p.getChedrauiprice()};
        Arrays.sort(numbers);




        Picasso.get().load(p.getImageName()).into(imgProducto);


        txtName.setText(p.getName());

        txtPrecio1.setText(String.valueOf( numbers[3] ));

        txtData1.setText("");

        txtPrecio2.setText(String.valueOf( numbers[0] ));


        txtDescription.setText(p.getDescription());

        precioWalmart.setText(String.valueOf( p.getWalmartprice() ));

        precioSoriana.setText(String.valueOf( p.getSorianaprice() ));

        precioSumesa.setText(String.valueOf( p.getSuperamaprice() ));

        precioChedraui.setText(String.valueOf( p.getChedrauiprice() ));
    }



    @Override
    public void onFailed(String msg) {
        AlertaDialogAccept dialog = new AlertaDialogAccept(getActivity(), "", msg, null, false);
        dialog.show();

    }

}
