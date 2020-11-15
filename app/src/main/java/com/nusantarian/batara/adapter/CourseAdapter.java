package com.nusantarian.batara.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nusantarian.batara.R;
import com.nusantarian.batara.activity.DetailCourseActivity;
import com.nusantarian.batara.model.MyCourse;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CardViewViewHolder> {
    private ArrayList<MyCourse> list_course;

    public CourseAdapter(ArrayList<MyCourse> list_course) {
        this.list_course = list_course;
    }

    @NonNull
    @Override
    public CourseAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CardViewViewHolder holder, int position) {
        MyCourse myCourse = list_course.get(position);

        holder.tvTitle.setText(myCourse.getName());
        holder.tvLevel.setText(myCourse.getLevel());
        holder.btn_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailCourseActivity.class);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_course.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLevel;
        Button btn_course;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title_course_home);
            tvLevel = itemView.findViewById(R.id.tv_level_course_home);
            btn_course = itemView.findViewById(R.id.btn_course);
        }
    }
}
