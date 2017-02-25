package com.java.demers_j.epicture;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private Dialog dialog;
    private Context context;
    ImageView countryPhoto;

    ViewHolders(View itemView, Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        countryPhoto = (ImageView) itemView.findViewById(R.id.image);
        countryPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
        System.out.println("width = " + itemView.getWidth() + "  height = " + itemView.getHeight());
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Position = " + getItemId(), Toast.LENGTH_SHORT).show();
        if (dialog != null && dialog.isShowing())
            return;
        dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_image_zoom);

        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        try {
            if (dialog.getWindow() != null)
                lp.copyFrom(dialog.getWindow().getAttributes());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ImageView imageZoom = (ImageView) dialog.findViewById(R.id.expanded_image);
        imageZoom.setImageResource(R.drawable.imgur_logo);
               /* Picasso.with(context)
                        .load(getIntent().getStringExtra("ImageProduit"))
                        .into(imageZoom);*/
        imageZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        if (dialog != null && dialog.isShowing())
            return false;
        dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_image_long_click);

        dialog.setCancelable(true);

        dialog.show();
        Button buttonDownload = (Button) dialog.findViewById(R.id.buttonDownload);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Download in progress " + getItemId(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Button buttonFavorite = (Button) dialog.findViewById(R.id.buttonFavorite);
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Favorite in progress " + getItemId(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        return false;
    }
}
