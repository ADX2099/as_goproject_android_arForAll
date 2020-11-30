package com.alpha.productscanner.custom;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alpha.productscanner.R;
import com.alpha.productscanner.listener.DialogListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AlertaDialogWithActions extends Dialog {

    private Unbinder unbinder;
    private Activity activity;
    private DialogListener dialogListener;
    private String title, message;
    private boolean isInfo;



    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    public AlertaDialogWithActions(Activity activity, String title, String message, DialogListener dialogListener, boolean isInfo) {
        super(activity);
        this.activity = activity;
        this.title = title;
        this.message = message;
        this.dialogListener = dialogListener;
        this.isInfo = isInfo;

    }



    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.alert_dialog_actions);
        unbinder = ButterKnife.bind(this);
        try {
            tvMessage.setText(message);
            if (TextUtils.isEmpty(title)) {
                if (isInfo) {
                    btnCancel.setVisibility(View.GONE);
                    tvTitle.setText(activity.getText(R.string.lbl_msg_fields));
                } else {
                    tvTitle.setVisibility(View.GONE);
                }
            } else {
                tvTitle.setText(title);
            }



            this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_ok, R.id.btn_cancel})
    public synchronized void onClickActions(View view){
        switch (view.getId()){
            case R.id.btn_ok:
                dismiss();
                if(dialogListener != null){
                    dialogListener.onAccept();
                }

                break;
            case R.id.btn_cancel:
                dismiss();
                if(dialogListener != null) {
                    dialogListener.onCancel();
                }
                break;
        }
    }

}
