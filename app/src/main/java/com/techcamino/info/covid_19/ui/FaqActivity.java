package com.techcamino.info.covid_19.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.feed.HeadingView;
import com.techcamino.info.covid_19.feed.InfoView;
import com.techcamino.info.covid_19.feed.data.Feed;
import com.techcamino.info.covid_19.feed.data.Info;
import com.techcamino.info.covid_19.util.Utils;

public class FaqActivity extends BaseActivity {


    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        initToolbar();
        try{
            toolbar.setBackgroundResource(R.drawable.toolbar_background_helpl);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        mExpandableView = findViewById(R.id.expandableView);

        for(Feed feed : Utils.loadFeeds(mContext)){
            mExpandableView.addView(new HeadingView(mContext, feed.getHeading()));
            for(Info info : feed.getInfoList()){
                mExpandableView.addView(new InfoView(mContext, info));
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
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
}
