package com.example.projectgreenindia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class PlantDetailsAdapter extends RecyclerView.Adapter<PlantDetailsAdapter.PlantDetailsViewHolder>
{
    //private List<PlantDetails> plantDetailsList;
    private List<PlantationsDetail> plantDetailsList;
    private Context ctx;
    public static final String TAG = "PantDetailsAdapter";

    PlantDetailsAdapter(List<PlantationsDetail> pdList, Context ctx)
    {
        this.plantDetailsList = pdList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public PlantDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.plantdetails_list_layout,null);

        return new PlantDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantDetailsViewHolder holder, int position)
    {
        final PlantationsDetail plantDetails = plantDetailsList.get(position);

        holder.tv_noOfPlants.setText(plantDetails.getNoofPlant());
        holder.tv_plantName.setText(plantDetails.getPlantName());
        holder.tv_plantLocation.setText(plantDetails.getLocation());
        holder.tv_plantedDate.setText(plantDetails.getDate());
        holder.tv_plantContributedStatus.setText(plantDetails.getAmount());

        String path = plantDetails.getImagepath();
        Log.i(TAG,"image path: "+path);

        Glide.with(ctx)
                .load("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject"+path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontAnimate()
                .into(holder.plantDetails_imgView);


        //Bitmap bitm = getBitmapfromUrl("http://ec2-18-191-26-57.us-east-2.compute.amazonaws.com:8080/finalYearProject/"+path);
        //holder.plantDetails_imgView.setImageBitmap(bitm);

        //holder.plantDetails_imgView.setImageResource(R.drawable.ic_neem);
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ctx,PlantedPlantDetails.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PlantDetails_obj",plantDetails);
                ctx.startActivity(intent);
            }
        });
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
    @Override
    public int getItemCount()
    {
        return plantDetailsList.size();
    }

    class PlantDetailsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView plantDetails_imgView;
        TextView tv_plantName,tv_plantLocation,tv_plantedDate,tv_plantContributedStatus,tv_noOfPlants;

        PlantDetailsViewHolder(@NonNull View itemView)
        {
            super(itemView);

            plantDetails_imgView = itemView.findViewById(R.id.plantDetails_imgView);

            tv_noOfPlants = itemView.findViewById(R.id.tv_noOfPlants);
            tv_plantName = itemView.findViewById(R.id.tv_plantName);
            tv_plantLocation = itemView.findViewById(R.id.tv_plantLocation);
            tv_plantedDate = itemView.findViewById(R.id.tv_plantedDate);
            tv_plantContributedStatus = itemView.findViewById(R.id.tv_plantContributedStatus);
        }
    }
}
