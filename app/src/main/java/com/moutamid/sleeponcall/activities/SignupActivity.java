package com.moutamid.sleeponcall.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.moutamid.sleeponcall.R;
import com.moutamid.sleeponcall.databinding.ActivitySignupBinding;
import com.moutamid.sleeponcall.models.UserModel;
import com.moutamid.sleeponcall.utilis.Constants;
import com.shawnlin.numberpicker.NumberPicker;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    String age = "18";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Constants.initDialog(this);

        binding.toolbar.title.setText("Create Account");
        binding.toolbar.backBtn.setOnClickListener(v -> onBackPressed());

        binding.login.setOnClickListener(v -> {
            startActivity(new Intent(this, SigninActivity.class));
            finish();
        });

        binding.age.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                age = String.valueOf(newVal);
            }
        });

        binding.male.setChecked(true);

        binding.createAccount.setOnClickListener(v -> {
            if (valid()){
                Constants.showDialog();
                Constants.auth().createUserWithEmailAndPassword(
                        binding.email.getEditText().getText().toString(),
                        binding.password.getEditText().getText().toString()
                ).addOnSuccessListener(authResult -> {
                    UserModel userModel = new UserModel(
                            Constants.auth().getCurrentUser().getUid(),
                            binding.name.getEditText().getText().toString(),
                            age,
                            binding.male.isChecked() ? "Male" : "Female",
                            binding.address.getEditText().getText().toString(),
                            binding.email.getEditText().getText().toString(),
                            binding.password.getEditText().getText().toString()
                    );
                    Constants.databaseReference().child(Constants.USER).child(Constants.auth().getCurrentUser().getUid())
                            .setValue(userModel).addOnSuccessListener(unused -> {
                                Constants.dismissDialog();
                                startActivity(new Intent(this, DashboardActivity.class));
                                finish();
                            }).addOnFailureListener(e -> {
                                Constants.dismissDialog();
                                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

    }

    private boolean valid() {
        if (binding.name.getEditText().getText().toString().isEmpty()){
            binding.name.getEditText().setError("Name is required");
            return false;
        }
        if (binding.address.getEditText().getText().toString().isEmpty()){
            binding.address.getEditText().setError("Name is required");
            return false;
        }
        if (binding.email.getEditText().getText().toString().isEmpty()){
            binding.email.getEditText().setError("Email is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getEditText().getText().toString()).matches()){
            binding.email.getEditText().setError("Email is not valid");
            return false;
        }
        if (binding.password.getEditText().getText().toString().isEmpty()){
            binding.password.getEditText().setError("Password is required");
            return false;
        }
        if (binding.rePassword.getEditText().getText().toString().isEmpty()){
            binding.rePassword.getEditText().setError("Password is required");
            return false;
        }
        if (!binding.password.getEditText().getText().toString().equals(binding.rePassword.getEditText().getText().toString())){
            binding.rePassword.getEditText().setError("Password is not match");
            return false;
        }
        return true;
    }
}