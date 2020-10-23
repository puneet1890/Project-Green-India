package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlantedPlantDetails extends AppCompatActivity
{
    private static final String TAG = "PlantedPlantDetails";

    //PlantDetails plantDetails;
    PlantationsDetail plantDetails;
    ImageView img_PPD_imgView;
    TextView tv_PPD_Description,tv_PPD_DescriptionMsg;
    Button btn_PPD_Locate;
    String description,imagepath;

    //static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planted_plant_details);

        setTitle("Plant Details");

        // Log.i(TAG,"Email in PlantedPlantDetails: "+email);

        Intent intent = getIntent();
        plantDetails = (PlantationsDetail) intent.getSerializableExtra("PlantDetails_obj");

        img_PPD_imgView = findViewById(R.id.img_PPD_imgView);
        tv_PPD_DescriptionMsg = findViewById(R.id.tv_PPD_DescriptionMsg);
        tv_PPD_Description = findViewById(R.id.tv_PPD_Description);
        btn_PPD_Locate = findViewById(R.id.btn_PPD_Locate);

        imagepath = plantDetails.getImagepath();
        Log.i(TAG,"Image path: "+imagepath);

        description = plantDetails.getPlantName()+"\n"+
                plantDetails.getLocation()+"\n"+
                plantDetails.getDate()+"\n"+
                plantDetails.getAmount();

        //Bitmap bitmap = getBitmapfromUrl("");
        //img_PPD_imgView.setImageBitmap(bitmap);

        Glide.with(PlantedPlantDetails.this)
                .load("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject"+imagepath)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontAnimate()
                .into(img_PPD_imgView);

        //img_PPD_imgView.setImageResource(R.drawable.ic_neem);
        tv_PPD_Description.setText(description);
    }

/*
    public Bitmap getBitmapfromUrl(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

*/

/*
     public void setEmail(final String email)
    {
        PlantedPlantDetails.email = email;
    }
*/

    public void locate_plant(View view)
    {
        Toast.makeText(getApplicationContext(),"Your plant will be tracked", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),User_Dashboard.class));
    }

}
