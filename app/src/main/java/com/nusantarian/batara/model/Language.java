package com.nusantarian.batara.model;

import com.google.gson.annotations.SerializedName;

public class Language {
    @SerializedName("name")
    private String name;

    public Language(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
