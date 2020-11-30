// Generated code from Butter Knife. Do not modify!
package com.alpha.productscanner.ui.home;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.alpha.productscanner.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view7f09005b;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.txtLabel = Utils.findRequiredViewAsType(source, R.id.txtLabel, "field 'txtLabel'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnScan, "method 'onClickActions'");
    view7f09005b = view;
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
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtLabel = null;

    view7f09005b.setOnClickListener(null);
    view7f09005b = null;
  }
}
