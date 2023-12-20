package com.hacktiv8.tokoku.ui.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktiv8.tokoku.R;
import com.hacktiv8.tokoku.databinding.ActivityIgFragmentBinding;

public class IgFragment extends AppCompatActivity {

    private ActivityIgFragmentBinding binding;

    public IgFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ActivityIgFragmentBinding.inflate(inflater, container, false);

        binding.igEwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/fkhonsaa/");}
        });

        binding.igfifit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/dan_rsy/");}
        });

        binding.igRifqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/syaifun_/");}
        });
        return binding.getRoot();
    }

    private void openUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}