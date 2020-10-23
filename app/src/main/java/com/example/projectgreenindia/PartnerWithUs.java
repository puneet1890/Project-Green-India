package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PartnerWithUs extends AppCompatActivity
{
    Button btn_Partner_Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_with_us);

        setTitle("Partner with Us");

        btn_Partner_Submit = findViewById(R.id.btn_Partner_Submit);
    }

    public void partner_submit(View view)
    {
        Toast.makeText(getApplicationContext(),"Your Interests have been recorded, You" +
                "will soon be contacted from Our Team regarding the affiliation with us",Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(),User_Dashboard.class));
    }
}
