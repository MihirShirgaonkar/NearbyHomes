package com.aditya.nearbyhomes.homes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.nearbyhomes.CustomProgressDialogue;
import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInstance;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreFragment extends Fragment {


    public ExploreFragment() {
        // Required empty public constructor
    }

    List<HomeModel> list;
RecyclerView recyclerView;
    RetrofitInterface retrofitInterface;

CustomProgressDialogue progressDialogue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_explore, container, false);



        recyclerView=v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        retrofitInterface= RetrofitInstance.getSeviceData();
        progressDialogue=new CustomProgressDialogue(getContext());
        progressDialogue.show();
        list=new ArrayList<>();

        retrofitInterface.getRoomsData().enqueue(new Callback<List<HomeModel>>() {
            @Override
            public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                if (response.isSuccessful()){
                    list=response.body();
                    recyclerView.setAdapter(new HomesAdapter(getContext(),list));
                }
                progressDialogue.dismiss();
            }

            @Override
            public void onFailure(Call<List<HomeModel>> call, Throwable t) {
progressDialogue.dismiss();
            }
        });



        return v;
    }
}