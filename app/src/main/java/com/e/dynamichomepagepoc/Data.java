package com.e.dynamichomepagepoc;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("data")
    private List<DataSections> dataSections;

    public List<DataSections> getDataSections() {
        return dataSections;
    }

    public void setDataSections(List<DataSections> dataSections) {
        this.dataSections = dataSections;
    }
}
