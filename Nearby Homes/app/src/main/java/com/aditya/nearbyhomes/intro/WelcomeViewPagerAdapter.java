package com.aditya.nearbyhomes.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.aditya.nearbyhomes.R;


public class WelcomeViewPagerAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;

    public WelcomeViewPagerAdapter(Context context) {
        this.context = context;
    }

    public int[] image1_list = {R.drawable.wlc1,R.drawable.wlc2, R.drawable.wlc4};


public String[] text_list = {"Homes at reasonable price\nin your locality",
                            "Find your home at\none touch",
                            "Houses in high priority area\n in your city"};




    @Override
    public int getCount() {
        return image1_list.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView img = view.findViewById(R.id.img);
        TextView text = view.findViewById(R.id.text);

text.setText(text_list[position]);
        img.setImageResource(image1_list[position]);







        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }


//    ViewPager.
}
