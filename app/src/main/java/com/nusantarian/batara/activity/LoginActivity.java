package com.nusantarian.batara.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.nusantarian.batara.R;
import com.nusantarian.batara.databinding.ActivityLoginBinding;
import com.nusantarian.batara.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        initMainFragment();

    }

    private void initMainFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_auth, new LoginFragment())
                .commit();
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

    @Override
    public void onBackStackChanged() {

    }
}