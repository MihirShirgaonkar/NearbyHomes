package com.aditya.nearbyhomes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.aditya.nearbyhomes.homes.MainHomeFragment;
import com.aditya.nearbyhomes.messages.MessageFragment;
import com.aditya.nearbyhomes.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (true) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }


        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainHomeFragment())
                    .commit();
        }

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelected(false);

        bottomNavigationView.setClickable(false);

        bottomNavigationView.setSelectedItemId(R.id.invisible);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new MainHomeFragment())
                                .addToBackStack("s")
                                .commit();
                        break;


                     case R.id.msg:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new MessageFragment())
                                .addToBackStack("s")
                                .commit();
                        break;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new ProfileFragment())
                                .addToBackStack("s")
                                .commit();
                        break;



                }


                return false;
            }
        });




    }
}