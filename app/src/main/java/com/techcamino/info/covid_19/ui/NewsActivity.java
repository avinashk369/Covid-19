package com.techcamino.info.covid_19.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.adapter.NewsAdapter;
import com.techcamino.info.covid_19.details.ArticleDetails;
import com.techcamino.info.covid_19.details.MessageDetails;
import com.techcamino.info.covid_19.util.Constants;
import com.techcamino.info.covid_19.util.DividerItemDecorator;
import com.techcamino.info.covid_19.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.techcamino.info.covid_19.util.Constants.COUNTRY;
import static com.techcamino.info.covid_19.util.Constants.NEWS_API_KEY;

public class NewsActivity extends BaseActivity implements NewsAdapter.PlaceInfoListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    protected ViewGroup viewGroup;
    protected Context context = this;
    private RecyclerView newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initToolbar();

        try{
            toolbar.setBackgroundResource(R.drawable.toolbar_background_news);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        newsList = findViewById(R.id.top_news);
        viewGroup = findViewById(android.R.id.content);
        progressDialog = Utility.getProgressDialog(context,viewGroup, Constants.PLEASE_WAIT);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getNews(COUNTRY,NEWS_API_KEY);
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
        getNews(COUNTRY,NEWS_API_KEY);
    }

    private void getNews(String country,String apiKey){
        progressDialog.show();
        Call<ArticleDetails> call = apiService.getNews(country,apiKey);
        call.enqueue(new Callback<ArticleDetails>() {
            @Override
            public void onResponse(Call<ArticleDetails> call, Response<ArticleDetails> response) {
                if (response.isSuccessful()) {

                    ArticleDetails articleDetails = new ArticleDetails();
                    articleDetails = response.body();
                    NewsAdapter newsAdapter = new NewsAdapter(articleDetails.getArticleDetailsArrayList(),context);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                    newsList.setLayoutManager(mLayoutManager);
                    newsList.addItemDecoration(new DividerItemDecorator(context.getResources().getDrawable(R.drawable.divider)));
                    newsList.setAdapter(newsAdapter);
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
            public void onFailure(Call<ArticleDetails> call, Throwable t) {
                Log.d("Failure", t.getMessage().toString());
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onPopupMenuClick(ArticleDetails articleDetails, View view) {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleDetails.getUrl())));

    }
}
