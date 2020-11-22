package com.example.azorafinaltest;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.azorafinaltest.ui.dashboard.DashboardFragment;
import com.example.azorafinaltest.ui.home.HomeFragment;
import com.example.azorafinaltest.ui.DataBaseManager.DataBaseManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new DashboardFragment();
    final Fragment fragment3 = new DataBaseManager();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(fragment1);
                    active = fragment1;
                    return true;
                case R.id.navigation_dashboard:
                    loadFragment(fragment2);
                    active = fragment2;
                    return true;
                case R.id.navigation_notifications:
                    loadFragment(fragment3);
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            fm.beginTransaction().hide(active).show(fragment).commit();
            return true;
        }
        return false;
    }
}