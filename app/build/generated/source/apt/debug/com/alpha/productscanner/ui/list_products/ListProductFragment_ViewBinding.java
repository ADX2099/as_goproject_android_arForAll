// Generated code from Butter Knife. Do not modify!
package com.alpha.productscanner.ui.list_products;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.alpha.productscanner.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListProductFragment_ViewBinding implements Unbinder {
  private ListProductFragment target;

  private View view7f09005b;

  @UiThread
  public ListProductFragment_ViewBinding(final ListProductFragment target, View source) {
    this.target = target;

    View view;
    target.imgBrand = Utils.findRequiredViewAsType(source, R.id.imgBrand, "field 'imgBrand'", ImageView.class);
    target.listProducts = Utils.findRequiredViewAsType(source, R.id.listProducts, "field 'listProducts'", RecyclerView.class);
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
    ListProductFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgBrand = null;
    target.listProducts = null;

    view7f09005b.setOnClickListener(null);
    view7f09005b = null;
  }
}
