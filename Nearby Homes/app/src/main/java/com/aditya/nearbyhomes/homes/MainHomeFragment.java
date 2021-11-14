package com.aditya.nearbyhomes.homes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.nearbyhomes.R;
import com.google.android.material.tabs.TabLayout;


public class MainHomeFragment extends Fragment {


    public MainHomeFragment() {
        // Required empty public constructor
    }


    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_main_home, container, false);



        tabLayout=v.findViewById(R.id.tablayout);
        viewPager=v.findViewById(R.id.viewpager);


        viewPager.setAdapter(new FragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }
}