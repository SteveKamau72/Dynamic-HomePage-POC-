package com.e.dynamichomepagepoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SlidingImagesAdapter extends RecyclerView.Adapter<SlidingImagesAdapter.MyViewHolder> {
    public Context context;
    private List<Section> sectionList;

    public SlidingImagesAdapter(List<Section> sections, Context context) {
        this.context = context;
        sectionList = sections;
    }

    @NonNull
    @Override
    public SlidingImagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View theView = LayoutInflater.from(context).inflate(R.layout.sliding_image_item, parent, false);
        return new SlidingImagesAdapter.MyViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull SlidingImagesAdapter.MyViewHolder holder, final int position) {

        Glide.with(context)
                .load(sectionList.get(position).getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();

    }

    public void setData(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
