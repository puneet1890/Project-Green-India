package com.example.projectgreenindia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TreeFactsAdapter extends RecyclerView.Adapter<TreeFactsAdapter.TreeFactsViewHolder>
{
    private List<String> treeFactsList;
    private Context ctx;

    TreeFactsAdapter(List<String> tdList, Context ctx)
    {
        this.treeFactsList = tdList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TreeFactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        View view = inflater.inflate(R.layout.treefacts_list_layout,null);
        return new TreeFactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeFactsViewHolder holder, int position)
    {
        final String treeFacts = treeFactsList.get(position);

        holder.treeFacts_imgView.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_tree_facts));
        holder.tv_treeFactsDescription.setText(treeFacts);
    }

    @Override
    public int getItemCount()
    {
        return treeFactsList.size();
    }


    class TreeFactsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView treeFacts_imgView;
        TextView tv_treeFactsDescription;

        TreeFactsViewHolder(@NonNull View itemView)
        {
            super(itemView);

            treeFacts_imgView = itemView.findViewById(R.id.treeFacts_imgView);
            tv_treeFactsDescription = itemView.findViewById(R.id.tv_treeFactsDescription);
        }
    }
}
