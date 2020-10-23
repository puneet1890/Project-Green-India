package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class WalletUserProfile extends AppCompatActivity
{
    private static final String TAG = "WalletUserProfile";
    CircleImageView circleImageView;
    EditText et_userProfile_phoneNum,et_userProfile_Email,et_userProfile_AboutMe;
    Button btn_userProfile_phoneNum,btn_userProfile_Email,btn_userProfile_AboutMe,btn_Update_UserDetails;
    String phoneNum,email,aboutMe;
    ProfileDBHelper dbHelper;

    UserProfile userProfile;
    String name,mobile,ref,emaill;

    private static final String[] INITIAL_PERMS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int INITIAL_REQUEST = 1337;

    private static final int SELECT_FILE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_user_profile);

        circleImageView = findViewById(R.id.user_profile_image); // ImageView

        setTitle("Profile");

        et_userProfile_phoneNum = findViewById(R.id.et_userProfile_phoneNum);
        et_userProfile_Email = findViewById(R.id.et_userProfile_Email);
        et_userProfile_AboutMe = findViewById(R.id.tv_UPHF_Plantations_TextView);

        btn_userProfile_phoneNum = findViewById(R.id.btn_userProfile_phoneNum);
        btn_userProfile_Email = findViewById(R.id.btn_userProfile_Email);
        btn_userProfile_AboutMe = findViewById(R.id.tv_UPHF_Referals);
        btn_Update_UserDetails = findViewById(R.id.btn_Update_UserDetails);

        dbHelper = new ProfileDBHelper(this);

        Intent intent = getIntent();
        name = intent.getStringExtra("user_name");
        mobile = intent.getStringExtra("user_mobile");
        ref = intent.getStringExtra("user_ref");
        emaill = intent.getStringExtra("user_email");

        et_userProfile_Email.setText(emaill);
        et_userProfile_phoneNum.setText(mobile);
        et_userProfile_AboutMe.setText("I Love Commuplant");

        if (!canAccessWriteStorage() || !canAccessReadStorage())
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                Log.d(TAG, "UploadActivity --> onCreate(),requesting permissions");
                requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
            }
        }

        //circleImageView.setImageResource(R.drawable.ic_wallet_user_pic);

        String imageUri = dbHelper.getProfileImage(emaill);

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

    private boolean canAccessReadStorage()
    {
        return (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    private boolean canAccessWriteStorage()
    {
        return (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }


    private boolean hasPermission(String perm)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            return ActivityCompat.checkSelfPermission(this,perm) == PackageManager.PERMISSION_GRANTED;
        }
        else {
            return true;
        }
    }

    public void edit_user_phoneNum(View view)
    {
        et_userProfile_phoneNum.setText("");
    }

    public void edit_user_Email(View view)
    {
        et_userProfile_Email.setText("");
    }

    public void edit_user_AboutMe(View view)
    {
        et_userProfile_AboutMe.setText("");
    }

    public void update_UserDetails(View view)
    {
        phoneNum = et_userProfile_phoneNum.getText().toString();
        email = et_userProfile_Email.getText().toString();
        aboutMe = et_userProfile_AboutMe.getText().toString();

        //Update User Details here

        Toast.makeText(getApplicationContext(),"User Details updated",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(),User_Dashboard.class));
    }

    public void change_profile_image(View view)
    {
        fn_permission();

        try
        {
            //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(Intent.createChooser(intent,"Select a File to Upload"),SELECT_FILE);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Log.d(TAG,"Exception occurred: " +ex.getMessage());
        }
    }

    private void fn_permission()  // Checking for permissions at Runtime(verifies if it already has & checks it again)
    {
        //Checking for the App, if it has already got the Permission
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
        {
            //If permission not granted, Explain the reason why the App requires Permission & request for Permission again
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)))
            {
                Toast.makeText(getApplicationContext(), "Please provide the permission to proceed further", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        INITIAL_REQUEST);
            }
            else // No Explaination required, directly request for Permission from User
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        INITIAL_REQUEST);
            }
        }
        else {

            //Permissions verified at Runtime again & has required Permission, please proceed with the Logic
            Toast.makeText(getApplicationContext(), "Permission has been granted already", Toast.LENGTH_LONG).show();
        }
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            //upload(uri);

            String uriStr = null;
            if (uri != null)
            {
                uriStr = uri.toString();
                userProfile = new UserProfile(emaill,uriStr);

                circleImageView.setImageURI(uri);

                dbHelper.createUserProfile(userProfile);
                Toast.makeText(getApplicationContext(),"Profile Image updated",Toast.LENGTH_LONG).show();
            }
        }
    }
}
