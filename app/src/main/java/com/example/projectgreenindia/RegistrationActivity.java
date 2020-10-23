package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity
{
    private static final String TAG = "RegistrationActivity";
    EditText et_Reg_Name,et_Reg_Email,et_Reg_MobileNo,et_Reg_RefferalCode,et_Reg_Password;
    Button btn_Reg_Register,btn_Reg_Login;
    ImageView reg_imgView;
    User user,user1;
    String name,email,mobile_no,refferal_code,password,displayContribute;
    Api api;
    Call<User> call;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        reg_imgView = findViewById(R.id.reg_imgView);
        reg_imgView.setImageResource(R.drawable.reg_bg);

        setTitle("Registration");

        et_Reg_Name = findViewById(R.id.et_Reg_Name);
        et_Reg_Email = findViewById(R.id.et_Reg_Email);
        et_Reg_MobileNo = findViewById(R.id.et_Reg_MobileNo);
        et_Reg_RefferalCode = findViewById(R.id.et_Reg_RefferalCode);
        btn_Reg_Login = findViewById(R.id.btn_Reg_Login);
        btn_Reg_Register = findViewById(R.id.btn_Reg_Register);
        et_Reg_Password = findViewById(R.id.et_Reg_Password);

    }

    public void reg_register(View view)
    {
        name = et_Reg_Name.getText().toString();
        email = et_Reg_Email.getText().toString();
        mobile_no = et_Reg_MobileNo.getText().toString();
        refferal_code = et_Reg_RefferalCode.getText().toString();
        password = et_Reg_Password.getText().toString();

        //String name,email,mobile_no,refferal_code,password;

        if(name.isEmpty()||email.isEmpty()||mobile_no.isEmpty()||password.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter valid details",Toast.LENGTH_LONG).show();
        }
        else
        {
            user = new User(name,email,mobile_no,refferal_code,password,displayContribute);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(Api.class);

            // HashMap<String, String> headerMap = new HashMap<>();
            // headerMap.put("Content-Type", "application/json");

            call = api.handleRegister(user);
            //call = api.handleRegister(headerMap, user);
            doregister();
        }
    }

    public void doregister()
    {
        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response)
            {
                int resCode = response.code();
                user1 = response.body();

                Log.i(TAG,"Response_Code: "+resCode + ", is Response Successful: "+response.isSuccessful());
                //Log.d(TAG, "User Bean: " + user.toString());

                if(resCode == 200)
                {
                    if (user1 != null)
                    {
                        Log.d(TAG, "User Bean: " + user1.toString());
                        //Log.d(TAG, "response Body from Server: "+response.body().toString());

                        Toast.makeText(getApplicationContext(), "User created successfully ", Toast.LENGTH_LONG).show();
/*
                        SharedPreferences sharedPref = getSharedPreferences("myPref",0);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("admin_mobile", mobile_no);
                        editor.apply();
*/
                        Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
                        intent.putExtra("flag",true);
                        intent.putExtra("user",user1);
                        Log.i(TAG,"Passing user to Login Activity: "+user1);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Could not create User profile ", Toast.LENGTH_LONG).show();
                    }
                }
                else if(resCode == 201)
                {
                    Toast.makeText(getApplicationContext(), "User profile already exists, please create new user", Toast.LENGTH_LONG).show();
                }
                else if(resCode == 400)
                {
                    Toast.makeText(getApplicationContext(), "Enter valid Email/Mobile Number, Mobile Number must be of 10 digits", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error occurred while creating user profile, please contact admin "+response.errorBody(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t)
            {
                Toast.makeText(getApplicationContext(),"Could not establish connection to Server "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void reg_login(View view)
    {
        startActivity(new Intent(getApplicationContext(),Login_Activity.class));
    }
}
