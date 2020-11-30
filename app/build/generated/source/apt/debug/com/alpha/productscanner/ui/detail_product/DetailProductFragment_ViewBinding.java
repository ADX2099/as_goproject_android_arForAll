// Generated code from Butter Knife. Do not modify!
package com.alpha.productscanner.ui.detail_product;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.alpha.productscanner.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailProductFragment_ViewBinding implements Unbinder {
  private DetailProductFragment target;

  private View view7f0900ab;

  private View view7f0900a9;

  private View view7f0900aa;

  private View view7f0900a8;

  @UiThread
  public DetailProductFragment_ViewBinding(final DetailProductFragment target, View source) {
    this.target = target;

    View view;
    target.imgProducto = Utils.findRequiredViewAsType(source, R.id.imgProducto, "field 'imgProducto'", ImageView.class);
    target.txtName = Utils.findRequiredViewAsType(source, R.id.txtName, "field 'txtName'", TextView.class);
    target.txtPrecio1 = Utils.findRequiredViewAsType(source, R.id.txtPrecio1, "field 'txtPrecio1'", TextView.class);
    target.txtData1 = Utils.findRequiredViewAsType(source, R.id.txtData1, "field 'txtData1'", TextView.class);
    target.txtPrecio2 = Utils.findRequiredViewAsType(source, R.id.txtPrecio2, "field 'txtPrecio2'", TextView.class);
    target.txtDescription = Utils.findRequiredViewAsType(source, R.id.txtDescription, "field 'txtDescription'", TextView.class);
    target.precioWalmart = Utils.findRequiredViewAsType(source, R.id.precioWalmart, "field 'precioWalmart'", TextView.class);
    target.precioSoriana = Utils.findRequiredViewAsType(source, R.id.precioSoriana, "field 'precioSoriana'", TextView.class);
    target.precioSumesa = Utils.findRequiredViewAsType(source, R.id.precioSumesa, "field 'precioSumesa'", TextView.class);
    target.precioChedraui = Utils.findRequiredViewAsType(source, R.id.precioChedraui, "field 'precioChedraui'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layWalmart, "method 'onClickActions'");
    view7f0900ab = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickActions(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.laySoriana, "method 'onClickActions'");
    view7f0900a9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickActions(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.laySumesa, "method 'onClickActions'");
    view7f0900aa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickActions(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layChedraui, "method 'onClickActions'");
    view7f0900a8 = view;
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
    DetailProductFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgProducto = null;
    target.txtName = null;
    target.txtPrecio1 = null;
    target.txtData1 = null;
    target.txtPrecio2 = null;
    target.txtDescription = null;
    target.precioWalmart = null;
    target.precioSoriana = null;
    target.precioSumesa = null;
    target.precioChedraui = null;

    view7f0900ab.setOnClickListener(null);
    view7f0900ab = null;
    view7f0900a9.setOnClickListener(null);
    view7f0900a9 = null;
    view7f0900aa.setOnClickListener(null);
    view7f0900aa = null;
    view7f0900a8.setOnClickListener(null);
    view7f0900a8 = null;
  }
}
