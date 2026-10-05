package com.example.masterapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryRecyclerView;

    int[] idArray = {1,2,3};
    String[] nameArrary = {"Electonics", "Clothes", "Books"};
    int[] imageArray = {R.drawable.electronics, R.drawable.clothes, R.drawable.books};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);

        categoryRecyclerView = findViewById(R.id.category_recycler);

        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));

        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, idArray, nameArrary, imageArray);
        categoryRecyclerView.setAdapter(adapter);
    }
}