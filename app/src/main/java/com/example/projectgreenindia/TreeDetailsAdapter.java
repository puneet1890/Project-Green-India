package com.example.projectgreenindia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class TreeDetailsAdapter extends RecyclerView.Adapter<TreeDetailsAdapter.TreeDetailsViewHolder>
{
    private List<TreeDetails> treeDetailsList;
    private Context ctx;

    TreeDetailsAdapter(List<TreeDetails> tdList, Context ctx)
    {
        this.treeDetailsList = tdList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TreeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.treedetails_list_layout,null);

        return new TreeDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeDetailsViewHolder holder, int position)
    {
        final TreeDetails treeDetails = treeDetailsList.get(position);

        holder.tv_treeName.setText(treeDetails.getName());
        holder.tv_treeImportance.setText(treeDetails.getImportance());
        holder.tv_treeDescription.setText(treeDetails.getDescription());
        //holder.treeDetails_imgView.setImageDrawable(ctx.getResources().getDrawable(treeDetails.getImage()));
        holder.treeDetails_imgView.setImageResource(R.drawable.ic_neem);

/*
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ctx,PlantedPlantDetails.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PlantDetails_obj",treeDetails);
                ctx.startActivity(intent);
            }
        });
 */
    }

    @Override
    public int getItemCount()
    {
        return treeDetailsList.size();
    }

    class TreeDetailsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView treeDetails_imgView;
        TextView tv_treeName,tv_treeImportance,tv_treeDescription;

        TreeDetailsViewHolder(@NonNull View itemView)
        {
            super(itemView);

            treeDetails_imgView = itemView.findViewById(R.id.treeDetails_imgView);
            tv_treeName = itemView.findViewById(R.id.tv_treeName);
            tv_treeImportance = itemView.findViewById(R.id.tv_treeImportance);
            tv_treeDescription = itemView.findViewById(R.id.tv_treeDescription);
        }
    }
}
