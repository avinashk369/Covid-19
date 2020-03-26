package com.techcamino.info.covid_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.gson.Gson;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompleteCallback;
import com.otaliastudios.autocomplete.AutocompletePresenter;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.details.DashboardDetails;
import com.techcamino.info.covid_19.details.MessageDetails;
import com.techcamino.info.covid_19.util.Constants;
import com.techcamino.info.covid_19.util.Utility;
import com.techcamino.info.covid_19.widgets.User;
import com.techcamino.info.covid_19.widgets.UserPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements OnChartValueSelectedListener {

    private PieChart chart;
    private int[] MY_COLORS;
    private BottomNavigationView bottomNavigationView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView infected,deaths,recovered,newCases,newDeath;
    protected ViewGroup viewGroup;
    protected Context context = this;

    private Autocomplete userAutocomplete;
    private MaterialSearchBar searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MY_COLORS = new int[]{
                getResources().getColor(R.color.grad_dusk_end),
                getResources().getColor(R.color.grad_dusk_start),
                getResources().getColor(R.color.green),
                getResources().getColor(R.color.yellow_dark),
                getResources().getColor(R.color.red)
        };
        viewGroup = findViewById(android.R.id.content);
        progressDialog = Utility.getProgressDialog(context,viewGroup, Constants.PLEASE_WAIT);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        infected  =findViewById(R.id.total_infected);
        deaths = findViewById(R.id.total_deaths);
        recovered = findViewById(R.id.total_recovered);
        newCases = findViewById(R.id.new_cases);
        newDeath = findViewById(R.id.new_death);
        //searchBar = findViewById(R.id.searchBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //Toast.makeText(getApplicationContext(), "Works!", Toast.LENGTH_LONG).show();
                getWorldStat();
                /*// To keep animation for 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);*/
            }
        });

        // Scheme colors for animation
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        initChart();

        getWorldStat();
        //setupMaterialSearchbar();
        setupUserAutocomplete();
    }

    private void setupMaterialSearchbar(){
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Log.d("TextChanged",charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setupUserAutocomplete() {
        EditText edit = findViewById(R.id.country_name);
        float elevation = 6f;
        Drawable backgroundDrawable = new ColorDrawable(Color.WHITE);
        AutocompletePresenter<User> presenter = new UserPresenter(this);
        AutocompleteCallback<User> callback = new AutocompleteCallback<User>() {
            @Override
            public boolean onPopupItemClicked(@NonNull Editable editable, @NonNull User item) {
                editable.clear();
                editable.append(item.getFullname());
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {}
        };

        userAutocomplete = Autocomplete.<User>on(edit)
                .with(elevation)
                .with(backgroundDrawable)
                .with(presenter)
                .with(callback)
                .build();
    }

    private void getWorldStat(){
        progressDialog.show();
        Call<DashboardDetails> call = apiService.getDashboardDetails();
        call.enqueue(new Callback<DashboardDetails>() {
            @Override
            public void onResponse(Call<DashboardDetails> call, Response<DashboardDetails> response) {
                if (response.isSuccessful()) {

                    DashboardDetails dashboardDetails = new DashboardDetails();
                    dashboardDetails = response.body();
                    infected.setText(dashboardDetails.getTotalCases());
                    deaths.setText(dashboardDetails.getTotalDeaths());
                    recovered.setText(dashboardDetails.getTotalRecovered());
                    newCases.setText(dashboardDetails.getNewCases());
                    newDeath.setText(dashboardDetails.getNewDeaths());
                    setData(dashboardDetails);
                    swipeRefreshLayout.setRefreshing(false);

                } else {
                    try {
                        MessageDetails messageDetails = new MessageDetails();
                        Gson gson = new Gson();
                        messageDetails=gson.fromJson(response.errorBody().charStream(),MessageDetails.class);
                        Log.d("Avinash", messageDetails.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DashboardDetails> call, Throwable t) {
                Log.d("Failure", t.getMessage().toString());
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
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

    private void setData(DashboardDetails dashboardDetails) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.

        entries.add(new PieEntry(Integer.valueOf(dashboardDetails.getTotalDeaths().replace(",","")),
                "Deaths",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(Integer.valueOf(dashboardDetails.getTotalCases().replace(",","")),
                "Infected",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(Integer.valueOf(dashboardDetails.getTotalRecovered().replace(",","")),
                "Recovered",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(Integer.valueOf(dashboardDetails.getNewCases().replace(",","")),
                "New Cases",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(Integer.valueOf(dashboardDetails.getNewDeaths().replace(",","")),
                "New Death",
                getResources().getDrawable(R.drawable.ic_launcher_background)));

        /*entries.add(new PieEntry(300,
                "Deaths",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(200,
                "Infected",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(100,
                "Recovered",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(150,
                "New Cases",
                getResources().getDrawable(R.drawable.ic_launcher_background)));
        entries.add(new PieEntry(180,
                "New Death",
                getResources().getDrawable(R.drawable.ic_launcher_background)));*/


        /*for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    levels.get(i),
                    getResources().getDrawable(R.drawable.star)));
        }*/

        PieDataSet dataSet = new PieDataSet(entries, null);

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(0f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        //if(!found)
        /*if(!found)
        {
            entries.add(new PieEntry(1,
                    "No value",
                    getResources().getDrawable(R.drawable.ic_launcher_background)));
            chart.getLegend().setEnabled(false);
            dataSet.setDrawValues(false);
        }*/

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
