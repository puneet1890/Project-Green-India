package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity
{
    Button btn_help_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        btn_help_submit = findViewById(R.id.btn_help_submit);

        setTitle("Help");
    }

    public void submit_query(View view)
    {
        Toast.makeText(getApplicationContext(),"Your Feedback is submitted, You'll soon be contacted by our team",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(),User_Dashboard.class));
    }
}
