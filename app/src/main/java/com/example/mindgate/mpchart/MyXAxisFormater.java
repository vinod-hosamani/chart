package com.example.mindgate.mpchart;

import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyXAxisFormater implements IAxisValueFormatter {
    private String[] weeks ;
    private String[] days;
    public MyXAxisFormater(String[] weeks, String[] days) {
        this.weeks=weeks;
        this.days = days;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (weeks != null && weeks.length > (int) value) {
//            return days[(int) value]+"\n"+weeks[(int) value];
            return days[(int) value]+"\n"+weeks[(int) value];
        } else
            return "";
    }


}