package com.techcamino.info.covid_19.ui;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.resoursec.APIClient;
import com.techcamino.info.covid_19.resoursec.APIInterFace;
import com.techcamino.info.covid_19.util.Constants;
import com.techcamino.info.covid_19.util.Utility;


public class BaseActivity extends AppCompatActivity {

    protected APIInterFace apiService;
    protected AlertDialog progressDialog,dialog;

    protected void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

    }

    @Override
    protected void onStart() {
        super.onStart();

        apiService =
                APIClient.getClient().create(APIInterFace.class);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransitionExit();
    }
    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

}