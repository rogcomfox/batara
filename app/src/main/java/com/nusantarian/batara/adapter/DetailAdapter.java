package com.nusantarian.batara.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nusantarian.batara.R;
import com.nusantarian.batara.model.DetailModel;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ListViewHolder>{
    private ArrayList<DetailModel> listDetail;

    public DetailAdapter(ArrayList<DetailModel> listDetail) {
        this.listDetail = listDetail;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_detail, parent, false);
        return  new ListViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final DetailModel detailModel = listDetail.get(position);
        holder.tvTitle.setText(detailModel.getJudul());
        holder.tvDesc.setText(detailModel.getDesc());

    }

    @Override
    public int getItemCount() {
        return listDetail.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.desc_detail_course);
            tvTitle = itemView.findViewById(R.id.title_detail_course);
        }
    }
}
