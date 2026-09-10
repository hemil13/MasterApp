package com.example.masterapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgetPasswordActivity extends AppCompatActivity {

    //Variable
    EditText email_forget, new_password, confirm_password;

    Button changePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

        email_forget = findViewById(R.id.email_forget);
        new_password = findViewById(R.id.new_password_forget);
        confirm_password = findViewById(R.id.confirm_new_password_forget);
        changePassword = findViewById(R.id.change_password_btn);


        changePassword.setOnClickListener(view -> {
            String email = email_forget.getText().toString().trim();
            String newPassword = new_password.getText().toString().trim();
            String confirmPassword = confirm_password.getText().toString().trim();


            if(email.isEmpty()){
                email_forget.setError("Enter Email");
                email_forget.requestFocus();
                return;
            }
            else if(newPassword.isEmpty()){
                new_password.setError("Enter New Password");
                new_password.requestFocus();
                return;
            }
            else if(newPassword.length() < 6){
                new_password.setError("Password must be of at least 6 characters");
                new_password.requestFocus();
                return;
            }
            else if(confirmPassword.isEmpty()){
                confirm_password.setError("Enter Confirm Password");
                confirm_password.requestFocus();
                return;
            }
            else if(!newPassword.equals(confirmPassword)){
                confirm_password.setError("Password do not match");
                confirm_password.requestFocus();
                return;
            }
            else {
                Toast.makeText(this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}