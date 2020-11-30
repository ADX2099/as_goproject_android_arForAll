package com.alpha.productscanner.ui.Registro;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.alpha.productscanner.R;
import com.alpha.productscanner.custom.AlertaDialogAccept;
import com.alpha.productscanner.listener.DialogAcceptListener;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegistroActivity extends AppCompatActivity implements RegistroInterface.view {


    public Unbinder unbinder;

    RegistroPresenter presenter;

    @BindView(R.id.btnRegistro)
    Button btnRegistro;

    @BindView(R.id.edtNombre)
    EditText edtNombre;

    @BindView(R.id.edtApellido)
    EditText edtApellido;

    @BindView(R.id.edtTelefono)
    EditText edtTelefono;

    @BindView(R.id.edtEmail)
    EditText edtEmail;


    @BindView(R.id.edtPwd)
    EditText edtPwd;

    Activity activity;
    Context con;


  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        unbinder = ButterKnife.bind(this, this);
        con = this;
        activity = this;
    /*
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtApellido = (EditText) findViewById(R.id.edtApellido);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        txtIngresarRegistro = (TextView) findViewById(R.id.txtIngresarRegistro);
 */
    /*
        sNombre = edtNombre.getText().toString();
        sApellido = edtApellido.getText().toString();
        sTelefono = edtTelefono.getText().toString();
        sEmail = edtEmail.getText().toString();
    */


        presenter = new RegistroPresenter(con, this);





    }



    @OnClick({R.id.btnRegistro})
    public synchronized void onClickActions(View view) {

       process();


    }





    private void process(){

        if (

                (edtNombre.getText().toString().equals(""))
                        || (edtApellido.getText().toString().equals(""))
                        || (edtTelefono.getText().toString().equals(""))
                        || (edtEmail.getText().toString().equals(""))
                        || (edtPwd.getText().toString().equals(""))

        ) {

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Verifique los campos", Toast.LENGTH_SHORT);

            toast1.show();


        } else {




            presenter.Transfer(edtNombre.getText().toString(),edtApellido.getText().toString(),edtTelefono.getText().toString(), edtEmail.getText().toString(), edtPwd.getText().toString() );

        }


    }




    @Override
    public void onSucces() {

        AlertaDialogAccept dialog = new AlertaDialogAccept(activity, "", getText(R.string.msg_registro_exitoso).toString(), new DialogAcceptListener() {
            @Override
            public void onAccept() {
                return;
            }
        }, false);
        dialog.show();




        edtApellido.setText("");
        edtNombre.setText("");
        edtEmail.setText("");
        edtTelefono.setText("");
        edtPwd.setText("");

    }

    @Override
    public void onFailed(String msg) {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        msg, Toast.LENGTH_SHORT);

        toast1.show();

    }
}








