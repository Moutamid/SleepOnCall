package com.moutamid.sleeponcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.moutamid.sleeponcall.activities.SignupActivity;
import com.moutamid.sleeponcall.databinding.ActivityMainBinding;
import com.moutamid.sleeponcall.utilis.Constants;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.checkApp(this);

        binding.create.setOnClickListener(v -> {
            startActivity(new Intent(this,  SignupActivity.class));
            finish();
        });

    }
}