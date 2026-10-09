package com.example.masterapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {

    RecyclerView subCategoryRecyclerView;

    int[] subIdArray = {1,2,3,4,5,6,7,8,9};
    int[] catIdArray = {1,1,1,2,2,2,3,3,3};
    String[] nameArray = {"EarBuds", "Mobile Phone", "Headphones",
            "Jeans", "Shirt", "Tshirt",
            "Horror", "Novel", "Fiction"};
    int[] imageArray = {R.drawable.earbuds, R.drawable.mobile, R.drawable.headphone,
            R.drawable.jeans, R.drawable.shirt, R.drawable.thsirt,
            R.drawable.horror, R.drawable.novel, R.drawable.fiction};

    SQLiteDatabase db;

    SharedPreferences sp;

    ArrayList<SubcategoryList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        db = openOrCreateDatabase("masterapp.db", MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category(categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), image VARCHAR)";
        db.execSQL(categoryTable);

        String subcategoryTable = "CREATE TABLE IF NOT EXISTS subcategory(subcategoryid INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, name VARCHAR(100), image VARCHAR)";
        db.execSQL(subcategoryTable);


        subCategoryRecyclerView = findViewById(R.id.subCategoryRecycler);

        for (int i = 0; i<subIdArray.length; i++){
            String checkSubCategory = "SELECT * FROM subcategory WHERE name = '"+nameArray[i]+"'";
            Cursor cursor = db.rawQuery(checkSubCategory, null);
            if(cursor.getCount()==0){
                String insertSubCategory = "INSERT INTO subcategory VALUES(NULL, '"+catIdArray[i]+"', '"+nameArray[i]+"', '"+imageArray[i]+"')";
                db.execSQL(insertSubCategory);
            }
        }

        arrayList = new ArrayList<>();

        String fetchSubCategory =  "SELECT * FROM subcategory  WHERE categoryid = '"+sp.getInt(ConstantSp.CATEGORY_ID, 0)+"'";
        Cursor subCatCursor = db.rawQuery(fetchSubCategory, null);
        if(subCatCursor.getCount()>0){
            while(subCatCursor.moveToNext()){
                SubcategoryList list = new SubcategoryList();
                list.setSubId(subCatCursor.getInt(0));
                list.setCatId(subCatCursor.getInt(1));
                list.setName(subCatCursor.getString(2));
                list.setImage(subCatCursor.getInt(3));

                arrayList.add(list);
            }
        }

        subCategoryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SubCategoryAdapter adapter = new SubCategoryAdapter(SubCategoryActivity.this, arrayList);
        subCategoryRecyclerView.setAdapter(adapter);
    }
}