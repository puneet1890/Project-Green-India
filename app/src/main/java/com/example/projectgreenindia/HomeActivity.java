package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity
{
    Button btn_Home_Reg, btn_Home_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_Home_Login = findViewById(R.id.btn_Home_Login);
        btn_Home_Reg = findViewById(R.id.btn_Home_Reg);

        setTitle("HomePage");
    }

    public void home_reg(View view)
    {
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
    }

    public void home_login(View view)
    {
        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
    }
}