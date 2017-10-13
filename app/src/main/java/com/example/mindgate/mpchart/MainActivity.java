package com.example.mindgate.mpchart;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    Toolbar toolbar;
    AppCompatImageView addMoney;
    DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    int hour,minute,years,month,day;
    private Date date;
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setViewPager();
    }
    private void setViewPager()
    {
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        addMoney= (AppCompatImageView) findViewById(R.id.addMoney);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.overView)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.insights)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pagerAdapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        setClickListener();
        toolbar.setTitle(getString(R.string.seller_mode));
    }

    @Override
    public void setClickListener() {
        addMoney.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addMoney:
                break;
        }
    }
}
