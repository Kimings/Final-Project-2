package com.hacktiv8.tokoku.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hacktiv8.tokoku.R;
import com.hacktiv8.tokoku.databinding.ActivityHomeAdminBinding;
import com.hacktiv8.tokoku.preferences.UserPrefence;

public class Home_Admin extends AppCompatActivity {

    private ActivityHomeAdminBinding binding;
    private UserPrefence prefence;
    private boolean isLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        prefence = new UserPrefence(this);
        if (user != null) {
            isLogin = true;
            binding.username.setText("Hello, "+prefence.getUserPref().getUsername());
        } else {
            isLogin = false;
            finish();
        }

        binding.btnStaff.setOnClickListener(v -> {

        });
    }
}