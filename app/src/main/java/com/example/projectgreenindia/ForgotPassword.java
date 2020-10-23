package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity
{
    private static final String TAG = "ForgotPassword";
    EditText et_FP_Password,et_FP_ConfirmPassword;
    Button btn_FP_ResetPassword;
    String email,password,confirm_password,otp;
    String mobile,pass,msg;
    Api api;
    Call<ResponseBody> call;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setTitle("Change Password");

        Intent intent = getIntent();
        //email = intent.getStringExtra("user_email");
        otp = intent.getStringExtra("otp");
        mobile = intent.getStringExtra("user_mobile");
        //pass = intent.getStringExtra("user_password");

        et_FP_Password = findViewById(R.id.et_FP_Password);
        et_FP_ConfirmPassword = findViewById(R.id.et_FP_ConfirmPassword);
        btn_FP_ResetPassword = findViewById(R.id.btn_FP_ResetPassword);
    }

    public void fp_forgotPassword(View view)
    {
        password = et_FP_Password.getText().toString();
        confirm_password = et_FP_ConfirmPassword.getText().toString();

        //http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/changePassword

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/rest/plant/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);


        checkPass();
        //startActivity(new Intent(getApplicationContext(),Login_Activity.class));
    }

    public void checkPass()
    {
        if((password != null || password.trim().isEmpty())  && (confirm_password != null || confirm_password.trim().isEmpty()))
        {
            if(password.equals(confirm_password))
            {
                HashMap<String, String> requestBody = new HashMap<>();
                //requestBody.put("Content-Type", "application/json");
                //headerMap.put("password", pass);
                //headerMap.put("mobile", mobile);

/*              requestBody.put("password", "12345678");
                requestBody.put("mobile", "9742875630");
*/
                Log.d(TAG,"password: "+password);
                Log.d(TAG,"mobile: "+mobile);

                requestBody.put("password", password);
                requestBody.put("mobile", mobile);

                call = api.changePassword(requestBody);
                //call = api.changePassword(headerMap,"12345678","9742875630");

                call.enqueue(new Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
                    {
                        int resCode = response.code();
                        msg = String.valueOf(response.body());

                        Log.i(TAG,"Response_Code: "+response.code() + ", is Response Successful: "+response.isSuccessful());

                        if(resCode == 200)
                        {
                            Log.d("travel", "response Code: " + resCode);
                            Log.d("travel", "response from Server: " + msg);

                            if (msg != null)
                            {
                                Log.d(TAG, "response Body from Server: " +msg);
                                Toast.makeText(getApplicationContext(), "Password Reset Successfull", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Password Reset Un-Successfull", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t)
                    {
                        Toast.makeText(getApplicationContext(),"Could not establish connection to Server "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Password & Confirm password does not match",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Password & Confirm password both fields are mandatory",Toast.LENGTH_LONG).show();
        }
    }
}
