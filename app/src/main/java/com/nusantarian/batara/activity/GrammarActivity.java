package com.nusantarian.batara.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.nusantarian.batara.databinding.ActivityGrammarBinding;

public class GrammarActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private ActivityGrammarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGrammarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        initGrammarFragment();
    }

    private void initGrammarFragment(){

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
    public boolean onNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}