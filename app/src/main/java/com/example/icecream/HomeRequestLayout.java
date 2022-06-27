package com.example.icecream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.icecream.ui.home.HomeFragment;

public class HomeRequestLayout extends AppCompatActivity {

    HomeFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_request_layout);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.request_layout, fragment).commit();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);

        fragment.setArguments(bundle);
    }
}