package com.example.mobile_exploremada.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mobile_exploremada.models.ImageModel;
import com.example.mobile_exploremada.utils.Credentials;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<ImageModel> imageDataList;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setImageDataList(List<ImageModel> imageDataList) {
        this.imageDataList = imageDataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imageDataList != null ? imageDataList.size() : 0;
    }

    @Override
    public ImageModel getItem(int position) {
        return imageDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return imageDataList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(400,400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // Ajuste l'échelle de l'image pour remplir complètement l'ImageView
        } else {
            imageView = (ImageView) convertView;
        }

        // Utilisez Glide pour charger l'image dans l'ImageView
        Glide.with(context)
                .load(Credentials.BASE_URL + "uploads/lieu/" + imageDataList.get(position).getImage())
                .into(imageView);

        return imageView;
    }
}
