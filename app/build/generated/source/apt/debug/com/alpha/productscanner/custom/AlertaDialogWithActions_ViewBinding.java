// Generated code from Butter Knife. Do not modify!
package com.alpha.productscanner.custom;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.alpha.productscanner.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlertaDialogWithActions_ViewBinding implements Unbinder {
  private AlertaDialogWithActions target;

  private View view7f09005c;

  private View view7f09005d;

  @UiThread
  public AlertaDialogWithActions_ViewBinding(AlertaDialogWithActions target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlertaDialogWithActions_ViewBinding(final AlertaDialogWithActions target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tvMessage'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_cancel, "field 'btnCancel' and method 'onClickActions'");
    target.btnCancel = Utils.castView(view, R.id.btn_cancel, "field 'btnCancel'", Button.class);
    view7f09005c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickActions(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_ok, "method 'onClickActions'");
    view7f09005d = view;
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
    AlertaDialogWithActions target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvMessage = null;
    target.btnCancel = null;

    view7f09005c.setOnClickListener(null);
    view7f09005c = null;
    view7f09005d.setOnClickListener(null);
    view7f09005d = null;
  }
}
