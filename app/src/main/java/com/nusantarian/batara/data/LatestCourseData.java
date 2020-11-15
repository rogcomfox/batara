package com.nusantarian.batara.data;

import com.nusantarian.batara.R;
import com.nusantarian.batara.model.LatestCourse;

import java.util.ArrayList;

public class LatestCourseData {
    private static int[] photo = {
            R.drawable.cover_grammar,
            R.drawable.cover_poetry,
            R.drawable.cover_daily_term,
    };

    private static String[] title = {
            "Grammar",
            "Poetry",
            "Daily-Term"

    };

    public static ArrayList<LatestCourse> getListData(){
        ArrayList<LatestCourse> list = new ArrayList<>();
        for (int position = 0; position < title.length; position++){
            LatestCourse latestCourse = new LatestCourse();
            latestCourse.setTitle(title[position]);
            latestCourse.setPhoto(photo[position]);
            list.add(latestCourse);
        }
        return list;
    }
}
