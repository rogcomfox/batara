package com.nusantarian.batara.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nusantarian.batara.R;
import com.nusantarian.batara.databinding.FragmentRegisterBinding;
import com.nusantarian.batara.service.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        binding.btnRegister.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register){
            binding.progress.setVisibility(View.VISIBLE);
            String email = Objects.requireNonNull(binding.tilEmail.getEditText()).getText().toString();
            String name = Objects.requireNonNull(binding.tilName.getEditText()).getText().toString();
            String username = Objects.requireNonNull(binding.tilUsername.getEditText()).getText().toString();
            String pass = Objects.requireNonNull(binding.tilPass.getEditText()).getText().toString();
            String confpass = Objects.requireNonNull(binding.tilConfirmPass.getEditText()).getText().toString();
            if (!isValid(email, name, username, pass, confpass)){
                binding.progress.setVisibility(View.GONE);
            } else {
                Call<ResponseBody> call = RetrofitClient
                        .getInstance().getApi().createUser(name, email, username, pass);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String message = null;
                        try {
                            if (response.code() == 200) {
                                message = Objects.requireNonNull(response.body()).string();
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
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.progress.setVisibility(View.GONE);
                    }
                });
            }
        }
    }

    private boolean isValid(String email, String name, String username, String pass, String confpass){
        String empty = Objects.requireNonNull(getActivity()).getResources().getString(R.string.text_empty);
        String invalid = getActivity().getResources().getString(R.string.text_invalid_email);
        String notMatch = getActivity().getResources().getString(R.string.text_pass_not_match);
        if (email.isEmpty() || pass.isEmpty() || name.isEmpty() || username.isEmpty() || confpass.isEmpty()){
            binding.tilEmail.setError(empty);
            binding.tilPass.setError(empty);
            binding.tilName.setError(empty);
            binding.tilUsername.setError(empty);
            binding.tilConfirmPass.setError(empty);
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmail.setError(invalid);
            return false;
        }
        else if (!pass.equals(confpass)){
            binding.tilPass.setError(notMatch);
            binding.tilConfirmPass.setError(notMatch);
            return false;
        }
        else {
            binding.tilEmail.setError(null);
            binding.tilPass.setError(null);
            binding.tilConfirmPass.setError(null);
            binding.tilUsername.setError(null);
            binding.tilName.setError(null);

            binding.tilEmail.isErrorEnabled();
            binding.tilPass.isErrorEnabled();
            binding.tilConfirmPass.isErrorEnabled();
            binding.tilEmail.isErrorEnabled();
            binding.tilName.isErrorEnabled();
            return true;
        }
    }
}