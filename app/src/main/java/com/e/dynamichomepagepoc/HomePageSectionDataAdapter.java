package com.e.dynamichomepagepoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomePageSectionDataAdapter extends RecyclerView.Adapter<HomePageSectionDataAdapter.MyViewHolder> {
    public Context context;
    private List<Section> sectionList;

    public HomePageSectionDataAdapter(List<Section> sections, Context context) {
        this.context = context;
        sectionList = sections;
    }

    @NonNull
    @Override
    public HomePageSectionDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View theView = LayoutInflater.from(context).inflate(R.layout.section_item, parent, false);

        return new HomePageSectionDataAdapter.MyViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageSectionDataAdapter.MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(sectionList.get(position).getImage())
                .into(holder.imageView);
        holder.tvTitle.setText(sectionList.get(position).getTitle());
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
        private TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            tvTitle = itemView.findViewById(R.id.title);
        }
    }
}

