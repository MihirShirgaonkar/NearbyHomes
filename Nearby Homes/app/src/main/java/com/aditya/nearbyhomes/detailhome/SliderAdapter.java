package com.aditya.nearbyhomes.detailhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.aditya.nearbyhomes.R;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;



public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {

    List<String> imageList;
    Context context;

    public SliderAdapter(List<String> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item_slider,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        String n = imageList.get(position);


//
        try {

        Picasso.with(context).load(n).into(viewHolder.imageView);
        }catch (Exception   e){

        }

    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    class SliderViewHolder extends  SliderViewAdapter.ViewHolder {

        ImageView imageView;

        public SliderViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);

        }
    }
}
