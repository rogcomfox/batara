package com.nusantarian.batara.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nusantarian.batara.R;
import com.nusantarian.batara.model.Language;

import java.util.List;

public class CardViewLanguageAdapter extends RecyclerView.Adapter<CardViewLanguageAdapter.CardViewViewHolder>{
    private List<Language> list_language;
    private Context context;

    public CardViewLanguageAdapter(Context context, List<Language> list_language) {
        this.list_language = list_language;
        this.context = context;
    }

    public void setCardViewLanguageAdapter(List<Language> languageList) {
        this.list_language = languageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewLanguageAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_language, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewLanguageAdapter.CardViewViewHolder holder, int position) {
        Language language = list_language.get(position);

        holder.tvLanguage.setText(language.getName());
    }

    @Override
    public int getItemCount() {
        if (list_language!=null){
            return list_language.size();
        }
        return 0;
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvLanguage;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLanguage = itemView.findViewById(R.id.tv_language);

        }
    }
}


