package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity
{
    ImageView splash_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_imageView = findViewById(R.id.splash_imageView);
        splash_imageView.setImageResource(R.drawable.plant_logo);

        int SPLASH_DISPLAY_LENGTH = 2000;

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent mainIntent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
