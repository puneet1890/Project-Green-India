package com.example.projectgreenindia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_Activity extends AppCompatActivity
{
    private static final String TAG = "Login Activity";
    EditText et_Login_Email,et_Login_Password;
    Button btn_Login_Login,btn_Login_ForgotPassword;
    Api api;
    Call<User> call;
    ImageView login_imgView;
    String email,password,mobile,pass,mob,ref,abool,name;
    //HashMap<String, String> pramsMap;
    User user;
    boolean flag_first_time = false;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        et_Login_Email = findViewById(R.id.et_Login_Email);
        et_Login_Password = findViewById(R.id.et_Login_Password);
        btn_Login_Login = findViewById(R.id.btn_Login_Login);
        btn_Login_ForgotPassword = findViewById(R.id.btn_Login_ForgotPassword);
        login_imgView = findViewById(R.id.login_imgView);

        login_imgView.setImageResource(R.drawable.login_bg);

        Intent intent = getIntent();
        flag_first_time = intent.getBooleanExtra("flag" ,false);

        if(flag_first_time)
        {
            user = (User) intent.getSerializableExtra("user");
            mobile = user.getMobile_no();

            Log.i(TAG,"First Time: Mobile number "+mobile);
        }
    }

    public void login_login(View view)
    {
        email = et_Login_Email.getText().toString();
        password = et_Login_Password.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        //call = api.getData(startDate,endDate,budget,type,groupName,member);

        call = api.getData(email,password);

        login();
    }

    void login()
    {
        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response)
            {
                Log.i(TAG,"Response from Server: "+response.toString());
                //Log.i(TAG,"Response Body from Server: "+response.body().toString());

                if(response.isSuccessful())
                {
                    Log.i(TAG,"Response from Server: "+response.toString());
                    Log.i(TAG,"Response Body from Server: "+response.body().toString());

                    User user = response.body();
                    Log.i(TAG,"User from Login: "+user);

                    pass = user.getPassword();
                    mob = user.getMobile_no();
                    ref = user.getRefferal_code();
                    abool =  user.getDisplayContribute();
                    email = user.getEmail();
                    name = user.getName();

                    Log.i(TAG,"Password: "+pass);
                    Log.i(TAG,"Mobile: "+mob);
                    Log.i(TAG,"Refferal Code: "+ref);
                    Log.i(TAG,"Display Contribute: "+abool);
                    Log.i(TAG,"Email: "+email);
                    Log.i(TAG,"Name: "+name);

                    pref = getApplicationContext().getSharedPreferences("MyPref",0);
                    editor = pref.edit();

                    editor.putString("user_email",email);
                    editor.putString("user_ref",ref);
                    editor.putString("user_contribute",abool);
                    editor.putString("user_mobile",mob);
                    editor.putString("user_name",name);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),User_Dashboard.class);
                    intent.putExtra("User_Email",email);
                    intent.putExtra("user_ref",ref);
                    intent.putExtra("user_pass",pass);
                    intent.putExtra("user_contribute",abool);
                    intent.putExtra("user_mobile",mob);
                    intent.putExtra("user_name",name);
                    startActivity(intent);
                }
                else
                {
                    Log.i(TAG,"Response from Server: "+response.errorBody());
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t)
            {
                Log.i(TAG,"Failed to Login: "+t.getMessage());
                Toast.makeText(getApplicationContext(),"Failed to Login: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void login_forgotPassword(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Validate_OTP.class);
        intent.putExtra("user_mobile",mob);
        intent.putExtra("user_password",pass);
        startActivity(intent);
    }
}
