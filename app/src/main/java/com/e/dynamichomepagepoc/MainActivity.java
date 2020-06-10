package com.e.dynamichomepagepoc;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Data dataJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseJson();
        setUpHomePageRecycler();
    }

    private void setUpHomePageRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        HomePageAdapter homePageAdapter = new HomePageAdapter(getApplicationContext());
        homePageAdapter.setData(dataJson.getDataSections());
        recyclerView.setAdapter(homePageAdapter);

    }

    private void parseJson() {
        String data = getAssetJsonData();
        dataJson = new Gson().fromJson(data, Data.class);
        Log.e("parseJson: ", dataJson.getDataSections().get(0).getSection_title());
    }

    public String getAssetJsonData() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.js");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;

    }
}