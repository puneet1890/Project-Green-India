package com.example.projectgreenindia;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TreeDetailsFragment extends Fragment
{
    private View rootView;
    public TreeDetailsFragment() { }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_tree_details, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //getActivity().setTitle("Details");

        final ArrayList<TreeDetails> treeDetailsList = new ArrayList<>();

        treeDetailsList.add(new TreeDetails("Neem:", "Neem tolerates a wide variety of environmental conditions", "used to treat the pox virus,used as a natural pesticide,used in the preparation of soaps & ointments", R.drawable.ic_neem));
        treeDetailsList.add(new TreeDetails("Mango:", "Mango trees were considered a symbol of prosperity in Ancient India", "help in fighting cancer,Decreases blood pressure,helps in controlling diabetes", R.drawable.ic_neem));
        treeDetailsList.add(new TreeDetails("Peepal:", "Peepal Tree, native to India and sacred to those of Buddhist faith", "helps with nosebleeds,used to treat poor appetite & stomach pain,for a brighter complexion", R.drawable.ic_neem));
        treeDetailsList.add(new TreeDetails("Teak:", "Teak is known for its high quality wood", "known high tensile strength,it is resistant to termite attacks,used to make furnitures", R.drawable.ic_neem));

        TreeDetailsAdapter adapter = new TreeDetailsAdapter(treeDetailsList,getActivity());

        final RecyclerView treeDetails_RecyclerView = rootView.findViewById(R.id.treeDetails_RecyclerView);
        treeDetails_RecyclerView.setLayoutManager(new LinearLayoutManager(TreeDetailsFragment.this.getActivity()));
        treeDetails_RecyclerView.setHasFixedSize(true);
        treeDetails_RecyclerView.setAdapter(adapter);
    }
}
