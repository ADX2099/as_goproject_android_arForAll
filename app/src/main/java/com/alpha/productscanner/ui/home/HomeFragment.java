package com.alpha.productscanner.ui.home;


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
import android.widget.TextView;

import com.alpha.productscanner.R;
import com.alpha.productscanner.Singleton;
import com.alpha.productscanner.constant.AlphaConstant;
import com.alpha.productscanner.manager.navigation.NavigatorManager;
import com.alpha.productscanner.ui.ar.ArActivity;
import com.alpha.productscanner.ui.main.MainActivity;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeInterface.View{

    public Unbinder unbinder;
    public View rootView;
    public HomePresenter presenter;
    public ArActivity activityAr;



    @BindView(R.id.txtLabel)
    TextView txtLabel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new HomePresenter(this);

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


                    ((MainActivity)getActivity()).closeSesion();

                    return true;
                }
                return false;
            }
        } );

    }

    @OnClick({R.id.btnScan})
    public synchronized void onClickActions(View view) {

        switch (view.getId()) {
            case R.id.btnScan:

                Intent arIntent = new Intent(getContext(), ArActivity.class);
                startActivity(arIntent);
                //presenter.camAR(activityAr);
                break;
            default:
                break;
        }
    }

    @Override
    public void callList(int idBrand) {
        Log.d("OkHttp", "================================> : CLICK "  );

        Bundle bundle = new Bundle();

        bundle.putInt(AlphaConstant.BUNDLE_ID_BRAND, idBrand);
        ((MainActivity)getActivity()).mPreference.setStringData(AlphaConstant.BUNDLE_ID_BRAND,""+idBrand);



        ((MainActivity)getActivity()).navigatorManager.navigation(NavigatorManager.F_LIST_PRODUCT,bundle);
    }
}
