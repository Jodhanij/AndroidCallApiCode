package com.inheritx.simplewebservice.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.inheritx.simplewebservice.http.RestAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Samir Bhatt on 4/28/2016.
 * Singleton class for Common functions
 */
public class Utils {
    private static Utils singleton;

    public static Utils getInstance() {
        if (singleton == null) {
            singleton = new Utils();
        }
        return singleton;
    }

    /**
     * Create instance of Retrofit for web service call
     */
    public RestAPI initializeWebServiceCall(final Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES).addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAPI mRestAPI = retrofit.create(RestAPI.class);
        return mRestAPI;
    }

    /**
     * Common function for display msg in snackbar
     *
     * @param context : context
     * @param msg     : Message to display
     */
    public void DisplayToast(Context context, String msg) {
        try {
            Snackbar.make(((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
            //   Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Hide keyboard
     */
    public void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * Return device id
     */
    public String getDeviceId(Context mContext) {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /**
     * Check for internet connections
     */
    public boolean isNetworkConnected(Context context) {
        try {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            return cm.getActiveNetworkInfo() != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
