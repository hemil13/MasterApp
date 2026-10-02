package com.example.masterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {
    TextView welcome;

    Button delete_profile, logout, profile, category;

    SharedPreferences sp;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        db = openOrCreateDatabase("masterapp.db", MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(userTable);

        welcome = findViewById(R.id.dashboard_welcome);

        welcome.setText("Welcome "+ sp.getString(ConstantSp.USER_NAME, ""));



        delete_profile = findViewById(R.id.dashboard_delete);
        logout = findViewById(R.id.dashboard_logout);
        profile = findViewById(R.id.dashboard_profile);
        category = findViewById(R.id.dashboard_category);


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                Toast.makeText(DashboardActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        delete_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteUser = "DELETE FROM user WHERE userid = '"+sp.getString(ConstantSp.USER_ID, "")+"'";
                db.execSQL(deleteUser);

                sp.edit().clear().commit();
                Toast.makeText(DashboardActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });




    }
}