package com.example.shinygrace.myproject;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager ImageViewPager;
    ImageViewPagerAdapter imageViewPagerAdapter;
    LinearLayout DotsLayout;
    private int dotscount;
    private ImageView[] dots;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageViewPager = findViewById(R.id.ImageViewPager);
        imageViewPagerAdapter = new ImageViewPagerAdapter(this);
        ImageViewPager.setAdapter(imageViewPagerAdapter);
        DotsLayout = findViewById(R.id.DotsLayout);
        dotscount = imageViewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        timer = new Timer();

        for(int i=0; i<dotscount;i++)
        {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            DotsLayout.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
        ImageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for(int i=0; i<dotscount;i++)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active_dot));

                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        timer.scheduleAtFixedRate(new mytimer(),2000,4000);
    }
    public class mytimer extends TimerTask
    {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(ImageViewPager.getCurrentItem()==0)
                        ImageViewPager.setCurrentItem(1);
                    else if(ImageViewPager.getCurrentItem()==1)
                        ImageViewPager.setCurrentItem(2);
                    else
                        ImageViewPager.setCurrentItem(0);
                }
            });
        }
    }
}
