package com.techcamino.info.covid_19.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.techcamino.info.covid_19.R;


public class Utility {


    /* Progress Bar */
    public static AlertDialog getProgressDialog(Context context, ViewGroup viewGroup, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.progress_layout_dialog, viewGroup, false);
        TextView msg = dialogView.findViewById(R.id.dialog_msg);
        msg.setText(message);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        return  alertDialog;
    }

    public static AlertDialog getCustomDialog(Context context, ViewGroup viewGroup, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_layout_dialog, viewGroup, false);
        TextView msg = dialogView.findViewById(R.id.custom_message);
        msg.setText(message);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        return  alertDialog;
    }
    /* End of progress Bar*/
}
