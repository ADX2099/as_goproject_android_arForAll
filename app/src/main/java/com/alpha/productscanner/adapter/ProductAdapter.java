package com.alpha.productscanner.adapter;

import android.app.Activity;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.productscanner.R;
import com.alpha.productscanner.dto.Products;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.PendingHolder>  {

private Activity activity;
private List<Products> list;
private ProductListener productListener;

public interface ProductListener {
    //void onDelete(int position, String folio, String concepto, String importe);
    void onDetail(Products m);


}

    public ProductAdapter(Activity activity, List<Products> list, ProductListener productListener) {
        this.activity = activity;
        this.list = list;
        this.productListener = productListener;
    }

    @NonNull
    @Override
    public PendingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);

        return new ProductAdapter.PendingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingHolder pendingHolder, int i) {
        try {
            Products m = list.get(i);

            double[] numbers = new double[]{m.getWalmartprice(), m.getSorianaprice(),m.getSuperamaprice(),m.getChedrauiprice ()};
            Arrays.sort(numbers);


            Log.d("OkHttp", "================================> : ENTRO EN ADAPTER: " + m.getName() );
            Log.d("OkHttp", "================================> : ENTRO EN ADAPTER: " + numbers[0] );
            Log.d("OkHttp", "================================> : ENTRO EN ADAPTER: " + numbers[3] );
            Log.d("OkHttp", "================================> : ENTRO EN getWalmartprice: " + m.getWalmartprice() );
            Log.d("OkHttp", "================================> : ENTRO EN getSorianaprice: " + m.getSorianaprice() );

            Log.d("OkHttp", "================================> : ENTRO EN getSuperamaprice: " + m.getSuperamaprice() );
            Log.d("OkHttp", "================================> : ENTRO EN getCheadrauiprice: " + m.getChedrauiprice() );




            pendingHolder.tv_id.setText(String.valueOf(m.getId()));
            pendingHolder.tvTitle.setText(m.getName());
            pendingHolder.tv_precio1.setText(String.valueOf( numbers[0]));
            pendingHolder.tv_precio2.setText(String.valueOf(numbers[3]));

            pendingHolder.iv_detail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    productListener.onDetail(m);
                }

            });



            pendingHolder.tv_precio1.setPaintFlags(pendingHolder.tv_precio1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public synchronized void swap(List<Products> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public synchronized void onDeleteItem(int p){
        try {
            if(p != -1){
                list.remove(p);
                notifyItemRemoved(p);
                notifyItemRangeChanged(p, list.size());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


public class PendingHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle;
    final TextView tv_precio1;
    final TextView tv_precio2;
    final ImageView iv_detail;
    final TextView tv_id;



    public PendingHolder(View itemView){
        super(itemView);
        iv_detail = itemView.findViewById(R.id.iv_detail);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tv_precio1 = itemView.findViewById(R.id.tv_precio1);
        tv_precio2 = itemView.findViewById(R.id.tv_precio2);
        tv_id= itemView.findViewById(R.id.tv_id);

    }

}

}
