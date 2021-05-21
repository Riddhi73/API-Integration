package com.example.retrofit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.retrofit.NavFragments.DashboardFragment;
import com.example.retrofit.NavFragments.ProfileFragment;
import com.example.retrofit.NavFragments.UserFragment;
import com.example.retrofit.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.users:
                fragment = new UserFragment();
                break;
            case R.id.profile:
                fragment = new ProfileFragment();
                break;

        }
        if (fragment!=null){
            loadFragments(fragment);

        }
        return true;
    }


    void loadFragments(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout,fragment).commit();

    }
}