package com.example.projectgreenindia;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class OTP_Receiver
{
/*
    private static final String TAG = "OTP_Receiver";
    public EditText editText;
    Context ctx;
    String otp;

    public OTP_Receiver(){}

    public OTP_Receiver(Context context)
    {
        this.ctx = context;
    }

/*
    public OTP_Receiver(Context context, EditText et)
    {
        this.context = context;
        this.editText = et;
    }
*/

/*
    public void setEditText(EditText editText)
    {
        this.editText = editText;
    }
*/
    //public String getEditText() { return editText.getText().toString(); }

/*
    @Override
    public void onReceive(Context context, Intent intent)
    {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for (SmsMessage sms : messages)
        {
            String message = sms.getMessageBody();
            otp = message.split(": ")[1];
            Log.i(TAG,"OTP: "+otp);

            try
            {
                editText = ((Activity)ctx).findViewById(R.id.et_otpNumber);
                editText.setText(otp);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.d(TAG,"Error while setting OTP: "+e.getMessage());
            }
        }
    }
*/
}
