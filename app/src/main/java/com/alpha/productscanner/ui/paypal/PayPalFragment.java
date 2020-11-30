package com.alpha.productscanner.ui.paypal;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alpha.productscanner.R;
import com.alpha.productscanner.constant.AlphaConstant;
import com.alpha.productscanner.custom.AlertaDialogAccept;
import com.alpha.productscanner.listener.DialogAcceptListener;
import com.alpha.productscanner.manager.navigation.NavigatorManager;
import com.alpha.productscanner.ui.main.MainActivity;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.CardBuilder;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.alpha.productscanner.constant.AlphaConstant.ID_PRODUCT_PURCHASE;
import static com.alpha.productscanner.constant.AlphaConstant.PRICE_PURCHASE;
import static com.alpha.productscanner.constant.AlphaConstant.TYPE_PURCHASE;
import static com.alpha.productscanner.constant.AlphaConstant.USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayPalFragment extends Fragment implements PayPalInterface.View {
    public Unbinder unbinder;
    public View rootView;

    int REQUEST_CODE=100;
    int RESULT_OK=0;
    int RESULT_CANCELED=-1;

    int DROP_IN_REQUEST=100;
    PayPalFragment mBraintreeFragment;

    PayPalPresenter presenter;


    public static PayPalFragment newInstance(Bundle args) {

        PayPalFragment fragment = new PayPalFragment();
        fragment.setArguments(args);
        return fragment;
     }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_pay_pal, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        presenter= new PayPalPresenter(this);
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
                    Log.d("OkHttp", "================================> RETURN FROM PAYPAL: "  );

                    Bundle bundle = new Bundle();

                    bundle.putInt(AlphaConstant.BUNDLE_ID_PRODUCT, Integer.valueOf(((MainActivity)getActivity()).mPreference.getStringData(AlphaConstant.ID_PRODUCT_PURCHASE)));


                    ((MainActivity)getActivity()).navigatorManager.navigation(NavigatorManager.F_DETAIL_PRODUCT,bundle);

                    return true;
                }
                return false;
            }
        } );



        presenter.getToken();


    }




    public void onBraintreeSubmit(String mAuthorization) {

       // mAuthorization="eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiJhZmYzYzQzZGQ5MmUzOGZkNGY4NmI3ZGM4YWY1OGQxZGQ2ZmE1NDM2MGVhZjNkZWM1OTIzNWRjNzkxMzRkYmYyfGNyZWF0ZWRfYXQ9MjAxOS0wNS0wOFQxNzozMjo1Ni45NTEzNDAzMzYrMDAwMFx1MDAyNm1lcmNoYW50X2lkPXM2ejl4ZzVkOTVyNTRwd2NcdTAwMjZwdWJsaWNfa2V5PTZqa25wajdqcWZmN3YyamMiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvczZ6OXhnNWQ5NXI1NHB3Yy9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJncmFwaFFMIjp7InVybCI6Imh0dHBzOi8vcGF5bWVudHMuc2FuZGJveC5icmFpbnRyZWUtYXBpLmNvbS9ncmFwaHFsIiwiZGF0ZSI6IjIwMTgtMDUtMDgifSwiY2hhbGxlbmdlcyI6W10sImVudmlyb25tZW50Ijoic2FuZGJveCIsImNsaWVudEFwaVVybCI6Imh0dHBzOi8vYXBpLnNhbmRib3guYnJhaW50cmVlZ2F0ZXdheS5jb206NDQzL21lcmNoYW50cy9zNno5eGc1ZDk1cjU0cHdjL2NsaWVudF9hcGkiLCJhc3NldHNVcmwiOiJodHRwczovL2Fzc2V0cy5icmFpbnRyZWVnYXRld2F5LmNvbSIsImF1dGhVcmwiOiJodHRwczovL2F1dGgudmVubW8uc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbSIsImFuYWx5dGljcyI6eyJ1cmwiOiJodHRwczovL29yaWdpbi1hbmFseXRpY3Mtc2FuZC5zYW5kYm94LmJyYWludHJlZS1hcGkuY29tL3M2ejl4ZzVkOTVyNTRwd2MifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiUGVyc29uYWwiLCJjbGllbnRJZCI6bnVsbCwicHJpdmFjeVVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS9wcCIsInVzZXJBZ3JlZW1lbnRVcmwiOiJodHRwOi8vZXhhbXBsZS5jb20vdG9zIiwiYmFzZVVybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXNzZXRzVXJsIjoiaHR0cHM6Ly9jaGVja291dC5wYXlwYWwuY29tIiwiZGlyZWN0QmFzZVVybCI6bnVsbCwiYWxsb3dIdHRwIjp0cnVlLCJlbnZpcm9ubWVudE5vTmV0d29yayI6dHJ1ZSwiZW52aXJvbm1lbnQiOiJvZmZsaW5lIiwidW52ZXR0ZWRNZXJjaGFudCI6ZmFsc2UsImJyYWludHJlZUNsaWVudElkIjoibWFzdGVyY2xpZW50MyIsImJpbGxpbmdBZ3JlZW1lbnRzRW5hYmxlZCI6dHJ1ZSwibWVyY2hhbnRBY2NvdW50SWQiOiJwZXJzb25hbCIsImN1cnJlbmN5SXNvQ29kZSI6IlVTRCJ9LCJtZXJjaGFudElkIjoiczZ6OXhnNWQ5NXI1NHB3YyIsInZlbm1vIjoib2ZmIn0=";

        DropInRequest dropInRequest = new DropInRequest()
                .clientToken(mAuthorization)
                .amount("100")
                .collectDeviceData(true)
                .disablePayPal();
        startActivityForResult(dropInRequest.getIntent(getContext()), REQUEST_CODE);





    }





