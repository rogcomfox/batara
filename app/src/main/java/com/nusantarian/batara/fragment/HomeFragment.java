package com.nusantarian.batara.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nusantarian.batara.R;
import com.nusantarian.batara.activity.ChooseLanguageActivity;
import com.nusantarian.batara.adapter.CourseAdapter;
import com.nusantarian.batara.adapter.LatestCourseAdapter;
import com.nusantarian.batara.data.CourseData;
import com.nusantarian.batara.data.LatestCourseData;
import com.nusantarian.batara.databinding.FragmentHomeBinding;
import com.nusantarian.batara.model.LatestCourse;
import com.nusantarian.batara.model.MyCourse;
import com.nusantarian.batara.model.User;
import com.nusantarian.batara.service.SharedPref;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {
    RelativeLayout choose_language;
    private RecyclerView rvCourse, rvLatest;
    private ArrayList<MyCourse> list = new ArrayList<>();
    private ArrayList<LatestCourse> list2 = new ArrayList<>();

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        User user = SharedPref.getInstance(getActivity()).getUser();
        binding.tvNameHome.setText("Hello, " + user.getFullName());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        choose_language = view.findViewById(R.id.choose_language_home);
        choose_language.setOnClickListener(this);

        rvCourse = view.findViewById(R.id.rv_course_home);
        rvLatest = view.findViewById(R.id.rv_latest_course_home);
        rvCourse.setHasFixedSize(true);
        rvLatest.setHasFixedSize(true);

        list.addAll(CourseData.getListData());
        rvCourse.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        CourseAdapter courseAdapter = new CourseAdapter(list);
        rvCourse.setAdapter(courseAdapter);

        list2.addAll(LatestCourseData.getListData());
        rvLatest.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        LatestCourseAdapter latestAdapter = new LatestCourseAdapter(list2);
        rvLatest.setAdapter(latestAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_language_home:
                Intent a = new Intent(getContext(), ChooseLanguageActivity.class);
                startActivity(a);
                break;
        }
    }
}