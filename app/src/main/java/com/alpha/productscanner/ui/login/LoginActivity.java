package com.alpha.productscanner.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.alpha.productscanner.R;
import com.alpha.productscanner.base.ActivityBase;
import com.alpha.productscanner.custom.AlertaDialogAccept;
import com.alpha.productscanner.listener.DialogAcceptListener;
import com.alpha.productscanner.ui.Registro.RegistroActivity;
import com.alpha.productscanner.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.alpha.productscanner.constant.AlphaConstant.TOKEN_FIREBASE;
import static com.alpha.productscanner.constant.AlphaConstant.USER;

public class LoginActivity extends ActivityBase implements LoginInterface.view {


    public Unbinder unbinder;

    LoginPresenter presenter;




    @BindView(R.id.edtUsuario)
    EditText edtUsuario;
    @BindView(R.id.edtPassword)
    EditText edtPassword;

    Context con;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this, this);
        con = this;
        activity = this;

        //crea conección con presentador
        presenter = new LoginPresenter(con, this);



        edtUsuario.setText("admin@gmail.com");
        edtPassword.setText("12345678");



    }







    @OnClick({R.id.btnIngresar,R.id.txtRegistrar})
    public synchronized void onClickActions(View view) {

        switch (view.getId()) {
            case R.id.btnIngresar:
                process();
                break;

            case R.id.txtRegistrar:
                Intent ActivityReg = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(ActivityReg);
                break;


            default:
                break;
        }


    }





    private void process(){

        if(edtUsuario.getText().toString().equals("")){
            AlertaDialogAccept dialog = new AlertaDialogAccept(activity, "", getText(R.string.msg_user_null).toString(), new DialogAcceptListener() {
                @Override
                public void onAccept() {
                    return;
                }
            }, false);
            dialog.show();


        }else if(edtPassword.getText().toString().equals("")){
            AlertaDialogAccept dialog = new AlertaDialogAccept(activity, "", getText(R.string.msg_pwd_null).toString(), new DialogAcceptListener() {
                @Override
                public void onAccept() {
                    return;
                }
            }, false);
            dialog.show();


        }else{


            mPreference.setStringData(USER,edtUsuario.getText().toString());

            String tokenFirebase =mPreference.getStringData(TOKEN_FIREBASE);
            presenter.Access(edtUsuario.getText().toString(),edtPassword.getText().toString(),tokenFirebase );


        }





    }


    @Override
    public void onSucces() {


        Intent ActivityRes = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ActivityRes);
    }

    @Override
    public void onFailed(String msg) {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        msg, Toast.LENGTH_SHORT);

        toast1.show();

    }
}
