package com.example.user.eduapp.Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.eduapp.R;
import com.example.user.eduapp.WelcomePage;

public class WelcomeActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView[]dotstv;
    private int[] layouts;
    private Button btnSkip;
    private Button btnNext;
    private MyPageAdapter pagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setStatusBarTransparent();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        linearLayout = (LinearLayout) findViewById(R.id.dotLayout);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        //when user press Skip, start MainActivity
        btnSkip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startMainActivity();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage<layouts.length){
                    viewPager.setCurrentItem(currentPage);
                }else
                {
                    startMainActivity();
                }
            }
        });
        layouts = new int[]{R.layout.slider_0, R.layout.slider_1, R.layout.slider_2, R.layout.slider_3};
        pagerAdapter = new MyPageAdapter(layouts, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==layouts.length-1){
                    btnNext.setText("Start");
                    btnSkip.setVisibility(View.GONE);
                }else{
                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);
    }

    private void setDotStatus(int page){
        linearLayout.removeAllViews();
        dotstv = new TextView[layouts.length];
        for(int i=0; i<dotstv.length; i++){
            dotstv[i]=new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            linearLayout.addView(dotstv[i]);
        }
        //set current dot active
        if(dotstv.length>0){
            dotstv[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }


    private void startMainActivity(){
        String username = getIntent().getExtras().getString("username");
        Intent intent = new Intent(WelcomeActivity.this, WelcomePage.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
    private void setStatusBarTransparent(){
        if(Build.VERSION.SDK_INT>=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }
}
