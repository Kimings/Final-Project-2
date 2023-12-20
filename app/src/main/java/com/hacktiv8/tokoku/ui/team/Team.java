package com.hacktiv8.tokoku.ui.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.hacktiv8.tokoku.R;
import com.hacktiv8.tokoku.databinding.ActivityTeamBinding;

public class Team extends AppCompatActivity {

    private ActivityTeamBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openInstagramFragment(); }
        });

        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openEmailFragment(); }
        });

        binding.github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openGithubFragment(); }
        });
    }

    private void openGithubFragment() {
        GithubFragment fragment = new GithubFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.commit();
    }

    private void openEmailFragment() {
        EmailFragment fragment = new EmailFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.commit();
    }

    private void openInstagramFragment() {
     IgFragment fragment = new IgFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.commit();
    }
}