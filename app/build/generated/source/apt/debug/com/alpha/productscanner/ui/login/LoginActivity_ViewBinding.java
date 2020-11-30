// Generated code from Butter Knife. Do not modify!
package com.alpha.productscanner.ui.login;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.alpha.productscanner.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f090059;

  private View view7f090127;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.edtUsuario = Utils.findRequiredViewAsType(source, R.id.edtUsuario, "field 'edtUsuario'", EditText.class);
    target.edtPassword = Utils.findRequiredViewAsType(source, R.id.edtPassword, "field 'edtPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnIngresar, "method 'onClickActions'");
    view7f090059 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickActions(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.txtRegistrar, "method 'onClickActions'");
    view7f090127 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickActions(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtUsuario = null;
    target.edtPassword = null;

    view7f090059.setOnClickListener(null);
    view7f090059 = null;
    view7f090127.setOnClickListener(null);
    view7f090127 = null;
  }
}
