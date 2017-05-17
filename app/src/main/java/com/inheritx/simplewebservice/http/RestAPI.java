package com.inheritx.simplewebservice.http;

import com.inheritx.simplewebservice.pojo.Login;
import com.inheritx.simplewebservice.pojo.SignUp;
import com.inheritx.simplewebservice.util.Constant;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Samir Bhatt on 4/27/2016.
 */
public interface RestAPI {
    /**
     * Login Web service
     *
     * @param user_email
     * @param password
     * @param device_type
     * @param device_tocken
     * @return
     */

    @FormUrlEncoded
    @POST(Constant.DIR + Constant.LOGIN)
    Call<Login> login(@Field("user_email") String user_email,
                      @Field("password") String password,
                      @Field("device_type") String device_type,
                      @Field("device_token") String device_tocken
    );

    /**
     * Sign up service Using RequestBody
     *
     * @param user_profile_image
     * @param user_email
     * @param first_name
     * @param last_name
     * @param password
     * @param city
     * @param state
     * @param country
     * @param gender
     * @param address_1
     * @param address_2
     * @param zipcode
     * @param device_token
     * @param device_type
     * @param user_role_id
     * @param weight
     * @param height
     * @param reach
     * @param inside_leg
     * @param date_of_birth
     * @param measurement
     * @param referral_code
     * @return
     */

    @Headers({"Connection: Keep-Alive",
            "Accept-Language: en-US"})
    @Multipart
    @POST(Constant.DIR + Constant.REGISTRATION)
    Call<SignUp> signUp(@Part("user_profile_image\"; filename=\"image.jpg\" ") RequestBody user_profile_image,
                        @Part("user_email") RequestBody user_email,
                        @Part("first_name") RequestBody first_name,
                        @Part("last_name") RequestBody last_name,
                        @Part("password") RequestBody password,
                        @Part("city") RequestBody city,
                        @Part("state") RequestBody state,
                        @Part("country") RequestBody country,
                        @Part("gender") RequestBody gender,
                        @Part("address_1") RequestBody address_1,
                        @Part("address_2") RequestBody address_2,
                        @Part("zipcode") RequestBody zipcode,
                        @Part("device_token") RequestBody device_token,
                        @Part("device_type") RequestBody device_type,
                        @Part("user_role_id") RequestBody user_role_id,
                        @Part("weight") RequestBody weight,
                        @Part("height") RequestBody height,
                        @Part("reach") RequestBody reach,
                        @Part("inside_leg") RequestBody inside_leg, @Part("date_of_birth") RequestBody date_of_birth, @Part("measurement") RequestBody measurement, @Part("referral_code") RequestBody referral_code);

}
