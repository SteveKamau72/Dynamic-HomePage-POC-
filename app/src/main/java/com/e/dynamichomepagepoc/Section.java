package com.e.dynamichomepagepoc;

import com.google.gson.annotations.SerializedName;

public class Section {
    @SerializedName("image")
    private String image;
    @SerializedName("title")
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
