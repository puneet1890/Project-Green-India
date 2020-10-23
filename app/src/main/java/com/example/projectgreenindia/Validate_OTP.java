package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Validate_OTP extends AppCompatActivity
{
    private static final String TAG = "Validate_OTP";
    private String mobile;
    public EditText et_otpNumber,et_mobileNum;
    Button btn_verifyOTP;
    Api api;
    String otp,pass;
    Call<ResponseBody> call;
    ImageView otp_imgView;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_otp);

        setTitle("OTP Verification");

        otp_imgView = findViewById(R.id.otp_imgView);
        requestSMSPermission();

        et_otpNumber = findViewById(R.id.et_otpNumber);
        et_mobileNum = findViewById(R.id.et_mobileNum);
        btn_verifyOTP = findViewById(R.id.btn_verifyOTP);

        otp_imgView.setImageResource(R.drawable.otp_bg);

/*        Intent intent = getIntent();
        mobile = intent.getStringExtra("user_mobile");
        pass = intent.getStringExtra("user_password");
*/
/*        SharedPreferences sharedPref = getSharedPreferences("myPref",0);
        mobile = sharedPref.getString("admin_mobile",null);
*/
    }

    private void fetchOTP()
    {
        mobile = et_mobileNum.getText().toString();
        Log.d(TAG,"Mobile from EditText: "+mobile);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        //call = api.getData(startDate,endDate,budget,type,groupName,member);

        call = api.getOTP(mobile);
        receivedOTP();
    }

    private void receivedOTP()
    {
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                Log.i(TAG,"response code from Server: "+response.code());
                Log.i(TAG,"response from Server: "+response.toString());

                if(response.isSuccessful())
                {
                    Log.i(TAG,"response from Server: "+response.toString());

                    //otp = response.body().toString();
                    //Log.i(TAG,"response from Server: "+otp);
                }
                else
                {
                    Log.i(TAG,"response from Server: "+response.toString());
                }
/*
                Intent intent = new Intent(getApplicationContext(),User_Dashboard.class);
                intent.putExtra("user_email",email);
                startActivity(intent);
*/
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t)
            {
                Log.i(TAG,"Failed to receive OTP: "+t.getMessage());
                Toast.makeText(getApplicationContext(),"Failed to receive OTP: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void verifyOTP(View view)
    {
        // http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/verifyOtp?mobile=9742875630&otp=8639
        otp = et_otpNumber.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        //call = api.getData(startDate,endDate,budget,type,groupName,member);

        call = api.checkOTP(mobile,otp);
        verifyingOTP();
    }

    private void verifyingOTP()
    {
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                Log.i(TAG,"response code from Server: "+response.code());
                Log.i(TAG,"response from Server: "+response.toString());

                if(response.isSuccessful())
                {
                    Log.i(TAG,"response from Server: "+response.toString());

                    try
                    {
                        String otp = response.body().string();
                        Log.i(TAG,"response from Server: "+otp);

                        //receiveOTP();
                        Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                        intent.putExtra("user_mobile",mobile);
                        intent.putExtra("user_password",pass);
                        startActivity(intent);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    //startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
                }
                else
                {
                    Log.i(TAG,"response from Server: "+response.toString());
/*                    Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                    intent.putExtra("user_mobile",mobile);
                    intent.putExtra("user_password",pass);
                    startActivity(intent);
*/
                }
/*
                Intent intent = new Intent(getApplicationContext(),User_Dashboard.class);
                intent.putExtra("user_email",email);
                startActivity(intent);
*/
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t)
            {
                Log.i(TAG,"Failed to receive OTP: "+t.getMessage());
                Toast.makeText(getApplicationContext(),"Failed to receive OTP: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

/*
    private void receiveOTP()
    {
        OTP_Receiver otp_receiver = new OTP_Receiver(ctx);
        //otp_receiver.setEditText(et_otpNumber);

        et_otpNumber.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                Log.i(TAG,"Received OTP: "+s.toString());

                Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                intent.putExtra("user_mobile",mobile);
                intent.putExtra("user_password",pass);
                intent.putExtra("otp",s.toString());
                startActivity(intent);
            }
        });
    }
*/

    private void requestSMSPermission()
    {
        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED)
        {
            String[] permission_list = new String[1];
            permission_list[0] = permission;

            ActivityCompat.requestPermissions(this, permission_list,1);
        }
    }

    public void getOTP(View view)
    {
        fetchOTP();
    }

}
