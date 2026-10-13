package com.example.masterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    ArrayList<ProductList> arrayList;

    public ProductAdapter(Context context, ArrayList<ProductList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_product_layout, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, originalPrice, discountPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.custom_product_image);
            name = itemView.findViewById(R.id.custom_product_layout_name);
            originalPrice = itemView.findViewById(R.id.custom_product_layout_original_price);
            discountPrice = itemView.findViewById(R.id.custom_product_layout_discount_price);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.image.setImageResource(arrayList.get(position).getImage());
        holder.name.setText(arrayList.get(position).getName());
        holder.originalPrice.setText(String.valueOf(arrayList.get(position).getOriginalPrice()));
        holder.discountPrice.setText(String.valueOf(arrayList.get(position).getDiscountPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pass
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
