package com.inheritx.simplewebservice.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.inheritx.simplewebservice.R;
import com.inheritx.simplewebservice.pojo.Login;
import com.inheritx.simplewebservice.util.Constant;
import com.inheritx.simplewebservice.util.PreferencesManager;
import com.inheritx.simplewebservice.util.Utils;
import com.inheritx.simplewebservice.util.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_signup, txt_login, txt_forgot_pass;
    private EditText et_username, et_password;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        txt_forgot_pass = (TextView) findViewById(R.id.txt_forgot_pass);
        txt_forgot_pass.setOnClickListener(this);
        txt_login = (TextView) findViewById(R.id.txt_login);
        txt_login.setOnClickListener(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/dincondensed Bold.ttf");
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        txt_signup.setOnClickListener(this);
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage(getResources().getString(R.string.valid_please_wait));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_signup:
                //   startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
                break;
            case R.id.txt_login:
                checkValidation();
                break;
            case R.id.txt_forgot_pass:
                openPopUpForForgotPassword();
                break;
            default:
                break;
        }
    }

    private void openPopUpForForgotPassword() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.dialog_forgot_pass);
        ((TextView) dialog.findViewById(R.id.tvLabelForgotPass)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Graphik-Bold.ttf"));
        final EditText etForgotPass = (EditText) dialog.findViewById(R.id.et_email_for_forgot_pass);
        dialog.findViewById(R.id.txt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.txt_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Validator mValidator = Validator.getmValidator();
                Utils.getInstance().hideKeyboard(LoginActivity.this);

                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(dialog.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                if (Utils.getInstance().isNetworkConnected(LoginActivity.this)) {
                    if (Validator.getmValidator().isEmptyText(etForgotPass.getText().toString())) {
                        Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_enter_email));
                    } else if (!mValidator.isValidEmail(etForgotPass.getText().toString().trim())) {
                        Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_enter_username));
                    } else {
                        Utils.getInstance().hideKeyboard(LoginActivity.this);
                    }

                } else {
                    Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_network_connection));
                }
            }
        });
        dialog.show();
    }

    private void checkValidation() {
        if (Validator.getmValidator().isEmptyText(et_username.getText().toString())) {
            Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_enter_email));
        } else if (!Validator.getmValidator().isValidEmail(et_username.getText().toString().trim())) {
            Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_enter_username));
        } else if (Validator.getmValidator().isEmptyText(et_password.getText().toString())) {
            Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_enter_password));
        } else {
            if (Utils.getInstance().isNetworkConnected(LoginActivity.this)) {
                callLoginAPI();
            } else {
                Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_network_connection));
            }
        }

    }

    private void callLoginAPI() {

        // Call<Login> call = Utils.getInstance().initializeWebServiceCall(LoginActivity.this).login(et_username.getText().toString(), et_password.getText().toString(), Constant.DEVICE_TYPE_VALUE, Utils.getInstance().getDeviceId(LoginActivity.this));
        Call<Login> call = Utils.getInstance().initializeWebServiceCall(LoginActivity.this).login(et_username.getText().toString().trim(), et_password.getText().toString().trim(), Constant.DEVICE_TYPE_VALUE, Utils.getInstance().getDeviceId(LoginActivity.this));
        pd.show();
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                pd.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        PreferencesManager.initializeInstance(LoginActivity.this);
                        PreferencesManager.getInstance().setValue(Constant.USER_ID, response.body().getData().getUser_id());
                        PreferencesManager.getInstance().setValue(Constant.USER_EMAIL, response.body().getData().getUser_email());
                        PreferencesManager.getInstance().setValue(Constant.FIRSTNAME, response.body().getData().getFirst_name());
                        PreferencesManager.getInstance().setValue(Constant.LASTNAME, response.body().getData().getLast_name());
                        PreferencesManager.getInstance().setValue(Constant.CITY, response.body().getData().getCity());
                        PreferencesManager.getInstance().setValue(Constant.COUNTRY, response.body().getData().getCountry());
                        PreferencesManager.getInstance().setValue(Constant.STATE, response.body().getData().getState());
                        PreferencesManager.getInstance().setValue(Constant.GENDER, response.body().getData().getGender());
                        PreferencesManager.getInstance().setValue(Constant.ADDRESS1, response.body().getData().getAddress_1());
                        PreferencesManager.getInstance().setValue(Constant.ADDRESS2, response.body().getData().getAddress_2());
                        PreferencesManager.getInstance().setValue(Constant.ZIPCODE, response.body().getData().getZipcode());
                        PreferencesManager.getInstance().setValue(Constant.USER_ROLE_ID, response.body().getData().getUser_role_id());
                        PreferencesManager.getInstance().setValue(Constant.DEVICE_TOKEN, response.body().getData().getDevice_token());
                        PreferencesManager.getInstance().setValue(Constant.DEVICE_TYPE, response.body().getData().getDevice_type());
                        PreferencesManager.getInstance().setValue(Constant.USER_PROFILE_IMAGE, response.body().getData().getUser_profile_image());
                        PreferencesManager.getInstance().setValue(Constant.USER_BIRTH_DATE, response.body().getData().getDate_of_birth());
                        PreferencesManager.getInstance().setValue(Constant.USER_WEIGHT, response.body().getData().getWeight());
                        PreferencesManager.getInstance().setValue(Constant.USER_HEIGHT, response.body().getData().getHeight());
                        PreferencesManager.getInstance().setValue(Constant.USER_REACH, response.body().getData().getReach());
                        PreferencesManager.getInstance().setValue(Constant.USER_INSIDE_LEG, response.body().getData().getInside_leg());
                        PreferencesManager.getInstance().setValue(Constant.MEASUREMENT, response.body().getData().getMeasurement());
                        PreferencesManager.getInstance().setBooleanValue(Constant.ISLOGIN, true);
                        finish();
                    } else {
                        Utils.getInstance().DisplayToast(LoginActivity.this, response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                pd.dismiss();
                Utils.getInstance().DisplayToast(LoginActivity.this, getResources().getString(R.string.valid_try_again));
            }
        });
    }


}
