package com.java.demers_j.epicture;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView countryPhoto;

    ViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryPhoto = (ImageView) itemView.findViewById(R.id.image);
        countryPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
        System.out.println("width = "+ itemView.getWidth()+"  height = "+itemView.getHeight());
  }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Position = " + getItemId(), Toast.LENGTH_SHORT).show();
    }

}