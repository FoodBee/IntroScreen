package com.example.introscreen;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager2;
    private MyViewPagerAdapter myViewPagerAdapter;
    private int[] layouts;
    private TextView dot0,dot1,dot2,headline;
    private Button getStarted;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.pager);

        getStarted = findViewById(R.id.getstarted);
        headline = findViewById(R.id.headline);
        dot0 = findViewById(R.id.dot_0);
        dot1 = findViewById(R.id.dot_1);
        dot2 = findViewById(R.id.dot_2);

        layouts = new int[]{
                R.layout.slide_1,
                R.layout.slide_2,
                R.layout.slide_3,
        };

        addBottomDotsAndText(0);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                viewPager2.setCurrentItem((viewPager2.getCurrentItem()+1)%3);
            }
        };

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_from_bottom,R.anim.slide_from_bottom);
            }
        });
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },2000,2000);
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager2.setAdapter(myViewPagerAdapter);


        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDotsAndText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager2.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    private void addBottomDotsAndText(int i) {
        switch (i){
            case 0:
                headline.setText(getResources().getString(R.string.slide_1_title));
                dot0.setTextColor(getResources().getColor(R.color.colorAccent));
                dot1.setTextColor(getResources().getColor(R.color.dot_inactive));
                dot2.setTextColor(getResources().getColor(R.color.dot_inactive));
                break;
            case 1:
                headline.setText(getResources().getString(R.string.slide_2_title));
                dot0.setTextColor(getResources().getColor(R.color.dot_inactive));
                dot1.setTextColor(getResources().getColor(R.color.colorAccent));
                dot2.setTextColor(getResources().getColor(R.color.dot_inactive));
                break;
            case 2:
                headline.setText(getResources().getString(R.string.slide_3_title));
                dot0.setTextColor(getResources().getColor(R.color.dot_inactive));
                dot1.setTextColor(getResources().getColor(R.color.dot_inactive));
                dot2.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
        }
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
