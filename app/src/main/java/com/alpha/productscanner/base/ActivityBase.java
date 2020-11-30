package com.alpha.productscanner.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.productscanner.R;
import com.alpha.productscanner.manager.navigation.NavigatorManager;
import com.alpha.productscanner.ui.ar.CustomArFragment;
import com.google.ar.sceneform.FrameTime;
import com.alpha.productscanner.preference.AlphaPreference;

public class ActivityBase extends AppCompatActivity {

    public NavigatorManager navigatorManager;
    public AlphaPreference mPreference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        navigatorManager = new NavigatorManager();
        mPreference = new AlphaPreference(this);

    }



    public synchronized void showToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            View toastView = getLayoutInflater().inflate(R.layout.toast_custom, null);
            ((TextView) toastView.findViewById(R.id.tv_message)).setText(message);
            Toast toast = new Toast(this);
            toast.setView(toastView);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
    }

}
