package com.example.mindgate.mpchart;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;


public class BarCustomMarkerView extends MarkerView {
    private AppCompatTextView tvContent;
    private DecimalFormat format;
    String[] weeks = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    public BarCustomMarkerView(Context context) {
        super(context, R.layout.custom_marker_view);
        tvContent=findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        tvContent.setText("s$ " + format.format(e.getY()));
        tvContent.setTextColor(Color.BLACK);
        Log.d("marker view", "refreshContent: "+format.format(e.getY()));
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
