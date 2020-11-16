package com.nusantarian.batara.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nusantarian.batara.R;
import com.nusantarian.batara.databinding.FragmentGrammarBinding;

public class GrammarFragment extends Fragment {

    private FragmentGrammarBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGrammarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}