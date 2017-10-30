package com.example.rev.firebasedatabasev1.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rev.firebasedatabasev1.R;
import com.example.rev.firebasedatabasev1.adapters.MainPagerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ViewPager mainPager;
    TabLayout tabLayout;

    FirebaseDatabase fDB;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fDB = FirebaseDatabase.getInstance();
        usersRef = fDB.getReference("users");

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), usersRef);

        mainPager = findViewById(R.id.main_pager);
        mainPager.setAdapter(mainPagerAdapter);
        tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mainPager);

    }
}
