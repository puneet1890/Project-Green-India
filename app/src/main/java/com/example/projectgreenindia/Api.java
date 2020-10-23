package com.example.projectgreenindia;

import java.util.ArrayList;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api
{
    //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/handleRegister
    //@FormUrlEncoded
    @POST("handleRegister")
    @Headers("Content-Type: application/json")
    Call<User> handleRegister(
            //@HeaderMap Map<String,String> headers,
            @Body User user);

    //
    @GET("login")
    Call<User> getData(@Query("userId") String userId,
                       @Query("password") String password);

    //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/generateOtp?mobile=9742875630
    @GET("generateOtp")
    Call<ResponseBody> getOTP(@Query("mobile") String mobile);

    //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/verifyOtp?mobile=9742875630&otp=8639
    @GET("verifyOtp")
    Call<ResponseBody> checkOTP(@Query("mobile") String mobile,
                                @Query("otp") String otp);

    //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/plantDetail?plantName=neem
    @GET("plantDetail")
    Call<Plant> getPlantDetails(@Query("plantName") String plantName);

    //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/changePassword
    //@FormUrlEncoded
    @POST("changePassword")
    Call<ResponseBody> changePassword(
            //@HeaderMap Map<String,String> headers,
            @Body Map<String, String> body);

    // http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/getReferralCode?userid=pankajsingh.cs@gmail.com
    @GET("getReferralCode")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> getReferalCode(@Query("userid") String userId);


    //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/invite
    @POST("invite")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> sendInvite(
            //@HeaderMap Map<String,String> headers,
            @Body Map<String, String> body);

    // http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/userHistory?userid=test@gmail.com

    @GET("userHistory")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> getUserHistory(@Query("userid") String userId);


    //Payment Gateway Status Web-service
    // http://ec2-18-191-26-57.us-east2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/updatePayment

    @POST("updatePayment")
    Call<ResponseBody> paymentUpdate(@Body Map<String, String> body);


    // http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/plantationDetail?userid=puneet@gmail.com

    @GET("plantationDetail")
    @Headers("Content-Type: application/json")
    Call<ArrayList<PlantationsDetail>> getPlantationDetail(@Query("userid") String userId);


    // http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/transactionDetail?userid=puneet@gmail.com

    @GET("transactionDetail")
    @Headers("Content-Type: application/json")
    Call<ArrayList<UserTransaction>> getTransactionDetails(@Query("userid") String userId);

}
