package com.example.masterapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sp;

    public ProductAdapter(Context context, ArrayList<ProductList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
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
                sp.edit().putInt(ConstantSp.PRODUCT_ID, arrayList.get(position).getProdId()).commit();
                sp.edit().putInt(ConstantSp.PRODUCT_IMAGE, arrayList.get(position).getImage()).commit();
                sp.edit().putString(ConstantSp.PRODUCT_NAME, arrayList.get(position).getName()).commit();
                sp.edit().putString(ConstantSp.PRODUCT_DESC, arrayList.get(position).getProdDesc()).commit();
                sp.edit().putInt(ConstantSp.PRODUCT_ORIGINAL_PRICE, arrayList.get(position).getOriginalPrice()).commit();
                sp.edit().putInt(ConstantSp.PRODUCT_DISCOUNT_PRICE, arrayList.get(position).getDiscountPrice()).commit();

                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
