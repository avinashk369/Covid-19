package com.techcamino.info.covid_19.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.adapter.TweetsAdapter;
import com.techcamino.info.covid_19.details.MessageDetails;
import com.techcamino.info.covid_19.details.TweetsDetails;
import com.techcamino.info.covid_19.util.Constants;
import com.techcamino.info.covid_19.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.techcamino.info.covid_19.util.Constants.TWITTER_COUNT;
import static com.techcamino.info.covid_19.util.Constants.TWITTER_RESULT_TYPE;
import static com.techcamino.info.covid_19.util.Constants.TWITTER_SEARCH;

public class TwitterActivity extends BaseActivity implements TweetsAdapter.PlaceInfoListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    protected ViewGroup viewGroup;
    protected Context context = this;
    private RecyclerView tweetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        initToolbar();
        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        tweetsList = findViewById(R.id.top_tweets);
        viewGroup = findViewById(android.R.id.content);
        progressDialog = Utility.getProgressDialog(context,viewGroup, Constants.PLEASE_WAIT);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //Toast.makeText(getApplicationContext(), "Works!", Toast.LENGTH_LONG).show();
                getLatestTweets(TWITTER_SEARCH,TWITTER_RESULT_TYPE,TWITTER_COUNT);
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
        getLatestTweets(TWITTER_SEARCH,TWITTER_RESULT_TYPE,TWITTER_COUNT);
    }

    private void getLatestTweets(String query,String restype,int count){
        progressDialog.show();
        Call<TweetsDetails> call = apiService.getTweets(query,restype,count);
        call.enqueue(new Callback<TweetsDetails>() {
            @Override
            public void onResponse(Call<TweetsDetails> call, Response<TweetsDetails> response) {
                if (response.isSuccessful()) {

                    TweetsDetails tweetsDetails = new TweetsDetails();
                    tweetsDetails = response.body();
                    TweetsAdapter tweetsAdapter = new TweetsAdapter(tweetsDetails.getTweetsDetailsArrayList(),context);
                    GridLayoutManager gridLayoutManager =  new GridLayoutManager(getApplicationContext(),1, GridLayoutManager.VERTICAL,false);
                    tweetsList.setLayoutManager(gridLayoutManager);
                    tweetsList.setAdapter(tweetsAdapter);
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
            public void onFailure(Call<TweetsDetails> call, Throwable t) {
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
    public void onPopupMenuClick(TweetsDetails tweetsDetails, View view) {

    }
}