/*

    public void setupBraintreeAndStartExpressCheckout() {
        PayPalRequest request = new PayPalRequest("1")
                .currencyCode("USD")
                .intent(PayPalRequest.INTENT_AUTHORIZE);

        PayPal.requestOneTimePayment(this, request);
    }


    @Override
    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        // Send nonce to server
        String nonce = paymentMethodNonce.getNonce();
        if (paymentMethodNonce instanceof PayPalAccountNonce) {
            PayPalAccountNonce payPalAccountNonce = (PayPalAccountNonce)paymentMethodNonce;

            // Access additional information
            String email = payPalAccountNonce.getEmail();
            String firstName = payPalAccountNonce.getFirstName();
            String lastName = payPalAccountNonce.getLastName();
            String phone = payPalAccountNonce.getPhone();

            // See PostalAddress.java for details
            PostalAddress billingAddress = payPalAccountNonce.getBillingAddress();
            PostalAddress shippingAddress = payPalAccountNonce.getShippingAddress();
        }
    }


*/





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("OkHttp", "================================> requestCode: " +requestCode );
        Log.d("OkHttp", "================================> resultCode: " +resultCode );
//        Log.d("OkHttp", "================================> data: " +data.getDataString() );

        if (requestCode == DROP_IN_REQUEST) {
            if (resultCode == RESULT_OK) {
                try {
                    DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                    String paymentMethodNonce = result.getPaymentMethodNonce().getNonce();

                    Log.d("OkHttp", "================================> RESULT_OK: " + paymentMethodNonce);
                }catch(Exception ex){
                    return;
                }
                // send paymentMethodNonce to your server
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("OkHttp", "================================> RESULT_CANCELED: "+data.getDataString()  );

                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                String paymentMethodNonce = result.getPaymentMethodNonce().getNonce();

                Log.d("OkHttp", "================================> RESULT_OK CANCELED: " +paymentMethodNonce );



                String amount=((MainActivity)getActivity()).mPreference.getStringData(PRICE_PURCHASE);
                String type=((MainActivity)getActivity()).mPreference.getStringData(TYPE_PURCHASE);
                String idProduct=((MainActivity)getActivity()).mPreference.getStringData(ID_PRODUCT_PURCHASE);
                String user=((MainActivity)getActivity()).mPreference.getStringData(USER);
                presenter.sendCheckout(paymentMethodNonce,amount,type,idProduct,user);

//sendCheckout(String noce,String amount, String type, String idProduct, String user){

                // canceled
            } else {
                // an error occurred, checked the returned exception
                Exception exception = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("OkHttp", "================================> ERROR: "  +exception.getMessage());

            }
        }
    }



    public void onBraintreeSubmit2(View v) {


        CardBuilder cardBuilder = new CardBuilder()
                .cardNumber("4111111111111111")
                .expirationDate("09/2018");

        //Card.tokenize(mBraintreeFragment, cardBuilder);

        /*
        try {
            String mAuthorization="A21AAH1B_vvkWurB-toi-QPdjNKPCewC809LupyjBgYOY02cxjbhjuZPDy6pSc7Ud9hak2elBvtEDBw-9Jqqa4krI0MiTzkYg";
            mBraintreeFragment = BraintreeFragment.newInstance(getActivity(), mAuthorization);
            // mBraintreeFragment is ready to use!

        } catch (InvalidArgumentException e) {
            // There was an issue with your authorization string.
        }

*/

        /*
        BraintreeGateway gateway = new BraintreeGateway(
                Environment.SANDBOX,
                "s6z9xg5d95r54pwc",
                "6jknpj7jqff7v2jc",
                "0e93e7046069cee399ffbe8be775ebe7"
        );
        */

/*
        DropInRequest dropInRequest = new DropInRequest()
                .clientToken("eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiI4ZDQ5ZDQwZWVmZmY2MzAwZjA2Y2Y2NzQzYmI0YTI3MjhjMWY0OWVkOTU5NWU5N2ZhZDI5MTJjMmNhOTFkZGI2fGNyZWF0ZWRfYXQ9MjAxOS0wNS0wN1QxODo0NDo0MS45OTc2MjYzMzcrMDAwMFx1MDAyNm1lcmNoYW50X2lkPTM0OHBrOWNnZjNiZ3l3MmJcdTAwMjZwdWJsaWNfa2V5PTJuMjQ3ZHY4OWJxOXZtcHIiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvMzQ4cGs5Y2dmM2JneXcyYi9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJncmFwaFFMIjp7InVybCI6Imh0dHBzOi8vcGF5bWVudHMuc2FuZGJveC5icmFpbnRyZWUtYXBpLmNvbS9ncmFwaHFsIiwiZGF0ZSI6IjIwMTgtMDUtMDgifSwiY2hhbGxlbmdlcyI6W10sImVudmlyb25tZW50Ijoic2FuZGJveCIsImNsaWVudEFwaVVybCI6Imh0dHBzOi8vYXBpLnNhbmRib3guYnJhaW50cmVlZ2F0ZXdheS5jb206NDQzL21lcmNoYW50cy8zNDhwazljZ2YzYmd5dzJiL2NsaWVudF9hcGkiLCJhc3NldHNVcmwiOiJodHRwczovL2Fzc2V0cy5icmFpbnRyZWVnYXRld2F5LmNvbSIsImF1dGhVcmwiOiJodHRwczovL2F1dGgudmVubW8uc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbSIsImFuYWx5dGljcyI6eyJ1cmwiOiJodHRwczovL29yaWdpbi1hbmFseXRpY3Mtc2FuZC5zYW5kYm94LmJyYWludHJlZS1hcGkuY29tLzM0OHBrOWNnZjNiZ3l3MmIifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiQWNtZSBXaWRnZXRzLCBMdGQuIChTYW5kYm94KSIsImNsaWVudElkIjpudWxsLCJwcml2YWN5VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3BwIiwidXNlckFncmVlbWVudFVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS90b3MiLCJiYXNlVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhc3NldHNVcmwiOiJodHRwczovL2NoZWNrb3V0LnBheXBhbC5jb20iLCJkaXJlY3RCYXNlVXJsIjpudWxsLCJhbGxvd0h0dHAiOnRydWUsImVudmlyb25tZW50Tm9OZXR3b3JrIjp0cnVlLCJlbnZpcm9ubWVudCI6Im9mZmxpbmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJtYXN0ZXJjbGllbnQzIiwiYmlsbGluZ0FncmVlbWVudHNFbmFibGVkIjp0cnVlLCJtZXJjaGFudEFjY291bnRJZCI6ImFjbWV3aWRnZXRzbHRkc2FuZGJveCIsImN1cnJlbmN5SXNvQ29kZSI6IlVTRCJ9LCJtZXJjaGFudElkIjoiMzQ4cGs5Y2dmM2JneXcyYiIsInZlbm1vIjoib2ZmIn0=")
                .disablePayPal()
                .amount("100")
                ;

        startActivityForResult(dropInRequest.getIntent(getContext()), REQUEST_CODE);

        */
    }

    @Override
    public void onSussessPayPal(String token) {

        Log.d("OkHttp", "================================> INICIA PAYPAL: "  );
        onBraintreeSubmit(token);
    }

    @Override
    public void onFailedPayPal(String msg) {
        Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(getContext(), "Error al generar token de seguriadd de PayPal Sandbox", Toast.LENGTH_SHORT).show();
                AlertaDialogAccept dialog = new AlertaDialogAccept(getActivity(), "", "Error al generar token de seguriadd de PayPal Sandbox", null, false);
                dialog.show();
            }
        };






    }

    @Override
    public void checkoutSussess(String msg) {

        Log.d("OkHttp", "================================> EXITO EN CHECKOUT: "  );
        AlertaDialogAccept dialog = new AlertaDialogAccept(getActivity(), "", msg, new DialogAcceptListener() {
            @Override
            public void onAccept() {
                Bundle bundle = new Bundle();


                bundle.putInt(AlphaConstant.BUNDLE_ID_BRAND, Integer.valueOf ( ((MainActivity)getActivity()).mPreference.getStringData(AlphaConstant.BUNDLE_ID_BRAND)));

                ((MainActivity)getActivity()).navigatorManager.navigation(NavigatorManager.F_LIST_PRODUCT,bundle);

            }
        }, false);
        dialog.show();


  /*      Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {


            }
        };
*/
    }

    @Override
    public void onFailedCheckOut(String msg) {
        Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                AlertaDialogAccept dialog = new AlertaDialogAccept(getActivity(), "", msg, null, false);
                dialog.show();

            }
        };

    }

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {



        Log.d("OkHttp", "================================>requestCode : " + requestCode );
        Log.d("OkHttp", "================================>resultCode : " + resultCode );

        Log.d("OkHttp", "================================>REQUEST_CODE : " + REQUEST_CODE );
        Log.d("OkHttp", "================================>RESULT_OK : " + RESULT_OK );
        Log.d("OkHttp", "================================>RESULT_CANCELED : " + RESULT_CANCELED );
        if (requestCode == REQUEST_CODE) {



            if (resultCode == RESULT_OK) {
                if (data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT) != null){
                    DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                }
                // use the result to update your UI and send the payment method nonce to your server
            } else if (resultCode == RESULT_CANCELED) {


                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);

                Log.d("OkHttp", "================================> ERROR: " +error.getMessage() );
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
*/

}
