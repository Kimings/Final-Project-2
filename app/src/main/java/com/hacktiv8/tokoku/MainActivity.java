package com.hacktiv8.tokoku;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktiv8.tokoku.databinding.ActivityMainBinding;
import com.hacktiv8.tokoku.model.User;
import com.hacktiv8.tokoku.preferences.UserPrefence;
import com.hacktiv8.tokoku.ui.admin.Home_Admin;
import com.hacktiv8.tokoku.ui.admin.Login_Admin;
import com.hacktiv8.tokoku.ui.staff.Staff_Home;
import com.hacktiv8.tokoku.ui.staff.Staff_Login;
import com.hacktiv8.tokoku.ui.team.Team;
import com.hacktiv8.tokoku.ui.user.Dasboard;
import com.hacktiv8.tokoku.ui.user.Register_User;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private UserPrefence prefence;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        prefence = new UserPrefence(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        binding.btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Register_User.class);
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(v -> {
            loginUser();
        });

        binding.btnAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Login_Admin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intent);
        });

        binding.btnStaff.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Staff_Login.class);
            startActivity(intent);
        });

        binding.btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Team.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = binding.inputUsername.getText().toString().trim();
        String password = binding.inputPassword.getText().toString().trim();

        try {
            databaseReference.child(username).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean isExcist = snapshot.exists();
                    if(isExcist) {
                        User user = snapshot.getValue(User.class);
                        if(user.getRole().equals("2")){
                            mAuth.signInWithEmailAndPassword(user.getEmail(), password)
                                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                User toSave = new User();
                                                toSave.setuId(user.getuId());
                                                toSave.setUsername(user.getUsername());
                                                toSave.setEmail(user.getEmail());
                                                toSave.setPhone(user.getPhone());
                                                toSave.setRole(user.getRole());
                                                String role = user.getRole();
                                                saveUser(toSave);
                                                reload(role);

                                            } else {
                                                Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                                                Toast.makeText(MainActivity.this, "Password Salah",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(MainActivity.this, "Anda Bukan User", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this,"Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this,"Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            if (currentUser.getUid().equals(prefence.getUserPref().getuId())) {
                reload(prefence.getUserPref().getRole());
            } else {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Session telah habis", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reload(String role) {
        if ("0".equals(role)) {
            Intent intent = new Intent(MainActivity.this, Home_Admin.class);
            startActivity(intent);
        } else if ("1".equals(role)) {
            Intent intent = new Intent(MainActivity.this, Staff_Home.class);
            startActivity(intent);
        } else if ("2".equals(role)) {
            Intent intent = new Intent(MainActivity.this, Dasboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private void saveUser(User user) {
        UserPrefence userPreference = new UserPrefence(MainActivity.this);
        userPreference.setUserPref(user);
    }
}