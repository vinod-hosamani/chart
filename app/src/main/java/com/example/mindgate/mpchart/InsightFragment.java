package com.example.mindgate.mpchart;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


public class InsightFragment extends android.support.v4.app.Fragment implements View.OnClickListener, OnChartValueSelectedListener {

    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    AppCompatImageView backButton, nextButton;
    AppCompatTextView datePicker, totalAmountTextView, avgAmountTextView;
    BarChart barChart;
    double totalAmount, avgAmount;
    List<BarEntry> dataEntries;
    BarDataSet barDataSet;
    BarData barData;
    String[] weeks = new String[7];
    String[] days = new String[7];
    DatePickerDialog.OnDateSetListener dateSetter;
    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_insight, container, false);
        initView(view);
        return view;
    }
    private void initView(View view)
    {
        backButton = view.findViewById(R.id.back);
        nextButton = view.findViewById(R.id.next);
        //selectedBarValue = view.findViewById(R.id.selectedBarValue);
        datePicker = view.findViewById(R.id.datePick);
        barChart = view.findViewById(R.id.barChart);

        barChart.setRenderer(new
                RoundedBarChartRenderer(barChart, barChart.getAnimator(),
                barChart.getViewPortHandler(), 16f, 16f));

        totalAmountTextView = view.findViewById(R.id.totalAmountTextView);
        avgAmountTextView = view.findViewById(R.id.avgEarning);

        calendar = Calendar.getInstance();
        dataEntries = new ArrayList<>();
        barChart.setOnChartValueSelectedListener(this);


        dateSetter = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                setLabels(calendar);
            }
        };
        setClickListner();
    }
    private void setLabels(Calendar calendar)
    {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        SimpleDateFormat dateAndMonthFormat = new SimpleDateFormat("dd MMM");

        String from = dateAndMonthFormat.format(calendar.getTime());

        for (int i=0;i<7;i++)
        {
            weeks[i] = dayFormat.format(calendar.getTime());
            days[i] = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String to = dateAndMonthFormat.format(calendar.getTime());

        datePicker.setText(from+"-"+to);

        loadPreference();
    }

    double[] randomArray = new double[7];

    private void loadPreference()
    {
        if (barData != null)
        {
            dataEntries.clear();
            barData.setValueTextColor(Color.BLACK);
            barChart.invalidate();
            barChart.clear();
        }

        Random random = new Random();

        for (int i = 0; i < randomArray.length; i++)
        {
            randomArray[i] = random.nextFloat() * random.nextInt(1000);
        }

        int count = 0;

        for (double x :randomArray)
        {
            dataEntries.add(new BarEntry(count++, (float) x));
            totalAmount+=x;
        }
        avgAmount = totalAmount/randomArray.length;
        barDataSet = new BarDataSet(dataEntries,"");
        barDataSet.setColor(ContextCompat.getColor(getContext(), R.color.color_acent));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.notifyDataSetChanged();
        barDataSet.setDrawValues(false);
        barData = new BarData(barDataSet);
        barData.notifyDataChanged();
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barData.setBarWidth(0.25f);
        //barChart.setMaxHighlightDistance(50);
        // barChart.highlightValue(10f,0);
        barChart.animateXY(3000, 3000);
        barChart.getDescription().setEnabled(false);
        //IAxisValueFormatter xAxisFormatter = new MyXAxisFormater(barChart);
        barChart.getLegend().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setMultiLineLabel(true);
        xAxis.setLabelCount(7);
//        xAxis.setAxisLineWidth(40);
//        xAxis.setGridLineWidth(40);
        xAxis.setValueFormatter(new MyXAxisFormater(weeks, days));
        barChart.setFitBars(true);
        //Accessing YAxis

        //Code for hiding right side yAxis details
        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.enableGridDashedLine(10f, 10f, 0);
        yAxisRight.setEnabled(false);

        //Code for hiding left side straight bar
        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.enableGridDashedLine(10f, 10f, 0);
        yAxisLeft.setDrawAxisLine(false);

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(9f);
        legend.setTextSize(11f);
        legend.setXEntrySpace(4f);

        BarCustomMarkerView customMarkerView = new BarCustomMarkerView(getContext());
        customMarkerView.setChartView(barChart);
        barChart.setMarker(customMarkerView);
        barChart.notifyDataSetChanged();
        barChart.invalidate();
        totalAmountTextView.setText(String.valueOf(decimalFormat.format(totalAmount)));
        avgAmountTextView.setText(String.valueOf(decimalFormat.format(avgAmount)));
    }
    private void setClickListner()
    {
        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        datePicker.setOnClickListener(this);
        barChart.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.datePick:
                new DatePickerDialog(getContext(), dateSetter, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;

            case R.id.back:
                getPreviousWeek();
                break;

            case R.id.next:
                getNextWeek();
                break;
        }
    }
    private void getNextWeek() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        setLabels(calendar);
    }
    private void getPreviousWeek()
    {
        calendar.add(Calendar.DAY_OF_MONTH, -13);
        setLabels(calendar);
    }
    protected RectF mOnValueSelectedRectF = new RectF();
    @Override
    public void onValueSelected(Entry e, Highlight h)
    {
        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        barChart.getBarBounds((BarEntry) e, bounds);

        Log.i("bounds", bounds.toString());
        Log.i("x-index",
                "low: " + barChart.getLowestVisibleX() + ", high: "
                        + barChart.getHighestVisibleX());
    }
    @Override
    public void onNothingSelected()
    {

    }
}
