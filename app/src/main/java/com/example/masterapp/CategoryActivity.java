package com.example.masterapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryRecyclerView;

    int[] idArray = {1,2,3};
    String[] nameArrary = {"Electonics", "Clothes", "Books"};
    int[] imageArray = {R.drawable.electronics, R.drawable.clothes, R.drawable.books};
    SQLiteDatabase db;

    ArrayList<CategoryList> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = openOrCreateDatabase("masterapp.db", MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category(categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), image VARCHAR)";
        db.execSQL(categoryTable);

        setContentView(R.layout.activity_category);

        categoryRecyclerView = findViewById(R.id.category_recycler);


        for(int i=0; i<idArray.length; i++){
            String checkCategory = "SELECT * FROM category WHERE name = '"+nameArrary[i]+"'";
            Cursor cursor = db.rawQuery(checkCategory, null);
            if(cursor.getCount() == 0){
                String insertCategory = "INSERT INTO category VALUES(NULL, '"+nameArrary[i]+"', '"+imageArray[i]+"')";
                db.execSQL(insertCategory);
            }
        }


        arraylist = new ArrayList<>();

        String fetchCategory = "SELECT * FROM category";
        Cursor cursor = db.rawQuery(fetchCategory, null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                CategoryList list = new CategoryList();
                list.setId(cursor.getInt(0));
                list.setName(cursor.getString(1));
                list.setImage(cursor.getInt(2));
                arraylist.add(list);
            }
        }


//        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
//        categoryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        categoryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, idArray, nameArrary, imageArray);
        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, arraylist);
        categoryRecyclerView.setAdapter(adapter);
    }
}



