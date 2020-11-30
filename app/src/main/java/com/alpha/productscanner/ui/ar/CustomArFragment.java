package com.alpha.productscanner.ui.ar;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpha.productscanner.R;
import com.alpha.productscanner.Singleton;
import com.alpha.productscanner.constant.AlphaConstant;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Collection;

public class CustomArFragment extends ArFragment  {



    @Override
    protected Config getSessionConfiguration(Session session) {
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        config.setFocusMode(Config.FocusMode.AUTO);

        session.configure(config);

        this.getArSceneView().setupSession(session);
        ((ArActivity) getActivity()).setUpDatabase(config, session);
        return  config;
    }


}
