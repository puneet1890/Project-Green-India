package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Join_the_cause extends AppCompatActivity
{
    Button btn_cause_join;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_the_cause);

        setTitle("Join The Cause");

        btn_cause_join = findViewById(R.id.btn_cause_join);
        btn_cause_join.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),JoinTheCommunity.class));
            }
        });
    }
}
