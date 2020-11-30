package com.alpha.productscanner.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.alpha.productscanner.base.ActivityBase;
import com.alpha.productscanner.ui.login.LoginActivity;
import com.alpha.productscanner.ui.main.MainActivity;
import com.alpha.productscanner.R;
import com.alpha.productscanner.ui.paypal.PayPalActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static com.alpha.productscanner.constant.AlphaConstant.TOKEN_FIREBASE;

public class SplashActivity extends ActivityBase {


    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
      //  FirebaseApp.initializeApp(this);


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        String token = task.getResult().getToken();
                        String msg = getString(R.string.fcm_token, token);
                        Log.d("OkHttp", "================================> TOKEN FIREBASE: " + token );
                        mPreference.setStringData(TOKEN_FIREBASE,token);



                    }
                });




        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }


}



