package com.example.masterapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    EditText name, email, contact, password, confrimPassword;
    Button edit, update;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        name = findViewById(R.id.name_profile);
        email = findViewById(R.id.email_profile);
        contact = findViewById(R.id.contact_profile);
        password = findViewById(R.id.password_profile);
        edit = findViewById(R.id.profile_btn);
        update = findViewById(R.id.update_profile_btn);
        confrimPassword = findViewById(R.id.confirm_password_profile);

        name.setText(sp.getString(ConstantSp.USER_NAME,""));
        email.setText(sp.getString(ConstantSp.USER_EMAIL,""));
        contact.setText(sp.getString(ConstantSp.USER_CONTACT,""));
        password.setText(sp.getString(ConstantSp.USER_PASSWORD,""));



        setData(false);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(true);

                update.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                confrimPassword.setVisibility(View.VISIBLE);

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(false);

                update.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);
                confrimPassword.setVisibility(View.GONE);

            }
        });

    }

    void setData(Boolean b){
        name.setEnabled(b);
        email.setEnabled(b);
        contact.setEnabled(b);
        password.setEnabled(b);

    }
}