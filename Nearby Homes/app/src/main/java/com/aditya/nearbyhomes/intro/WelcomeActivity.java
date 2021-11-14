package com.aditya.nearbyhomes.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.register.LoginActivity;


public class WelcomeActivity extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dotsLayout;
    WelcomeViewPagerAdapter adapter;
    TextView[] dots;

    TextView next;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

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


//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        next = findViewById(R.id.next);


        viewPager = findViewById(R.id.viewpager);
        dotsLayout = findViewById(R.id.dots);

        adapter = new WelcomeViewPagerAdapter(WelcomeActivity.this);
        viewPager.setAdapter(adapter);

        addDotsindicator(0);
        viewPager.addOnPageChangeListener(viewListner);


TextView regTv;
        regTv=findViewById(R.id.reg);
        regTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentPage==dots.length-1){
//                    Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
//                    startActivity(intent);
                }
                viewPager.setCurrentItem(currentPage+1);
            }
        });


    }


    public void addDotsindicator(int position) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            dots[i].setTextColor(getResources().getColor(R.color.aluminum));

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.absentcolor));
        }
    }


    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsindicator(position);
            currentPage = position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void location_page(View view) {
        Intent intent=new Intent(WelcomeActivity.this,LocationActivity.class);
        startActivity(intent);
        finish();
    }
}
