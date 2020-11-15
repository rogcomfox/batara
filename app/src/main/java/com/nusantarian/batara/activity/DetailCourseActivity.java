package com.nusantarian.batara.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.nusantarian.batara.R;
import com.nusantarian.batara.adapter.DetailAdapter;
import com.nusantarian.batara.data.DetailData;
import com.nusantarian.batara.model.DetailModel;

import java.util.ArrayList;

public class DetailCourseActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvDetail;
    private ArrayList<DetailModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        rvDetail = findViewById(R.id.rv_topic_course);
        rvDetail.setHasFixedSize(true);

        list.addAll(DetailData.getListData());
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        DetailAdapter listDetailAdapter = new DetailAdapter(list);
        rvDetail.setAdapter(listDetailAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}