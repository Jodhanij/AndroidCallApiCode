package com.inheritx.simplewebservice.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Samir Bhatt on 4/28/2016.
 *
 *Common class for Shared preferences
 */
public class PreferencesManager {

    private static final String PREF_NAME = "com.inheritx.simplewebservice";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setValue(String key, String value) {
        mPref.edit()
                .putString(key, value)
                .commit();
    }

    public void setIntValue(String key, Integer value) {
        mPref.edit()
                .putInt(key, value)
                .commit();
    }

    public void setBooleanValue(String key, Boolean value) {
        mPref.edit()
                .putBoolean(key, value)
                .commit();
    }

    public Boolean getBooleanValue(String key, Boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

    public String getValue(String key) {
        return mPref.getString(key, "");
    }

    public Integer getIntValue(String key) {
        return mPref.getInt(key, 0);
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}
