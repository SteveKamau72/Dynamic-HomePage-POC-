package com.e.dynamichomepagepoc;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataSections {
    @SerializedName("section_title")
    private String section_title;

    @SerializedName("view_type")
    private String view_type;

    @SerializedName("section_data")
    private List<Section> section_data;

    public String getSection_title() {
        return section_title;
    }

    public void setSection_title(String section_title) {
        this.section_title = section_title;
    }

    public String getView_type() {
        return view_type;
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    public List<Section> getSections() {
        return section_data;
    }

    public void setSection_data(List<Section> section_data) {
        this.section_data = section_data;
    }
}
