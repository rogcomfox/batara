package com.nusantarian.batara.responses;

import com.nusantarian.batara.model.Language;

import java.util.ArrayList;

public class LanguageResponses {
    private boolean success;
    private String message;
    private ArrayList<Language> data;

    public LanguageResponses(boolean success, String message, ArrayList<Language> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Language> getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(ArrayList<Language> data) {
        this.data = data;
    }
}
