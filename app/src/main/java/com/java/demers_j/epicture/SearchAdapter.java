package com.java.demers_j.epicture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

class SearchAdapter extends BaseAdapter {
    private List<String> imageList;
    private LayoutInflater mInflater;

    SearchAdapter(Context context, List<String> imageList) {
        this.imageList = imageList;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (position <= 0 && position > imageList.size()) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_image, parent, false);
                viewHolder = new ViewHolder(convertView);
            } else {
                convertView = mInflater.inflate(R.layout.item_image, parent, false);
                viewHolder = new ViewHolder(convertView);
            }
            System.out.println("position = " + position + "  string = " + imageList.get(position));
            if (imageList.get(position).equals("7"))
                viewHolder.imageView.setImageResource(R.drawable.flickr_logo);
            else
                viewHolder.imageView.setImageResource(R.drawable.imgur_logo);
            return convertView;
        }
        return null;
    }

    private class ViewHolder {
        ImageView imageView;

        ViewHolder(View view) {
            this.imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}
