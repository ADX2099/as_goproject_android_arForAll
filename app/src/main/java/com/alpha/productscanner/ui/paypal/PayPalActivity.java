package com.alpha.productscanner.ui.paypal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alpha.productscanner.R;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.exceptions.BraintreeError;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeCancelListener;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;




import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.PostalAddress;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;




public class PayPalActivity extends AppCompatActivity  {
//    public class PayPalActivity extends AppCompatActivity implements PaymentMethodNonceCreatedListener, ConfigurationListener, BraintreeCancelListener, BraintreeErrorListener {

    BraintreeFragment mBraintreeFragment;

    int DROP_IN_REQUEST=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal);

      /*  try {

            String mAuthorization="A21AAFMDGCYpz9YVGHr0CdMYdVTFmHLgVoc3hn-grApACI8KQpx5VnHeqwEJew8htPDKpj8PyuZmTWB1mPpQrwherlf-R_tAA";
            mBraintreeFragment = BraintreeFragment.newInstance(this, mAuthorization);
            // mBraintreeFragment is ready to use!
            setProgressBarIndeterminateVisibility(true);

            PayPalRequest request = new PayPalRequest("1.00");
            PayPal.requestOneTimePayment(mBraintreeFragment, request);


        } catch (InvalidArgumentException e) {
            // There was an issue with your authorization string.
        }
*/

        String mAuthorization="A21AAFMDGCYpz9YVGHr0CdMYdVTFmHLgVoc3hn-grApACI8KQpx5VnHeqwEJew8htPDKpj8PyuZmTWB1mPpQrwherlf-R_tAA";
        DropInRequest dropInRequest = new DropInRequest()
                .clientToken(mAuthorization);
        startActivityForResult(dropInRequest.getIntent(this), DROP_IN_REQUEST);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DROP_IN_REQUEST) {
            if (resultCode == RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                String paymentMethodNonce = result.getPaymentMethodNonce().getNonce();


                Log.d("OkHttp", "================================> RESULT_OK: " +paymentMethodNonce );
                // send paymentMethodNonce to your server
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("OkHttp", "================================> RESULT_CANCELED: "  );
                // canceled
            } else {
                // an error occurred, checked the returned exception
                Exception exception = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("OkHttp", "================================> RESULT_CANCELED: "  );

            }
        }
    }



    /*
    @Override
    public void onCancel(int requestCode) {

        Log.d("OkHttp", "================================> CANCEL: " + requestCode);
    }

    @Override
    public void onError(Exception error) {
        if (error instanceof ErrorWithResponse) {
            ErrorWithResponse errorWithResponse = (ErrorWithResponse) error;
            BraintreeError cardErrors = errorWithResponse.errorFor("creditCard");
            if (cardErrors != null) {
                // There is an issue with the credit card.
                BraintreeError expirationMonthError = cardErrors.errorFor("expirationMonth");
                if (expirationMonthError != null) {
                    // There is an issue with the expiration month.

                    Log.d("OkHttp", "================================> ERROR : " + expirationMonthError.getMessage());
                }
            }
        }
    }

    @Override
    public void onConfigurationFetched(Configuration configuration) {

    }

    @Override
    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        String nonce = paymentMethodNonce.getNonce();
        Log.d("OkHttp", "================================> NONCE: " + nonce );
    }
*/

}
