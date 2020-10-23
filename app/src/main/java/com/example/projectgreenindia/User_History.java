package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User_History extends AppCompatActivity
{
    String email;
    public static final String TAG = "UserHistory";
    Api api;
    Call<ArrayList<UserTransaction>> call;

    UserTransaction transactions;
    RecyclerView transactions_recyclerView;
    ArrayList<UserTransaction> transactionsList;
    UserTransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);

        setTitle("All Transactions");

        transactions_recyclerView = findViewById(R.id.transactions_recyclerView);

        Intent intent = getIntent();
        email = intent.getStringExtra("user_email");

        transactionsList = new ArrayList<>();
        getUserDetails();

        transactionAdapter = new UserTransactionAdapter(transactionsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        transactions_recyclerView.setLayoutManager(mLayoutManager);
        transactions_recyclerView.setItemAnimator(new DefaultItemAnimator());
        transactions_recyclerView.setAdapter(transactionAdapter);

    }

    private void getUserDetails()
    {
        //mobile = et_mobileNum.getText().toString();
        Log.d(TAG, "Email from Intent: " +email);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        //call = api.getData(startDate,endDate,budget,type,groupName,member);

        call = api.getTransactionDetails(email);
        getUserTransationDetails();
    }

    private void getUserTransationDetails()
    {
        call.enqueue(new Callback<ArrayList<UserTransaction>>()
        {
            @Override
            public void onResponse(@NotNull Call<ArrayList<UserTransaction>> call, @NotNull Response<ArrayList<UserTransaction>> response)
            {
                Log.i(TAG, "response code from Server: " + response.code());
                Log.i(TAG, "response from Server: " + response.body());
                Log.i(TAG, "response code from Server: " + response.code());

                if (response.isSuccessful())
                {
                    try
                    {
                        transactionsList.addAll(response.body());
                        transactionAdapter.notifyDataSetChanged();
                        //adapter.notifyDataSetChanged();

                        Log.i(TAG, "responseBody from Server: "+transactionsList);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Log.i(TAG, "Exception occurred: "+e.getMessage());
                    }
                }
                else {
                    Log.i(TAG, "response from Server: " + response.toString());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<UserTransaction>> call, @NotNull Throwable t)
            {
                Toast.makeText(getApplicationContext(),"Failed to connect to the web-service",Toast.LENGTH_LONG).show();
                t.printStackTrace();
                Log.i(TAG, "Exception occurred: "+t.getMessage());
            }
        });
    }
}
