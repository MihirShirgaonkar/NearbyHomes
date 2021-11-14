package com.aditya.nearbyhomes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.aditya.nearbyhomes.intro.WelcomeActivity;


public class SplashScreenActivity extends AppCompatActivity {

        private static int SPLASH_SCREEN_TIME_OUT=3000;


    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        registrationPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();

        String LoginStatus = registrationPreferences.getString("LoginStatus", "");
        String Role = registrationPreferences.getString("Role", "");


            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //This method is used so that your splash activity
            //can cover the entire screen.

            //this will bind your MainActivity.class file with activity_main.

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (LoginStatus!=null && LoginStatus.equals("1")){

                        if (Role.equals("User")){
                        Intent intent =new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        }
                        else if (Role.equals("Admin")){

//                            Intent intent =new Intent(SplashScreenActivity.this, MainActivityAdmin.class);
//                            startActivity(intent);
                            finish();
                        }
                    }else {
                        Intent intent =new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    //Intent is used to switch from one activity to another.

                    //invoke the SecondActivity.

                    finish();
                    //the current activity will get finished.
                }
            }, SPLASH_SCREEN_TIME_OUT);

    }
}
