package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.projectgreenindia.payment.PayUMoneyUI;

public class JoinTheCommunity extends AppCompatActivity
{
    private static final String TAG = "Join the Community";
    Button btn_payment;
    //List<Plant> plantList;
    GridLayout mainGrid;
    boolean mybool;
    static int rs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_the_community);

        setTitle("Join The Community");
        mainGrid = findViewById(R.id.mainGrid);
        btn_payment = findViewById(R.id.btn_payment);

        //setSingleEvent(mainGrid);
        setToggleEvent(mainGrid);
    }

    private void setToggleEvent(final GridLayout mainGrid)
    {
        for(int i=0;i<mainGrid.getChildCount();i++)
        {
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1)
                    {
                        mybool = true;

                        if(rs>40)
                        {
                            rs = rs%40;
                        }
                        rs = rs + 10;
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        //Toast.makeText(getApplicationContext(),"State : "+mybool,Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        mybool = false;

                        if(rs>0)
                        {
                            rs = rs - 10;
                            mybool = true;
                        }

                        if(rs<=0)
                        {
                            mybool = false;
                        }

                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        //Toast.makeText(getApplicationContext(),"State : "+mybool,Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void make_payment(View view)
    {
        if(mybool==true)
        {
            Log.i(TAG,"Total Rs: "+rs);
            //Toast.makeText(getApplicationContext(),"Payment successfully completed",Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getApplicationContext(), PayUMoneyUI.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please select alteast one plant", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
