package com.example.mindgate.mpchart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OverviewFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    AppCompatImageView backButton,nextButton;
    AppCompatTextView datePicker_textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_overview,container,false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        setClickListener();
    }

    private void setClickListener() {

    }

    @Override
    public void onClick(View view) {

    }
}
