package com.moutamid.sleeponcall.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moutamid.sleeponcall.R;
import com.moutamid.sleeponcall.databinding.ActivityDashboardBinding;
import com.moutamid.sleeponcall.fragments.PurchaseFragment;
import com.moutamid.sleeponcall.fragments.FeedFragment;
import com.moutamid.sleeponcall.fragments.ProfileFragment;
import com.moutamid.sleeponcall.utilis.Constants;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.checkApp(this);
        Constants.initDialog(this);

        binding.bottomNav.setItemActiveIndicatorColor(ColorStateList.valueOf(getResources().getColor(R.color.Pink)));
        binding.bottomNav.setItemActiveIndicatorWidth(100);
        binding.bottomNav.setItemActiveIndicatorHeight(100);
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.bottomNav.setSelectedItemId(R.id.feed_nav);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.feed_nav ){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout , new FeedFragment()).commit();
            return true;
        } else  if (item.getItemId() == R.id.buy_nav ){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout , new PurchaseFragment()).commit();
            return true;
        } else  if (item.getItemId() == R.id.profile_nav ){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout , new ProfileFragment()).commit();
            return true;
        }
        return false;
    }

}