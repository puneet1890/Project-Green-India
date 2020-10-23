package com.example.projectgreenindia;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlantDetailsFragment extends Fragment
{
    private View rootView;
    static String email;
    public static final String TAG = "PlantDetailsFragment";

    Api api;
    private Call<ArrayList<PlantationsDetail>> call;
    ArrayList<PlantationsDetail> plantDetailsList;
    PlantDetailsAdapter adapter;

    public PlantDetailsFragment() { }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_plant_details, container, false);
        plantDetailsList = new ArrayList<>();

        Log.i(TAG,"Email in PlantDetails_Fragment: "+email);

        getPlantationDetails();

        return rootView;
    }

    private void getPlantationDetails()
    {
        //mobile = et_mobileNum.getText().toString();
        Log.d(TAG, "Email from Intent: " +email);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

        call = api.getPlantationDetail(email);

        getUserPlantationDetails();
    }

    private void getUserPlantationDetails()
    {
        call.enqueue(new Callback<ArrayList<PlantationsDetail>>()
        {
            @Override
            public void onResponse(@NotNull Call<ArrayList<PlantationsDetail>> call, @NotNull Response<ArrayList<PlantationsDetail>> response)
            {
                Log.i(TAG, "response code from Server: " + response.code());
                Log.i(TAG, "response from Server: " + response.body());
                Log.i(TAG, "response code from Server: " + response.code());

                if (response.isSuccessful())
                {
                    try
                    {
                        plantDetailsList.addAll(response.body());
                        adapter.notifyDataSetChanged();

                        Log.i(TAG, "responseBody from Server: "+plantDetailsList);

                        if(plantDetailsList.isEmpty())
                        {
                            Toast.makeText(getActivity(),"Take your first step and book a plant",Toast.LENGTH_LONG).show();
                        }
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
            public void onFailure(@NotNull Call<ArrayList<PlantationsDetail>> call, @NotNull Throwable t)
            {
                Toast.makeText(getActivity(),"Failed to connect to the web-service",Toast.LENGTH_LONG).show();
                t.printStackTrace();
                Log.i(TAG, "Exception occurred: "+t.getMessage());
            }
        });
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG,"Email in PlantDetails_Fragment: "+email);
/*
        final ArrayList<PlantDetails> plantDetailsList = new ArrayList<>();

        plantDetailsList.add(new PlantDetails("Neem Tree:", "Bidadi", "10/02/2020", "XX Rs", R.drawable.ic_plant_details));
        plantDetailsList.add(new PlantDetails("Mango Tree:", "Ramanagara", "01/02/2020", "XX Rs", R.drawable.ic_plant_details));
        plantDetailsList.add(new PlantDetails("Peepal Tree:", "Channapatna", "20/01/2020", "XX Rs", R.drawable.ic_plant_details));
        plantDetailsList.add(new PlantDetails("Teak Tree:", "Bangalore", "10/01/2020", "XX Rs", R.drawable.ic_plant_details));
*/
        adapter = new PlantDetailsAdapter(plantDetailsList,getActivity());

        final RecyclerView plantDetailsRecyclerView = rootView.findViewById(R.id.plantDetails_RecyclerView);
        plantDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(PlantDetailsFragment.this.getActivity()));
        plantDetailsRecyclerView.setHasFixedSize(true);
        plantDetailsRecyclerView.setAdapter(adapter);
    }

}
