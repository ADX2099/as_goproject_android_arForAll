// Generated code from Butter Knife. Do not modify!
package com.alpha.productscanner.ui.Registro;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.alpha.productscanner.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegistroActivity_ViewBinding implements Unbinder {
  private RegistroActivity target;

  private View view7f09005a;

  @UiThread
  public RegistroActivity_ViewBinding(RegistroActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegistroActivity_ViewBinding(final RegistroActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnRegistro, "field 'btnRegistro' and method 'onClickActions'");
    target.btnRegistro = Utils.castView(view, R.id.btnRegistro, "field 'btnRegistro'", Button.class);
    view7f09005a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickActions(p0);
      }
    });
    target.edtNombre = Utils.findRequiredViewAsType(source, R.id.edtNombre, "field 'edtNombre'", EditText.class);
    target.edtApellido = Utils.findRequiredViewAsType(source, R.id.edtApellido, "field 'edtApellido'", EditText.class);
    target.edtTelefono = Utils.findRequiredViewAsType(source, R.id.edtTelefono, "field 'edtTelefono'", EditText.class);
    target.edtEmail = Utils.findRequiredViewAsType(source, R.id.edtEmail, "field 'edtEmail'", EditText.class);
    target.edtPwd = Utils.findRequiredViewAsType(source, R.id.edtPwd, "field 'edtPwd'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegistroActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnRegistro = null;
    target.edtNombre = null;
    target.edtApellido = null;
    target.edtTelefono = null;
    target.edtEmail = null;
    target.edtPwd = null;

    view7f09005a.setOnClickListener(null);
    view7f09005a = null;
  }
}
