package com.techcamino.info.covid_19.util;

import android.content.Context;
import android.content.SharedPreferences;


import com.techcamino.info.covid_19.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bibaswann on 20-02-2017.
 */

public class SettingsAPI {
    Context mContext;
    private SharedPreferences sharedSettings;

    public SettingsAPI(Context context) {
        mContext = context;
        sharedSettings = mContext.getSharedPreferences(mContext.getString(R.string.settings_file_name), Context.MODE_PRIVATE);
    }

    public String readSetting(String key) {
        return sharedSettings.getString(key, Constants.PREF_DEFAULT_STRING);
    }

    public boolean readBooleanSetting(String key) {
        return sharedSettings.getBoolean(key, false);
    }

    public void addUpdateSettings(String key, String value) {
        SharedPreferences.Editor editor = sharedSettings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void addUpdateBooleanSettings(String key, boolean value) {
        SharedPreferences.Editor editor = sharedSettings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void deleteAllSettings() {
        sharedSettings.edit().clear().apply();
    }

    public List<String> readAll() {
        List<String> allUser = new ArrayList<>();
        Map<String, ?> allEntries = sharedSettings.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().contains("@"))
                allUser.add(entry.getKey() + " (" + entry.getValue() + ")");
        }
        return allUser;
    }
}
