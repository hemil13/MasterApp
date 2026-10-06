package com.example.masterapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    int[] idArray;
    String[] nameArrary;
    int[] imageArray;

    public CategoryAdapter(Context context, int[] idArray, String[] nameArrary, int[] imageArray) {
        this.context = context;
        this.idArray = idArray;
        this.nameArrary = nameArrary;
        this.imageArray = imageArray;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.category_name);
            image = itemView.findViewById(R.id.category_image);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(nameArrary[position]);
        holder.image.setImageResource(imageArray[position]);

    }

    @Override
    public int getItemCount() {
        return idArray.length;
    }
}


//
//1. variable initialization and declaration
//2. view allocation
//3. id allocation
//4. set data
//5. length set