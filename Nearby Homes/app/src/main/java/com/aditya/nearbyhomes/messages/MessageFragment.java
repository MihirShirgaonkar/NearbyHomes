package com.aditya.nearbyhomes.messages;

import android.content.Context;
import android.content.SharedPreferences;
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


public class MessageFragment extends Fragment {


    public MessageFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    CustomProgressDialogue progressDialogue;
List<MSGConnectionModel > list;
    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_message, container, false);

        recyclerView=v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressDialogue=new CustomProgressDialogue(getContext());
        progressDialogue.show();
        registrationPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();


        list=new ArrayList<>();

        RetrofitInterface retrofitInterface = RetrofitInstance.getSeviceData();

        retrofitInterface.getMSG_Connection().enqueue(new Callback<List<MSGConnectionModel>>() {
            @Override
            public void onResponse(Call<List<MSGConnectionModel>> call, Response<List<MSGConnectionModel>> response) {
                if (response.isSuccessful()){


                    String User_Name = registrationPreferences.getString("User_Name","NULL");
                    String User_Id2 = registrationPreferences.getString("User_Id","NULL");
                    for (MSGConnectionModel n :response.body()){
                        if (n.getUser_Id().equals(User_Id2)){
                            list.add(n);
                        }
                    }

                    recyclerView.setAdapter(new MessageAdapter(list,getContext()));


                    progressDialogue.dismiss();
                }
                progressDialogue.dismiss();
            }

            @Override
            public void onFailure(Call<List<MSGConnectionModel>> call, Throwable t) {
progressDialogue.dismiss();
            }
        });

       return v;
    }
}