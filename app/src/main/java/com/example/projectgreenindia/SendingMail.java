package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendingMail extends AppCompatActivity
{
    private static final String TAG = "MailSendingTest";
    EditText et_mailTo;
    Button btn_Send;
    TextView tv_SM_refferalCode;
    String description,subject,ref,refferedby,invitation_status,status;
    List<String> usersList,email;
    Call<ResponseBody> call;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_mail);

        setTitle("Send Mail");

        Intent intent = getIntent();
        ref = intent.getStringExtra("refferal_code");
        refferedby = intent.getStringExtra("sender_user_email");

        et_mailTo = findViewById(R.id.et_mailTo);
        btn_Send = findViewById(R.id.btn_Send);
        tv_SM_refferalCode = findViewById(R.id.tv_SM_refferalCode);

        tv_SM_refferalCode.setText(ref);
    }

    public void sendMail(View view)
    {
        String recepientList = et_mailTo.getText().toString();
        String[] recepients = recepientList.split(",");

        usersList = new ArrayList<>();
        email = new ArrayList<>();
        usersList = Arrays.asList(recepients);

        //List<Integer> list = new ArrayList<Integer>(Arrays.asList(values));

        //String link = "https://goo.gl/2LzPFU";

        String link = "https://drive.google.com/file/d/1-52A26Ls07SDw8ABG97AD1fyAxz7vNxV/view?usp=drivesdk";

        // Log.i(TAG, "Sending email to: "+recepients.toString());
        Log.i(TAG, "Sending email to: "+usersList);

        email.addAll(usersList);
        Log.i(TAG, "Sending email to: "+email);

        sendInivitation(refferedby,email);

        subject = " You are invited to join hands with Project_Green_India";

        description = "Note: Community campaign: Plant trees - save lives. \"Live your life with joy and give children a clean environment to live as well"+"\n \n"+
                "Plant a tree through our community App - "+link+" and website - Community.co.in "+ "\n \n"+
                "Refferal Code: "+ref+"\n \n"+
                "Regards."+"\n \n";

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        //emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String []{"acufore.software@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recepients);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, description);
        emailIntent.setType("message/rfc822");

        try
        {
            startActivity(Intent.createChooser(emailIntent,"Send email using..."));
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendInivitation(String refferedby, List<String> email)
    {
        Retrofit retrofit = new Retrofit.Builder()
                //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/invite
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        //call = api.getData(startDate,endDate,budget,type,groupName,member);

        //HashMap<String, String> headerMap = new HashMap<>();
        //headerMap.put("Content-Type", "application/json");

        HashMap<String, String> body = new HashMap<>();
        body.put("referredby",refferedby);
        body.put("email",email.toString());

        //call = api.sendInvite(headerMap,body);
        call = api.sendInvite(body);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                if(response.isSuccessful())
                {
                    try
                    {
                        Log.i(TAG,"Response from Server: "+response.toString());

                        status = response.body().string();

                        if (status != null)
                        {
                            String invite_status = parseData(status);
                            Log.i(TAG,"Invitation_status: "+invite_status);

                            //Toast.makeText(getApplicationContext(),"Successfully to sent the invitation: "+invite_status,Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Log.i(TAG,"response from Server: "+response.errorBody());
                    Toast.makeText(getApplicationContext(),"Failed to send invitation",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t)
            {
                Log.i(TAG,"Failed to fetch Refferal Code: "+t.getMessage());
                Toast.makeText(getApplicationContext(),"Failed to send invitation", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String parseData(String rcode)
    {
        JSONObject jsonObject;
        try
        {
            jsonObject = new JSONObject(rcode);
            invitation_status = jsonObject.getString("success");

            Log.i(TAG,"Invitation Status: "+invitation_status);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return invitation_status;
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        startActivity(new Intent(getApplicationContext(),User_Dashboard.class));
    }
}
