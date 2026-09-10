package com.example.masterapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class Signup_activity extends AppCompatActivity {

    TextView login_signup;

    EditText name, email_signup, number_signup, password_signup, confirm_password_signup;

    Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name_signup);
        email_signup = findViewById(R.id.email_signup);
        number_signup = findViewById(R.id.number_signup);
        password_signup = findViewById(R.id.password_signup);
        confirm_password_signup = findViewById(R.id.confirm_password_signup);
        login_signup = findViewById(R.id.login_signup);
        signup_button = findViewById(R.id.signup_button);

        signup_button.setOnClickListener(view -> {
            String name_text = name.getText().toString();
            String email_text = email_signup.getText().toString();
            String number_text = number_signup.getText().toString();
            String password_text = password_signup.getText().toString();
            String confirm_password_text = confirm_password_signup.getText().toString();

            if (name_text.isEmpty()){
                name.setError("Enter Name");
                name.requestFocus();
                return;
            }
            if (email_text.isEmpty()){
                email_signup.setError("Enter Email");
                email_signup.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()){
                email_signup.setError("Enter Valid Email");
                email_signup.requestFocus();
                return;
            }

            if (number_text.isEmpty()){
                number_signup.setError("Enter Number");
                number_signup.requestFocus();
                return;
            }
            if (number_text.length() !=10){
                number_signup.setError("Contact number must be 10 digits");
                number_signup.requestFocus();
                return;
            }
            if (password_text.isEmpty()){
                password_signup.setError("Enter Password");
                password_signup.requestFocus();
                return;
            }
            if (password_text.length() < 6){
                password_signup.setError("Password must be 6 characters long");
                password_signup.requestFocus();
                return;
            }
            if (confirm_password_text.isEmpty()){
                confirm_password_signup.setError("Enter Confirm Password");
                confirm_password_signup.requestFocus();
                return;
            }
            if (!confirm_password_text.equals(password_text)){
                confirm_password_signup.setError("Password does not match");
                confirm_password_signup.requestFocus();
                return;
            }

        });

        login_signup.setOnClickListener(view -> {
            Snackbar.make(view, "Welcome back", Snackbar.LENGTH_SHORT).setAction("Next", v -> {
                            Intent intent = new Intent(Signup_activity.this, MainActivity.class);
                            startActivity(intent);
            }).show();
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
//            startActivity(intent);
        });

    }
}