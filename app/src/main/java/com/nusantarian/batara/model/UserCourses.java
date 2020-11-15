package com.nusantarian.batara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCourses {
    @SerializedName("progress")
    @Expose
    private Integer progress;
    @SerializedName("isGraduate")
    @Expose
    private Boolean isGraduate;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("courseID")
    @Expose
    private String courseID;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Boolean getIsGraduate() {
        return isGraduate;
    }

    public void setIsGraduate(Boolean isGraduate) {
        this.isGraduate = isGraduate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
