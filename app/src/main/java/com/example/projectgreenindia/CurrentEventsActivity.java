package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CurrentEventsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_events);

        setTitle("Current Events & Programs");
    }
}
