package com.example.masterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button login_button;
    TextView create_new_account;
    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        db = openOrCreateDatabase(ConstantSp.DBNAME, MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(userTable);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_button = findViewById(R.id.sign_in_button);
        create_new_account = findViewById(R.id.create_new_account);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();

                if (email_text.isEmpty()){
                    email.setError("Enter Email");
                    email.requestFocus();
                    return;
                }
                else if (password_text.isEmpty()){
                    password.setError("Enter Password");
                    password.requestFocus();
                }

                else{
                    String checkUser = "SELECT * FROM user WHERE email = '"+email_text+"' AND password = '"+password_text+"'"; //email aur password same row
                    Cursor cursor = db.rawQuery(checkUser, null);
                    if(cursor.getCount()>0){

                       // 1   demo    demo@gmail.com    976543210   123123

                        while(cursor.moveToNext()){
                            sp.edit().putString(ConstantSp.USER_ID, cursor.getString(0)).commit();
                            sp.edit().putString(ConstantSp.USER_NAME, cursor.getString(1)).commit();
                            sp.edit().putString(ConstantSp.USER_EMAIL, cursor.getString(2)).commit();
                            sp.edit().putString(ConstantSp.USER_CONTACT, cursor.getString(3)).commit();
                            sp.edit().putString(ConstantSp.USER_PASSWORD, cursor.getString(4)).commit();
                        }

                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

        create_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Signup_activity.class);
                startActivity(intent);
            }
        });




    }
}



