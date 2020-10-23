package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.nio.channels.InterruptedByTimeoutException;

import de.hdodenhof.circleimageview.CircleImageView;

public class WalletActivity extends AppCompatActivity
{
    CircleImageView circleImageView;
    String name,mobile,ref,email;

    TextView tv_UserName,tv_UserMobile,tv_UserRefferalCode,tv_UserEmail;
    ProfileDBHelper dbHelper;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        tv_UserName = findViewById(R.id.tv_UserName);
        tv_UserMobile = findViewById(R.id.tv_UserMobile);
        tv_UserRefferalCode = findViewById(R.id.tv_UserRefferalCode);
        tv_UserEmail = findViewById(R.id.tv_UserEmail);

        setTitle("Wallet");

        circleImageView = findViewById(R.id.wallet_profile_image);
        circleImageView.setImageResource(R.drawable.ic_wallet_user_pic);

        dbHelper = new ProfileDBHelper(this);

        Intent intent = getIntent();
        name = intent.getStringExtra("user_name");
        mobile = intent.getStringExtra("user_mobile");
        ref = intent.getStringExtra("user_ref");
        email = intent.getStringExtra("user_email");

        tv_UserName.setText(name);
        tv_UserMobile.setText(mobile);
        tv_UserRefferalCode.setText(ref);
        tv_UserEmail.setText(email);

        String imageUri = dbHelper.getProfileImage(email);

        if(imageUri != null)
        {
            Uri uri = Uri.parse(imageUri);
            circleImageView.setImageURI(uri);
        }
        else
        {
            circleImageView.setImageResource(R.drawable.ic_wallet_user_pic);
        }
    }

    public void wallet_logout(View view)
    {
        pref = getApplicationContext().getSharedPreferences("MyPref",0);
        editor = pref.edit();

        editor.remove("user_email");
        editor.remove("user_ref");
        editor.remove("user_contribute");
        editor.remove("user_mobile");
        editor.remove("user_name");

        editor.clear();
        editor.apply();

        Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finishAffinity();
    }

    public void wallet_help(View view)
    {
        startActivity(new Intent(getApplicationContext(),HelpActivity.class));
    }

    public void wallet_change_pwd(View view)
    {
        startActivity(new Intent(getApplicationContext(),Validate_OTP.class));
    }

    public void wallet_transactions(View view)
    {
/*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setMessage("Please choose your Favourite Screen");

        alertDialogBuilder.setNegativeButton("ScreenOne",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Intent go = new Intent(getApplicationContext(),MainActivity.class);

                // you pass the position you want the viewpager to show in the extra,
                // please don't forget to define and initialize the position variable
                // properly
                //go.putExtra("viewpager_position", 0);

                //startActivity(go);
            }
        });

        alertDialogBuilder.setPositiveButton("User Histroy", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                // Create new fragment and transaction

                /*SecondFragment newFragment = new SecondFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment, "NewFragment");
                transaction.addToBackStack(null);
                transaction.commit();*/
        // You don't do the above code, it will just create a new fragment
        // you do this instead.

  /*              Intent go = new Intent(WalletActivity.this,UserPlantsHistroyFragment.class);

                // you pass the position you want the viewpager to show in the extra,
                // please don't forget to define and initialize the position variable
                // properly
                go.putExtra("viewpager_position", 1);

                startActivity(go);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
*/
        //});

        Intent intent = new Intent(getApplicationContext(),User_History.class);
        intent.putExtra("user_email",email);
        startActivity(intent);
    }

    public void wallet_add_mobileNum(View view)
    {
        //startActivity(new Intent(getApplicationContext(),AddMobileNumActivity.class));
    }

    public void wallet_edit_profile(View view)
    {
        Intent intent = new Intent(getApplicationContext(),WalletUserProfile.class);
        intent.putExtra("user_name",name);
        intent.putExtra("user_mobile",mobile);
        intent.putExtra("user_ref",ref);
        intent.putExtra("user_email",email);

        startActivity(intent);
    }
}
