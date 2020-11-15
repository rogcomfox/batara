package com.nusantarian.batara.data;

import com.nusantarian.batara.model.MyCourse;

import java.util.ArrayList;

public class CourseData {
    private static String[] name = {
            "Kajembaran Basa",
            "Cara Cepat Ngomong Sunda",
            "Ngamumule Basa Sunda"
    };
    private static String[] level = {
            "Easy",
            "Intermediate",
            "Advance"
    };

    public static ArrayList<MyCourse> getListData(){
        ArrayList<MyCourse> list = new ArrayList<>();
        for (int position = 0; position < name.length; position++){
            MyCourse course = new MyCourse();
            course.setName(name[position]);
            course.setLevel(level[position]);
            list.add(course);
        }
        return list;
    }
}
