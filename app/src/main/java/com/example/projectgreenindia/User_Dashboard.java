package com.example.projectgreenindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class User_Dashboard extends AppCompatActivity
{
    private static final String TAG = "User Dashboard";
    //ViewFlipper v_Flipper;
    ImageView img_dash;
    Button btn_Join_Contribute,btn_Invite_Earn,btn_See_It_Works,btn_Partner_With_Us;
    String email,ref,pass,displayContribute,mobile,name;
    int doubleBackToExitPressed = 1;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId())
        {
            case R.id.navigation_home:
                //mTextMessage.setText(R.string.title_home);
                startActivity(new Intent(getApplicationContext(), User_Dashboard.class));
                return true;

            case R.id.navigation_myplant:
                //mTextMessage.setText(R.string.title_myPlant);
                Intent intent = new Intent(getApplicationContext(), MyPlantActivity.class);
                Log.i(TAG,"Email in User Dashboard: "+email);
                intent.putExtra("user_email",email);
                startActivity(intent);
                return true;

            case R.id.navigation_discover:
                //mTextMessage.setText(R.string.title_discover);
                startActivity(new Intent(getApplicationContext(), DiscoverActivity.class));
                return true;

            case R.id.navigation_wallet:
                //mTextMessage.setText(R.string.title_wallet);
                Intent walletintent = new Intent(getApplicationContext(), WalletActivity.class);
                walletintent.putExtra("user_name",name);
                walletintent.putExtra("user_mobile",mobile);
                walletintent.putExtra("user_ref",ref);
                walletintent.putExtra("user_email",email);
                startActivity(walletintent);
                return true;

        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        setTitle("User Dashboard");

        BottomNavigationView navView = findViewById(R.id.nav_view);

        //mTextMessage = findViewById(R.id.message);
        img_dash = findViewById(R.id.img_dash);
        btn_Join_Contribute = findViewById(R.id.btn_Join_Contribute);
        btn_Invite_Earn = findViewById(R.id.btn_Invite_Earn);
        btn_See_It_Works = findViewById(R.id.btn_See_It_Works);
        btn_Partner_With_Us = findViewById(R.id.btn_Partner_With_Us);
        //v_Flipper = findViewById(R.id.v_Flipper);

        img_dash.setImageResource(R.drawable.dashboard_header);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

/*        int[] images = {R.drawable.dashboard_header};

        for(int image:images)
        {
            flipperImages(image);
        }
*/
        pref = getApplicationContext().getSharedPreferences("MyPref",0);
        //editor = pref.edit();

        email = pref.getString("user_email",null);
        ref = pref.getString("user_ref",null);
        displayContribute = pref.getString("user_contribute",null);
        mobile = pref.getString("user_mobile",null);
        name = pref.getString("user_name",null);

/*
        Intent intent = getIntent();
        email = intent.getStringExtra("User_Email");
        ref = intent.getStringExtra("user_ref");
        pass = intent.getStringExtra("user_pass");
        displayContribute = intent.getStringExtra("user_contribute");
        mobile = intent.getStringExtra("user_mobile");
        name = intent.getStringExtra("user_name");
*/

        Log.i(TAG,"Email: "+email);
        Log.i(TAG,"Referral Code: "+ref);
        Log.i(TAG,"Password: "+pass);
        Log.i(TAG,"displyContribute: "+displayContribute);
        Log.i(TAG,"mobile: "+mobile);
        Log.i(TAG,"name: "+name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() 
    {
        if (doubleBackToExitPressed == 2) 
        {
            finishAffinity();
            System.exit(0);
        }
        else {
            doubleBackToExitPressed++;
            Toast.makeText(this, "Please press Back again to exit", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() 
        {
            @Override
            public void run()
            {
                doubleBackToExitPressed=1;
                // `enter code here`
            }
        }, 2000);
    }

/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            // do something on back.
            Toast.makeText(getApplicationContext(),"Press twice to exit",Toast.LENGTH_LONG).show();
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 1)
        {
            // do something on back.
            Toast.makeText(getApplicationContext(),"Back Button pressed twice",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //How to intercept the BACK key in an Activity is also one of the common questions we see //developers ask, so as of 2.0 we have a new little API to make this more simple and easier //to discover and get right:

    @Override
    public void onBackPressed()
    {
        return;
    }

*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_notifications:
                Toast.makeText(getApplicationContext(),"No New Notifications",Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_logout:

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

                break;

            default: return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setBackgroundResource(image);

        v_Flipper.addView(imageView);
        v_Flipper.setFlipInterval(2000);
        v_Flipper.setAutoStart(true);

        //animation
        v_Flipper.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
        v_Flipper.setOutAnimation(getApplicationContext(),android.R.anim.slide_out_right);
    }
*/

    public void join_the_community(View view)
    {
        if(displayContribute != null)
        {
            if(displayContribute.equals("true"))
            {
                Intent intent = new Intent(getApplicationContext(),ContributeActivity.class);
                intent.putExtra("user_email",email);
                intent.putExtra("user_mobile",mobile);
                startActivity(intent);
            }
            else
            {
                startActivity(new Intent(getApplicationContext(),Join_the_cause.class));
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please Logout & Login again to the Application",Toast.LENGTH_LONG).show();
            //startActivity(new Intent(getApplicationContext(),Join_the_cause.class));
        }

    }

    public void invite_earn(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Invite_Earn_Activity.class);
        intent.putExtra("refferal_code",ref);
        intent.putExtra("user_email",email);
        intent.putExtra("user_pass",pass);

        startActivity(intent);
    }

    public void see_how_itWorks(View view)
    {
        startActivity(new Intent(getApplicationContext(),SeeHowItWorks.class));
    }

    public void dash_partner_with_us(View view)
    {
        startActivity(new Intent(getApplicationContext(),PartnerWithUs.class));
    }

}
