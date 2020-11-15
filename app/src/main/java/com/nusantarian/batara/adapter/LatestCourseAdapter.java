package com.nusantarian.batara.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nusantarian.batara.R;
import com.nusantarian.batara.model.LatestCourse;

import java.util.ArrayList;

public class LatestCourseAdapter extends RecyclerView.Adapter<LatestCourseAdapter.CardViewViewHolder> {
    private ArrayList<LatestCourse> list_latest;

    public LatestCourseAdapter(ArrayList<LatestCourse> list_latest) {
        this.list_latest = list_latest;
    }

    @NonNull
    @Override
    public LatestCourseAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest_course, parent, false);
        return new LatestCourseAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestCourseAdapter.CardViewViewHolder holder, int position) {
        LatestCourse latestCourse = list_latest.get(position);

        Glide.with(holder.itemView.getContext())
                .load(latestCourse.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.photo);
        holder.tvTitle.setText(latestCourse.getTitle());


    }

    @Override
    public int getItemCount() {
        return list_latest.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView photo;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_latest_course_title);
            photo = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
