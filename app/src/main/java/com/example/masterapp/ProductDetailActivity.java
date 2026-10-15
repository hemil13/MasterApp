package com.example.masterapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView productImage, wishlist, cart_icon, cart_minus, cart_add;
    LinearLayout cart_layout;
    TextView productName, productDesc, productOriginalPrice, productDiscountPrice, cart_qty;

    SharedPreferences sp;

    SQLiteDatabase db;

    Boolean isWishlist = false;

    int cartTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

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

        String wishlistTable = "CREATE TABLE IF NOT EXISTS wishlist (wishlistid INTEGER PRIMARY KEY AUTOINCREMENT, productid INTEGER, userid INTEGER)";
        db.execSQL(wishlistTable);

        productImage = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_detail_name);
        productDesc = findViewById(R.id.product_detail_desc);
        productOriginalPrice = findViewById(R.id.product_detail_original_price);
        productDiscountPrice = findViewById(R.id.product_detail_after_discount_price);

        wishlist = findViewById(R.id.product_detail_wishlist);

        cart_icon = findViewById(R.id.product_detail_cart);
        cart_layout = findViewById(R.id.product_detail_cart_layout);
        cart_minus = findViewById(R.id.product_detail_cart_minus);
        cart_add = findViewById(R.id.product_detail_cart_add);
        cart_qty = findViewById(R.id.product_detail_cart_qty);


        productImage.setImageResource(sp.getInt(ConstantSp.PRODUCT_IMAGE, 0));
        productName.setText(sp.getString(ConstantSp.PRODUCT_NAME, ""));
        productDesc.setText(sp.getString(ConstantSp.PRODUCT_DESC, ""));
        productOriginalPrice.setText(String.valueOf(sp.getInt(ConstantSp.PRODUCT_ORIGINAL_PRICE, 0)));
        productDiscountPrice.setText(String.valueOf(sp.getInt(ConstantSp.PRODUCT_DISCOUNT_PRICE, 0)));

        String checkWishlist = "SELECT * FROM wishlist WHERE productid = '"+sp.getInt(ConstantSp.PRODUCT_ID, 0)+"' AND userid = '"+sp.getString(ConstantSp.USER_ID, "")+"'";
        Cursor cursor = db.rawQuery(checkWishlist, null);
        if(cursor.getCount()>0){
            isWishlist = true;
            wishlist.setImageResource(R.drawable.wishlist_fill);
        }
        else{
            isWishlist = false;
            wishlist.setImageResource(R.drawable.wishlist_empty);
        }




        wishlist.setOnClickListener(new View.OnClickListener() {

            // added (isWhislist = true) -> click -> remove
            // not added(Default) (isWihslist = false) -> click -> add
            @Override
            public void onClick(View view) {

                // true hai -> added hai
                if(isWishlist){
                    Toast.makeText(ProductDetailActivity.this, "Removed From Wishlist", Toast.LENGTH_SHORT).show();
                    isWishlist = false;

                    String deleteFromWishlist = "DELETE FROM wishlist WHERE productid = '"+sp.getInt(ConstantSp.PRODUCT_ID, 0)+"' AND userid = '"+sp.getString(ConstantSp.USER_ID, "")+"'";
                    db.execSQL(deleteFromWishlist);

                    wishlist.setImageResource(R.drawable.wishlist_empty);
                }

                // False hai -> added nai hai
                else{
                    Toast.makeText(ProductDetailActivity.this, "Added To Wishlist!", Toast.LENGTH_SHORT).show();
                    isWishlist = true;

                    String insertInWishlist = "INSERT INTO wishlist VALUES(NULL, '"+sp.getInt(ConstantSp.PRODUCT_ID, 0)+"', '"+sp.getString(ConstantSp.USER_ID, "")+"')";
                    db.execSQL(insertInWishlist);

                    wishlist.setImageResource(R.drawable.wishlist_fill);
                }
            }
        });


        cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailActivity.this, "Added To Cart!", Toast.LENGTH_SHORT).show();
                cartTotal++;
                cart_qty.setText(String.valueOf(cartTotal));
                cart_layout.setVisibility(View.VISIBLE);
                cart_icon.setVisibility(View.GONE);
            }
        });






    }
}

// 1 set through SP
// 2 toggle in wishlist (only toast msg)