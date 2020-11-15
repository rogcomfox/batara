package com.nusantarian.batara.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nusantarian.batara.R;
import com.nusantarian.batara.databinding.ActivityMainBinding;
import com.nusantarian.batara.fragment.FlashCardFragment;
import com.nusantarian.batara.fragment.HomeFragment;
import com.nusantarian.batara.fragment.MyCourseFragment;
import com.nusantarian.batara.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, FragmentManager.OnBackStackChangedListener {

    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new MyCourseFragment();
    final Fragment fragment3 = new FlashCardFragment();
    final Fragment fragment4 = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigation.setOnNavigationItemSelectedListener(this);
        binding.floatingButton.setOnClickListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        fm.beginTransaction().add(R.id.frame_main, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.frame_main, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.frame_main, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.frame_main, fragment1, "1").commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                fm.beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
                return true;
            case R.id.nav_history:
                fm.beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
                return true;
            case R.id.nav_puzzle:
                fm.beginTransaction().hide(active).show(fragment3).commit();
                active = fragment3;
                return true;
            case R.id.nav_profile:
                fm.beginTransaction().hide(active).show(fragment4).commit();
                active = fragment4;
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floating_button){
            startActivity(new Intent(MainActivity.this, ArScanActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}