package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Invite_Earn_Activity extends AppCompatActivity
{
    private static final String TAG = "Invite & Earn";
    String ref,email,pass,refferal_code;
    TextView tv_Refferal_UniqueMsg,tv_RefferalCode,tv_Refferal_Msg;
    Button btn_Invite_Earn;
    Api api;
    Call<ResponseBody> call;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_earn);

        setTitle("Invite & Earn");

        Intent intent = getIntent();
        ref = intent.getStringExtra("refferal_code");
        email = intent.getStringExtra("user_email");
        pass = intent.getStringExtra("user_pass");

        Log.i(TAG,"Refferal Code: "+ref);
        Log.i(TAG,"Email: "+email);
        Log.i(TAG,"Password: "+pass);

        //tv_Refferal_Msg = findViewById(R.id.tv_Refferal_Msg);
        tv_RefferalCode = findViewById(R.id.tv_RefferalCode);
        //tv_Refferal_UniqueMsg = findViewById(R.id.tv_Refferal_UniqueMsg);
        btn_Invite_Earn = findViewById(R.id.btn_Invite_Earn);

        sharedPref = getSharedPreferences("myPref",0);
        //editor = sharedPref.edit();

        getRefferalCode();

        String ref_code = sharedPref.getString("ref_code",null);
        tv_RefferalCode.setText(ref_code);
    }


    void getRefferalCode()
    {
        //email = et_Login_Email.getText().toString();
        //password = et_Login_Password.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/getReferralCode?userid=pankajsingh.cs@gmail.com
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        //call = api.getData(startDate,endDate,budget,type,groupName,member);

        call = api.getReferalCode(email);
        getCode();
    }

    void getCode()
    {
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                Log.i(TAG,"response from Server: "+response.toString());

                if(response.isSuccessful())
                {
                    try
                    {
                        Log.i(TAG,"Response from Server: "+response.toString());
                        if (response.body() != null)
                        {
                            //Log.i(TAG,"Response Body from Server: "+response.body().string());
                            String rcode = response.body().string();
                            //Log.i(TAG,"Response Body from Server: "+response.body().string());
                            Log.i(TAG,"Referral Code: "+rcode);

                            String reff_code = parseData(rcode);

                            //SharedPreferences sharedPref = getSharedPreferences("myPref",0);
                            editor = sharedPref.edit();
                            editor.putString("ref_code",reff_code);
                            editor.apply();

                            tv_RefferalCode.setText(reff_code);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Log.i(TAG,"response from Server: "+response.code());
                    //Toast.makeText(getApplicationContext(),"Failed to fetch referral code",Toast.LENGTH_LONG).show();

                    String ref_code = sharedPref.getString("ref_code",null);
                    tv_RefferalCode.setText(ref_code);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t)
            {
                Log.i(TAG,"Failed to fetch Refferal Code: "+t.getMessage());
                Toast.makeText(getApplicationContext(),"Failed to fetch referral code: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String parseData(String rcode)
    {
        JSONObject jsonObject;
        try
        {
            jsonObject = new JSONObject(rcode);
            refferal_code = jsonObject.getString("referralCode");

            Log.i(TAG,"Referral Code: "+refferal_code);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return refferal_code;
    }

    public void sendRefferalMail(View view)
    {
        String ref_Code = tv_RefferalCode.getText().toString();

        Intent intent = new Intent(getApplicationContext(),SendingMail.class);
        intent.putExtra("refferal_code",ref_Code);
        intent.putExtra("sender_user_email",email);
        startActivity(intent);
    }
}

