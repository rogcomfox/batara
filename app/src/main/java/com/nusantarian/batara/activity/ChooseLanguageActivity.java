package com.nusantarian.batara.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.nusantarian.batara.R;
import com.nusantarian.batara.adapter.CardViewLanguageAdapter;
import com.nusantarian.batara.model.Language;
import com.nusantarian.batara.service.Api;
import com.nusantarian.batara.service.RetrofitClient;
import com.nusantarian.batara.service.SharedPref;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseLanguageActivity extends AppCompatActivity {

    private CardViewLanguageAdapter adapter;
    private RecyclerView recyclerView;
    List<Language> languageList;
    private Api api;
    private SharedPref session;
    private Language language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        session = new SharedPref(getBaseContext());

        languageList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_choose_language);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CardViewLanguageAdapter(getApplicationContext(), languageList);
        recyclerView.setAdapter(adapter);

        String token = session.getToken();
        String name = language.getName();

        Log.d("TAG", "onClick: "+token);
        Api api = RetrofitClient.getInstance().getApi();
        Call<List<Language>> call= api.getAllLanguage("bearer "+token);

        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if(response.code() == 200){
                    // Sudah bisa dapat dan masuk ke languageList
                    languageList = response.body();
                } else {
                    Log.d("TAG", "onResponse: "+ response.code());
                    try {
                        Log.d("TAG", "onResponse: "+ response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {
            }
        });
    }
}
