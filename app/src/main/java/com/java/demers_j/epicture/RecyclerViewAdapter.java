package com.java.demers_j.epicture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolders> {

    private List<String> itemList;
    private Context context;

    RecyclerViewAdapter(Context context, List<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        if (itemList.get(position).equals("7"))
            holder.countryPhoto.setImageResource(R.drawable.flickr_logo);
        else
            holder.countryPhoto.setImageResource(R.drawable.imgur_logo);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

}

