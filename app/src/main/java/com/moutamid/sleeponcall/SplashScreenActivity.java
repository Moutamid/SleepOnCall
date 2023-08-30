package com.moutamid.sleeponcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.moutamid.sleeponcall.activities.DashboardActivity;
import com.moutamid.sleeponcall.utilis.Constants;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() ->{

            if (Constants.auth().getCurrentUser() != null){
                startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);

    }
}