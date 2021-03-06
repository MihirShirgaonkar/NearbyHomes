package com.aditya.nearbyhomes;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class CustomProgressDialogue extends Dialog {


    public CustomProgressDialogue(@NonNull Context context) {
        super(context);

        WindowManager.LayoutParams  params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View  view= LayoutInflater.from(context).inflate(R.layout.progress_bar_layout,null);
        setContentView(view);
        getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.progress_bg));
    }
}
