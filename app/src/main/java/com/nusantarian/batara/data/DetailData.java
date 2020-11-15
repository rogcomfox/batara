package com.nusantarian.batara.data;

import com.nusantarian.batara.model.DetailModel;

import java.util.ArrayList;

public class DetailData {
    private static String[] title = {
            "What is unda usuk basa?",
            "What is kajembaran basa?"
    };
    private static String[] desc = {
            "Explain about introduction what is undak usuk and when we must use it.",
            "Explain about how to use kajembaran basa? How important kajembaran basa is?"
    };
    public static ArrayList<DetailModel> getListData(){
        ArrayList<DetailModel> list = new ArrayList<>();
        for (int position = 0; position < title.length; position++){
            DetailModel detail = new DetailModel();
            detail.setJudul(title[position]);
            detail.setDesc(desc[position]);
            list.add(detail);
        }
        return list;
    }
}
