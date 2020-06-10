package com.e.dynamichomepagepoc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kingfisher.easyviewindicator.RecyclerViewIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.MyViewHolder> {
    public static final int VIEW_TYPE_HORIZONTAL_RECYCLERVIEW = 1;
    public static final int VIEW_TYPE_VIEWPAGER = 2;
    public static final int VIEW_TYPE_SINGLE = 3;
    private HomePageSectionDataAdapter homePageSectionDataAdapter;
    private SlidingImagesAdapter slidingImagesAdapter;
    private RecyclerView.RecycledViewPool recycledViewPool;
    public List<DataSections> dataSections;
    public Context context;

    public HomePageAdapter(Context context) {
        this.context = context;
        dataSections = new ArrayList<>();
        this.recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        if (dataSections.get(position).getView_type().equals("horizontal_recylerview")) {
            return VIEW_TYPE_HORIZONTAL_RECYCLERVIEW;
        } else if (dataSections.get(position).getView_type().equals("viewpager")) {
            return VIEW_TYPE_VIEWPAGER;
        } else {
            return VIEW_TYPE_SINGLE;
        }
    }

    @NonNull
    @Override
    public HomePageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View theView = null;
        if (viewType == VIEW_TYPE_HORIZONTAL_RECYCLERVIEW) {
            theView = LayoutInflater.from(context).inflate(R.layout.horizontal_recyclerview_item, parent, false);
        } else if (viewType == VIEW_TYPE_VIEWPAGER) {
            theView = LayoutInflater.from(context).inflate(R.layout.viewpager_item, parent, false);
        } else {
            theView = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        }
        return new MyViewHolder(theView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if (getItemViewType(position) == VIEW_TYPE_HORIZONTAL_RECYCLERVIEW) {
            holder.tvTitle.setText(dataSections.get(position).getSection_title());
            homePageSectionDataAdapter = new HomePageSectionDataAdapter
                    (dataSections.get(position).getSections(), context);
            holder.recyclerViewHorizontal.setAdapter(homePageSectionDataAdapter);
            holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);
        } else if (getItemViewType(position) == VIEW_TYPE_VIEWPAGER) {
            slidingImagesAdapter = new SlidingImagesAdapter(dataSections.get(position).getSections(), context);
            holder.slidingImagesRecyclerview.setAdapter(slidingImagesAdapter);
            holder.recyclerViewIndicator.setRecyclerView(holder.slidingImagesRecyclerview);
            holder.slidingImagesRecyclerview.setRecycledViewPool(recycledViewPool);
            // If you need to change the adapter size, you should call this function
            holder.recyclerViewIndicator.forceUpdateItemCount();

            holder.recyclerViewIndicator.setItemCount(dataSections.get(position).getSections().size());
            holder.recyclerViewIndicator.setCurrentPosition(0);
            holder.slidingImagesRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    switch (newState) {
                        case RecyclerView.SCROLL_STATE_IDLE:
                            int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                            holder.recyclerViewIndicator.setCurrentPosition(position);
                            break;
                    }
                }
            });

        } else {
            holder.tvSingleTitle.setText(dataSections.get(position).getSection_title());
            Log.e("onBindViewHolder: ", dataSections.get(position).getSections().toString());
            Glide.with(context).load(dataSections.get(position).getSections().get(0).getImage())
                    .into(holder.singleImageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataSections.size();

    }

    public void setData(List<DataSections> dataSections) {
        this.dataSections = dataSections;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvSingleTitle;
        public ImageView singleImageView;
        public RecyclerViewIndicator recyclerViewIndicator;
        public RecyclerView recyclerViewHorizontal, slidingImagesRecyclerview;
        private LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        public MyViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == VIEW_TYPE_HORIZONTAL_RECYCLERVIEW) {
                tvTitle = (TextView) itemView.findViewById(R.id.title);
                recyclerViewHorizontal = (RecyclerView) itemView.findViewById(R.id.horizontal_recyclerview);
                recyclerViewHorizontal.setHasFixedSize(true);
                recyclerViewHorizontal.setNestedScrollingEnabled(false);
                recyclerViewHorizontal.setLayoutManager(horizontalManager);
                recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());
            } else if (viewType == VIEW_TYPE_VIEWPAGER) {
                slidingImagesRecyclerview = (RecyclerView) itemView.findViewById(R.id.horizontal_recyclerview);
                recyclerViewIndicator = itemView.findViewById(R.id.recyclerViewIndicator);
                slidingImagesRecyclerview.setHasFixedSize(true);
                slidingImagesRecyclerview.setNestedScrollingEnabled(false);
                slidingImagesRecyclerview.setLayoutManager(horizontalManager);
                slidingImagesRecyclerview.setItemAnimator(new DefaultItemAnimator());
            } else {
                tvSingleTitle = (TextView) itemView.findViewById(R.id.title);
                singleImageView = (ImageView) itemView.findViewById(R.id.image_view);
            }
        }
    }
}


