package com.nusantarian.batara.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nusantarian.batara.R;
import com.nusantarian.batara.databinding.FragmentFlashCardBinding;

import java.util.Objects;

public class FlashCardFragment extends Fragment implements View.OnClickListener {

    private FragmentFlashCardBinding binding;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFlashCardBinding.inflate(inflater, container, false);
        binding.cardAnimals.setOnClickListener(this);
        binding.cardDaily.setOnClickListener(this);
        binding.cardFood.setOnClickListener(this);
        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_food:
                ft.replace(R.id.frame_main, new FoodFlashCard())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.card_animals:
                Toast.makeText(getActivity(), "FlashCard for Animal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_daily:
                Toast.makeText(getActivity(), "FlashCard for Daily Conversation", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}