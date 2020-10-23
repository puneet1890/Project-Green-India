package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectgreenindia.payment.PayUMoneyUI;

public class ContributeActivity extends AppCompatActivity
{
    private static final String TAG = "Contribute Activity";
    String email,mobile,amount;
    EditText et_Contribute_Amount;
    Button btn_contribute_payment;
    int amt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);

        setTitle("Contribute");

        Intent intent = getIntent();
        email = intent.getStringExtra("user_email");
        mobile = intent.getStringExtra("user_mobile");

        et_Contribute_Amount = findViewById(R.id.et_Contribute_Amount);
        btn_contribute_payment = findViewById(R.id.btn_contribute_payment);
    }

    public void contribute_make_payment(View view)
    {
        amount = et_Contribute_Amount.getText().toString();

        String amt = calculate(amount);
/*
        if(amt == null)
        {
            amt = "100";
        }
*/
        Intent intent = new Intent(getApplicationContext(), PayUMoneyUI.class);
        intent.putExtra("user_email",email);
        intent.putExtra("user_mobile",mobile);
        intent.putExtra("user_amount",amt);

        Log.i(TAG,"email: "+email+", mobile: "+mobile+", amount: "+amount);
        startActivity(intent);
    }

    private String calculate(String amount)
    {
        if(!(amount.isEmpty() | amount == null))
        {
            amt = Integer.parseInt(amount);

            if(amt <100)
            {
                return "100";
            }
            else
            {
                return amount;
            }
        }
        else
        {
            return "100";
        }

    }
}
