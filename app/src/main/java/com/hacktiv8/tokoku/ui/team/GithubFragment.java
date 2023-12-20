package com.hacktiv8.tokoku.ui.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktiv8.tokoku.R;
import com.hacktiv8.tokoku.databinding.ActivityGithubFragmentBinding;

public class GithubFragment extends AppCompatActivity {

    private ActivityGithubFragmentBinding binding;

    public GithubFragment(){

    }

    public View onCreate(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        binding = ActivityGithubFragmentBinding.inflate(inflater, container, false);

        binding.githubEwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openUrl("https://github.com/FathimahKhonsa"); }
        });

        binding.githubfifit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openUrl("https://github.com/RadRasyad"); }
        });

        binding.githubRifqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openUrl("https://github.com/SyaifunNadhif"); }
        });
        return binding.getRoot();
    }

    private void openUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}