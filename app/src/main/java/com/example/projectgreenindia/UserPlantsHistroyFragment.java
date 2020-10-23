package com.example.projectgreenindia;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class UserPlantsHistroyFragment extends Fragment
{
    public UserPlantsHistroyFragment() { }

    static String email;
    public static final String TAG = "UserPlantsHistroy";
    Api api;
    private Call<ResponseBody> call;
    private TextView tv_UPHF_Referals,tv_UPHF_Plantations,tv_UPHF_Contributions,tv_UPHF_Earnings;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_user_plants_histroy, container, false);

        Log.i(TAG,"Email in User History Fragment: "+email);

        tv_UPHF_Referals = rootView.getRootView().findViewById(R.id.tv_UPHF_Referals);
        tv_UPHF_Plantations = rootView.getRootView().findViewById(R.id.tv_UPHF_Plantations);
        tv_UPHF_Contributions = rootView.getRootView().findViewById(R.id.tv_UPHF_Contributions);
        tv_UPHF_Earnings = rootView.getRootView().findViewById(R.id.tv_UPHF_Earnings);

        getUserDetails();

        return rootView;
    }

/*
     public void setEmail(final String email)
    {
        UserPlantsHistroyFragment.email = email;
    }

*/

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

        call = api.getUserHistory(email);
        getUserHistroyDetails();
    }

    private void getUserHistroyDetails()
    {
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                Log.i(TAG, "response code from Server: " + response.code());

                if (response.isSuccessful())
                {
                    try
                    {
                        String responseStr = response.body().string();
                        Log.i(TAG, "responseBody String from Server: "+responseStr);

                        UserHistoryBean bean = parseData(responseStr);
                        Log.i(TAG,"UserHistory Bean: "+bean);

                        tv_UPHF_Referals.setText(bean.getReferals());
                        tv_UPHF_Plantations.setText(bean.getPlant());
                        tv_UPHF_Contributions.setText(bean.getContributions());
                        tv_UPHF_Earnings.setText(bean.getEarning());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.i(TAG, "response from Server: " + response.toString());
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
                Log.i(TAG, "Failed to receive OTP: " + t.getMessage());
                Toast.makeText(getActivity(), "Failed to receive OTP: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private UserHistoryBean parseData(String responseStr)
    {
        UserHistoryBean bean;
        JSONObject jsonObject;
        try
        {
            jsonObject = new JSONObject(responseStr);

            String referals = jsonObject.getString("referals");
            String plant = jsonObject.getString("plant");
            String contributions = jsonObject.getString("contributions");
            String earning = jsonObject.getString("earning");

            bean = new UserHistoryBean(referals,plant,contributions,earning);
            Log.i(TAG,"UserHistory Bean: "+bean);

            return bean;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
