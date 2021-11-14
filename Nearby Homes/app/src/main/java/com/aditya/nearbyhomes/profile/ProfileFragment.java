package com.aditya.nearbyhomes.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aditya.nearbyhomes.MainActivity;
import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.intro.WelcomeActivity;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefs";

    TextView nameTv,phoneTv,pinTv,addressTv;
    Button logoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);

        registrationPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();


        String User_Name = registrationPreferences.getString("User_Name","NULL");
        String User_Id2 = registrationPreferences.getString("User_Id","NULL");
        String Pincode = registrationPreferences.getString("Pincode","NULL");
        String AddressLine = registrationPreferences.getString("AddressLine","NULL");
        String Phone = registrationPreferences.getString("Phone","NULL");


        nameTv=v.findViewById(R.id.name);
        phoneTv=v.findViewById(R.id.phone);
        pinTv=v.findViewById(R.id.pin);
        addressTv=v.findViewById(R.id.address);
        logoutBtn=v.findViewById(R.id.logout);

        nameTv.setText(User_Name);
        pinTv.setText(Pincode);
        addressTv.setText(AddressLine);
        phoneTv.setText("+91-"+Phone);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String FName, MName, LName, Email, Phone, Customer_Id, Address;

                SharedPreferences registrationPreferences;
                SharedPreferences.Editor registerationPrefsEditor;
                final String MyPREFERENCES = "MyPrefs";
                registrationPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                registerationPrefsEditor = registrationPreferences.edit();




                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.dialog_main_);
                dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog1.setCancelable(false);
                dialog1.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.popup_bg_trans));

                final TextView cancle = dialog1.findViewById(R.id.btn_cancel);
                TextView submit = dialog1.findViewById(R.id.btn_submit);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        registrationPreferences.edit().clear().commit();
                        Intent i = new Intent(getContext(), WelcomeActivity.class);
//                    menuMultipleActions.collapse();
                        getActivity().finish();
                        startActivity(i);
                        dialog1.dismiss();
                    }
                });


                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        dialog1.dismiss();
                    }
                });
                dialog1.show();
            }
        });



        return v;
    }
}