package com.moutamid.sleeponcall.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.moutamid.sleeponcall.R;
import com.moutamid.sleeponcall.databinding.ActivityForgotPasswordBinding;
import com.moutamid.sleeponcall.utilis.Constants;

public class ForgotPasswordActivity extends AppCompatActivity {
    ActivityForgotPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Constants.initDialog(this);

        binding.toolbar.title.setText("Forgot Password");
        binding.toolbar.backBtn.setOnClickListener(v -> onBackPressed());

        binding.recover.setOnClickListener(v -> {
            if (valid()) {
                Constants.showDialog();
                Constants.auth().sendPasswordResetEmail(binding.email.getEditText().getText().toString())
                        .addOnSuccessListener(unused -> {
                            Constants.dismissDialog();
                            Toast.makeText(this, "A Password Reset link is sent to your email", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(e -> {
                            Constants.dismissDialog();
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }

    private boolean valid() {
        if (binding.email.getEditText().getText().toString().isEmpty()){
            binding.email.getEditText().setError("Email is Empty!");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getEditText().getText().toString()).matches()){
            binding.email.getEditText().setError("Email is not valid!");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, SigninActivity.class));
        finish();
    }

}