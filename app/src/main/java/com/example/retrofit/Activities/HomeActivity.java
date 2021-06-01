package com.example.retrofit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.retrofit.NavFragments.DashboardFragment;
import com.example.retrofit.NavFragments.ProfileFragment;
import com.example.retrofit.NavFragments.UserFragment;
import com.example.retrofit.R;
import com.example.retrofit.SharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragments(new DashboardFragment());
        sharedPreference = new SharedPreference(getApplicationContext());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logoutmenu , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                logOutUser();
                break;
            case R.id.deleteAccnt:
                deleteaccnt();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    private void deleteaccnt() {
    }

    private void logOutUser() {

        sharedPreference.logOut();
        Intent intent = new Intent(HomeActivity.this,loginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(this, "You Have Been Logged Out", Toast.LENGTH_SHORT).show();

    }
}