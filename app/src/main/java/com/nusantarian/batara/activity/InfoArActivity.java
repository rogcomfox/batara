package com.nusantarian.batara.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nusantarian.batara.R;
import com.nusantarian.batara.databinding.ActivityInfoArBinding;

public class InfoArActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityInfoArBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoArBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgIcon.setClipToOutline(true);
        binding.btnBack.setOnClickListener(this);
        binding.btnGoToCourse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.btn_go_to_course:
                Toast.makeText(this, "Course Accepted", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}