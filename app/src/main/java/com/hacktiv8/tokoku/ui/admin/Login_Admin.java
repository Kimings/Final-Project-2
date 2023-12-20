package com.hacktiv8.tokoku.ui.admin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktiv8.tokoku.R;
import com.hacktiv8.tokoku.databinding.ActivityLoginAdminBinding;
import com.hacktiv8.tokoku.model.User;
import com.hacktiv8.tokoku.preferences.UserPrefence;

public class Login_Admin extends AppCompatActivity {

    private ActivityLoginAdminBinding binding;
    private UserPrefence prefence;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        prefence = new UserPrefence(this);

    binding.btnLoginAdmin.setOnClickListener(V -> {
        loginAdmin();
    });
    }

    private void loginAdmin() {
        String username = binding.inputUsername.getText().toString().trim();
        String password = binding.inputPassword.getText().toString().trim();

        try {
            mDatabase.child(username).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean isExist = snapshot.exists();
                    if (isExist) {
                        User user = snapshot.getValue(User.class);
                        if (user.getRole().equals("0")) {
                            mAuth.signInWithEmailAndPassword(user.getEmail(), password)
                                    .addOnCompleteListener(Login_Admin.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                User toSave = new User();
                                                toSave.setuId(user.getuId());
                                                toSave.setUsername(user.getUsername());
                                                toSave.setEmail(user.getEmail());
                                                toSave.setPhone(user.getPhone());
                                                toSave.setRole(user.getRole());
                                                saveUser(toSave);
                                                reload();
                                            } else {
                                                Log.w("Login_Admin", "signInWithEmail:failure", task.getException());
                                                Toast.makeText(Login_Admin.this, "Password Salah",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(Login_Admin.this, "Anda Bukan Admin", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login_Admin.this,"Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Login_Admin.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(Login_Admin.this, "Akun tidak ditemukan",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            if (currentUser.getUid().equals(prefence.getUserPref().getuId())) {
                reload();
            } else {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Login_Admin.this, "Session telah habis", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reload() {
        Intent intent = new Intent(Login_Admin.this, Home_Admin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void saveUser(User user) {
        UserPrefence userPreference = new UserPrefence(Login_Admin.this);
        userPreference.setUserPref(user);
    }

}