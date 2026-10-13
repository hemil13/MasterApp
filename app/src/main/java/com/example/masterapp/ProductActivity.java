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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    int[] prodId = {1,2,3,4,5,6,7,8,9};
    int[] subId = {1,1,1,2,2,2,3,3,3};
    String[] name = {"Noise Aura", "Boat 120", "Airpodes Pro",
    "One Plus", "Redmi", "Sony",
    "Noise Headphone", "Airpodes Max", "Sony Headphone"};
    int[] image = {R.drawable.noiseaura, R.drawable.boat120, R.drawable.airpodspro2,
    R.drawable.oneplus, R.drawable.redmi, R.drawable.sony,
    R.drawable.noise, R.drawable.airpods_max, R.drawable.sony_headphones};

    String[] prodDesc = {"Noise Aura Prod Description", "Boat 120 Prod Description", "Airpodes Pro Prod Description",
    "One Plus Prod Description", "Redmi Prod Description", "Sony Prod Description",
    "Noise Headphone Prod Description", "Airpodes Max Prod Description", "Sony Headphone Prod Description"};
    int[] originalPrice = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000};
    int[] discountPrice = {800, 1800, 2800, 3800, 4800, 5800, 6800, 7800, 8800};

    ArrayList<ProductList> arrayList;

    SQLiteDatabase db;

    SharedPreferences sp;

    RecyclerView product_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        db = openOrCreateDatabase("masterapp.db", MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category(categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), image VARCHAR)";
        db.execSQL(categoryTable);

        String subcategoryTable = "CREATE TABLE IF NOT EXISTS subcategory(subcategoryid INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, name VARCHAR(100), image VARCHAR)";
        db.execSQL(subcategoryTable);

        String productTable = "CREATE TABLE IF NOT EXISTS product(productid INTEGER PRIMARY KEY AUTOINCREMENT, subcategoryid INTEGER, name VARCHAR(100), image VARCHAR, productDesc VARCHAR, originalprice INTEGER, discountprice INTEGER)";
        db.execSQL(productTable);

        product_recycler = findViewById(R.id.product_recycler);

        for (int i = 0; i<prodId.length; i++){
            String checkSubCategory = "SELECT * FROM product WHERE name = '"+name[i]+"' AND subcategoryid = '"+subId[i]+"'";
            Cursor cursor = db.rawQuery(checkSubCategory, null);
            if(cursor.getCount()==0){
                String insertSubCategory = "INSERT INTO product VALUES(NULL, '"+subId[i]+"', '"+name[i]+"', '"+image[i]+"', '"+prodDesc[i]+"', '"+originalPrice[i]+"', '"+discountPrice[i]+"')";
                db.execSQL(insertSubCategory);
            }
        }

        arrayList = new ArrayList<>();

        String fetchProducts =  "SELECT * FROM product  WHERE subcategoryid = '"+sp.getInt(ConstantSp.SUBCATEGORY_ID, 0)+"'";
        Cursor productCursor = db.rawQuery(fetchProducts, null);
        if(productCursor.getCount()>0){
            while(productCursor.moveToNext()){
                ProductList list = new ProductList();
                list.setProdId(productCursor.getInt(0));
                list.setSubId(productCursor.getInt(1));
                list.setName(productCursor.getString(2));
                list.setImage(productCursor.getInt(3));
                list.setProdDesc(productCursor.getString(4));
                list.setOriginalPrice(productCursor.getInt(5));
                list.setDiscountPrice(productCursor.getInt(6));

                arrayList.add(list);
            }
        }

        product_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ProductAdapter adapter = new ProductAdapter(ProductActivity.this, arrayList);
        product_recycler.setAdapter(adapter);

    }
}