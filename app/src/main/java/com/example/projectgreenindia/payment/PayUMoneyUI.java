package com.example.projectgreenindia.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectgreenindia.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayUMoneyUI extends AppCompatActivity
{
    String c_email,c_mobile,c_amount;
    public static final String TAG = "PayUMoneyUI";

    TextInputLayout email_til, mobile_til;
    EditText email_et, mobile_et, amount_et;
    Button pay_now_button;
    public static final String PHONE_PATTERN = "^[987]\\d{9}$";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payumoney_ui);

        setTitle("Checkout");

        email_et = findViewById(R.id.email_et);
        mobile_et = findViewById(R.id.mobile_et);
        amount_et = findViewById(R.id.amount_et);
        pay_now_button = findViewById(R.id.pay_now_button);
        email_til = findViewById(R.id.email_til);
        mobile_til = findViewById(R.id.mobile_til);

        amount_et.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(7, 2)});

        Intent intent = getIntent();
        c_email = intent.getStringExtra("user_email");
        c_mobile = intent.getStringExtra("user_mobile");
        c_amount = intent.getStringExtra("user_amount");

        Log.i(TAG,"email: "+c_email+", mobile: "+c_mobile+", amount: "+c_amount);

        if(c_email != null || c_mobile != null || c_amount != null)
        {
            email_et.setText(c_email);
            mobile_et.setText(c_mobile);
            amount_et.setText(c_amount);
        }

        initListeners();

        //amount_et.setText("100");
    }

    private void initListeners()
    {
        email_et.addTextChangedListener(new EditTextInputWatcher(email_til));
        mobile_et.addTextChangedListener(new EditTextInputWatcher(mobile_til));
    }

    public static void setErrorInputLayout(EditText editText, String msg, TextInputLayout textInputLayout)
    {
        textInputLayout.setError(msg);
        editText.requestFocus();
    }

    public static boolean isValidEmail(String strEmail)
    {
        return strEmail != null && android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches();
    }

    public static boolean isValidPhone(String phone)
    {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);

        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean validateDetails(String email, String mobile)
    {
        email = email.trim();
        mobile = mobile.trim();

        if (TextUtils.isEmpty(mobile))
        {
            setErrorInputLayout(mobile_et, getString(R.string.err_phone_empty), mobile_til);
            return false;
        }
        else if (!isValidPhone(mobile))
        {
            setErrorInputLayout(mobile_et, getString(R.string.err_phone_not_valid), mobile_til);
            return false;
        }
        else if (TextUtils.isEmpty(email))
        {
            setErrorInputLayout(email_et, getString(R.string.err_email_empty), email_til);
            return false;
        }
        else if (!isValidEmail(email))
        {
            setErrorInputLayout(email_et, getString(R.string.email_not_valid), email_til);
            return false;
        }
        else {
            return true;
        }
    }

    public static class EditTextInputWatcher implements TextWatcher
    {
        private TextInputLayout textInputLayout;

        EditTextInputWatcher(TextInputLayout textInputLayout)
        {
            this.textInputLayout = textInputLayout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if (s.toString().length() > 0)
            {
                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
            }
        }
    }

    public class DecimalDigitsInputFilter implements InputFilter
    {
        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero)
        {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
        {
            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }
    }

    public void payment_now(View view)
    {
        Toast.makeText(getApplicationContext(),"Payment has been completed",Toast.LENGTH_LONG).show();

/*
        c_email = email_et.getText().toString();
        c_mobile = mobile_et.getText().toString();
        c_amount = amount_et.getText().toString();

        if(Integer.parseInt(c_amount)<100)
        {
            c_amount = "100";
        }

        if(validateDetails(c_email, c_mobile))
        {
            Intent intent = new Intent(PayUMoneyUI.this, StartPaymentActivity.class);
            intent.putExtra("email",c_email);
            intent.putExtra("phone",c_mobile);
            intent.putExtra("amount",c_amount);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please enter valid details",Toast.LENGTH_LONG).show();
        }
 */

    }

}
