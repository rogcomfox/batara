package com.nusantarian.batara.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.nusantarian.batara.R;
import com.nusantarian.batara.activity.MainActivity;
import com.nusantarian.batara.databinding.FragmentLoginBinding;
import com.nusantarian.batara.model.LoginResponse;
import com.nusantarian.batara.model.User;
import com.nusantarian.batara.service.RetrofitClient;
import com.nusantarian.batara.service.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private SharedPref session;
    private FragmentLoginBinding binding;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        session = new SharedPref(getContext());
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.btnLogin.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);
        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                binding.progress.setVisibility(View.VISIBLE);
                String username = Objects.requireNonNull(binding.tilUsername.getEditText()).getText().toString();
                String pass = Objects.requireNonNull(binding.tilPass.getEditText()).getText().toString();
                if (!isValid(username, pass)) {
                    binding.progress.setVisibility(View.GONE);
                } else {
                    Call<LoginResponse> call = RetrofitClient
                            .getInstance().getApi().userLogin(username, pass);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse res = null;
                            String message = null;
                            User user = null;
                            try {
                                if (response.code() == 200) {
                                    res = response.body();
                                    String token = res.getToken();
                                    user = res.getUser();
                                    session.saveUser(user);
                                    session.saveToken(token);
                                    Log.d("TAG", "onResponse: "+session.getToken());
                                    Log.d("TAG", "onResponse: "+session.getUser().getFullName());
                                    message = res.getMessage();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    message = Objects.requireNonNull(response.errorBody()).string();
                                }
                                binding.progress.setVisibility(View.GONE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (message != null){
                                try {
                                    JSONObject json = new JSONObject(message);
                                    Toast.makeText(getActivity(), json.getString("message"), Toast.LENGTH_LONG)
                                            .show();
                                } catch (JSONException e){
                                    e.printStackTrace();
                                }
                                binding.progress.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            binding.progress.setVisibility(View.GONE);
                        }
                    });
                }
                break;
            case R.id.btn_register:
                ft.replace(R.id.frame_auth, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    private boolean isValid(String username, String pass) {
        String empty = Objects.requireNonNull(getActivity()).getResources().getString(R.string.text_empty);
        if (username.isEmpty() || pass.isEmpty()) {
            binding.tilUsername.setError(empty);
            binding.tilPass.setError(empty);
            return false;
        } else {
            binding.tilUsername.setError(null);
            binding.tilPass.setError(null);
            binding.tilUsername.isErrorEnabled();
            binding.tilPass.isErrorEnabled();
            return true;
        }
    }
}