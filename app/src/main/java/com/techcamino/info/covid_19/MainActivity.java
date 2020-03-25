package com.techcamino.info.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private PieChart chart;
    private int[] MY_COLORS;
    private BottomNavigationView bottomNavigationView;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MY_COLORS = new int[]{
                getResources().getColor(R.color.grad_dusk_end),
                getResources().getColor(R.color.grad_dusk_start),
                getResources().getColor(R.color.green)
        };

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initChart();
        setData(3,2);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    return true;

                case R.id.navigation_trending:
                    return true;

                case R.id.navigation_help:
                    return true;
            }
            return false;
        }
    };

    public void initChart(){
        chart = findViewById(R.id.dashboard_chart);
        chart.setUsePercentValues(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        //chart.setCenterTextTypeface(ResourcesCompat.getFont(this, R.font.montserrat_regular));
        chart.setCenterText(generateCenterSpannableText());


        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(35f);
        chart.setTransparentCircleRadius(38f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(false);
        chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        chart.setOnChartValueSelectedListener(this);
        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart.getLegend();
        l.setEnabled(false);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);

        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setDrawEntryLabels(false);
        chart.setEntryLabelColor(Color.BLUE);
        //chart.setEntryLabelTypeface(ResourcesCompat.getFont(this, R.font.montserrat_regular));
        chart.setEntryLabelTextSize(12f);
    }

    private SpannableString generateCenterSpannableText() {
        Calendar cal = Calendar.getInstance();
        String month  = cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault());
        int day  = cal.get(Calendar.DAY_OF_MONTH);
        Log.d("Day time", month+" = "+day);
        SpannableString s = new SpannableString(day+"\n"+month);
        s.toString().split("\n");
        s.setSpan(new RelativeSizeSpan(3.7f), 0, s.toString().split("\n")[0].length(), 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.toString().split("\n")[0].length(), 0);

        /*s.setSpan(new RelativeSizeSpan(.8f), s.toString().split("\n")[0].length(), s.toString().split("\n")[1].length(), 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.toString().split("\n")[0].length(), s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.toString().split("\n")[0].length(), s.length(), 0);*/
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0, s.length(), 0);
        return s;
    }
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.

        boolean found = true;

            entries.add(new PieEntry(40,
                    "Deaths",
                    getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(300,
                "Infected",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
            entries.add(new PieEntry(170,
                    "Recovered",
                    getResources().getDrawable(R.drawable.ic_launcher_background)));



        /*for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    levels.get(i),
                    getResources().getDrawable(R.drawable.star)));
        }*/

        PieDataSet dataSet = new PieDataSet(entries, null);

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        //if(!found)
        if(!found)
        {
            entries.add(new PieEntry(1,
                    "No value",
                    getResources().getDrawable(R.drawable.ic_launcher_background)));
            chart.getLegend().setEnabled(false);
            dataSet.setDrawValues(false);
        }

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();


        for(int c: MY_COLORS)
            colors.add(c);

        dataSet.setColors(colors);
        dataSet.setDrawValues(false);
        //dataSet.setColors(MY_COLORS);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        //data.setValueTypeface(ResourcesCompat.getFont(this, R.font.montserrat_regular));
        chart.setData(data);



        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }
}
