package com.hacktiv8.tokoku.ui.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktiv8.tokoku.R;
import com.hacktiv8.tokoku.databinding.ActivityEmailFragmentBinding;

public class EmailFragment extends AppCompatActivity {

    private ActivityEmailFragmentBinding binding;

    public EmailFragment(){

    }

    public View onCreate(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ActivityEmailFragmentBinding.inflate(inflater, container, false);

        binding.emailEwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"khonsa.fathimah@gmail.com"});
                startActivity(Intent.createChooser(email, "Send mail"));
            }
        });

        binding.emailfifit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"Rafidany21@gmail.com"});
                startActivity(Intent.createChooser(email, "Send mail"));
            }
        });

        binding.emailRifqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"syaifunnadhif@gmail.com"});
                startActivity(Intent.createChooser(email, "Send email"));
            }
        });

        return binding.getRoot();
    }
}