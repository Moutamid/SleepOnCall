package com.moutamid.sleeponcall.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.moutamid.sleeponcall.R;
import com.moutamid.sleeponcall.databinding.ActivitySigninBinding;
import com.moutamid.sleeponcall.utilis.Constants;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Constants.initDialog(this);

        binding.toolbar.title.setText("Login to your account");
        binding.toolbar.backBtn.setOnClickListener(v -> onBackPressed());

        binding.forgot.setOnClickListener(v -> {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
            finish();
        });
        binding.signup.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        });

        binding.login.setOnClickListener(v -> {
            if (valid()){
                Constants.showDialog();
                Constants.auth().signInWithEmailAndPassword(
                        binding.email.getEditText().getText().toString(),
                        binding.password.getEditText().getText().toString()
                ).addOnSuccessListener(authResult -> {
                    Constants.dismissDialog();
                    startActivity(new Intent(this, DashboardActivity.class));
                    finish();
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

    }

    private boolean valid() {
        if (binding.email.getEditText().getText().toString().isEmpty()) {
            binding.email.getEditText().setError("Email is Empty!");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getEditText().getText().toString()).matches()) {
            binding.email.getEditText().setError("Email is not valid!");
            return false;
        }
        if (binding.password.getEditText().getText().toString().isEmpty()) {
            binding.password.getEditText().setError("Password is Empty!");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, SignupActivity.class));
        finish();
    }
}