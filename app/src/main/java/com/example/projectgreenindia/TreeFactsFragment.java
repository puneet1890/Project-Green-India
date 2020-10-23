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

public class TreeFactsFragment extends Fragment
{
    private View rootView;

    public TreeFactsFragment() { }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_tree_facts, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //getActivity().setTitle("Facts");
        final ArrayList<String> treeFactsList = new ArrayList<>();

        treeFactsList.add("An average size tree can provide enough wood to make 170,100 pencils.");
        treeFactsList.add("The first type of aspirin, painkiller and fever reducer came from the tree bark of a willow tree.");
        treeFactsList.add("85% of plant life is found in the ocean.");
        treeFactsList.add("The Amazon rainforest produces half the world’s oxygen supply.");
        treeFactsList.add("Cricket bats are made of a tree called Willow and baseball bats are made out of the wood of the Hickory tree.");
        treeFactsList.add("Dendrochronology is the science of calculating a tree’s age by its rings.");
        treeFactsList.add("Caffeine serves the function of a pesticide in a coffee plant.");
        treeFactsList.add("Apple is 25% air that is why it floats on water.");
        treeFactsList.add("The tallest tree ever was an Australian eucalyptus – In 1872 it was measured at 435 feet tall.");
        treeFactsList.add("Bamboo is the fastest-growing woody plant in the world; it can grow 35 inches in a single day.");
        treeFactsList.add("The California redwood are the tallest and largest living organism in the world.");
        treeFactsList.add("Oak trees are struck by lightning more than any other tree.");
        treeFactsList.add("Caffeine serves the function of a pesticide in a coffee plant.");
        treeFactsList.add("The first known tree was a leafless, fern-like plant from New York.");
        treeFactsList.add("Before trees, Earth was home to fungi that grew 26 feet tall.");
        treeFactsList.add("Trees didn't exist for the first 90 percent of Earth's history.");
        treeFactsList.add("At over 2000 kilometres long, The Great Barrier Reef is the largest living structure on Earth.");
        treeFactsList.add("The evaporation from a large oak or beech tree is from ten to twenty-five gallons in twenty-four hours.");
        treeFactsList.add("Ginkgo is one of the oldest living tree species, it dates back to about 250 million years ago.");
        treeFactsList.add("Some trees emit chemicals that attract enemies of their enemies.");

        TreeFactsAdapter adapter = new TreeFactsAdapter(treeFactsList,getActivity());

        final RecyclerView treeFacts_RecyclerView = rootView.findViewById(R.id.treeFacts_RecyclerView);
        treeFacts_RecyclerView.setLayoutManager(new LinearLayoutManager(TreeFactsFragment.this.getActivity()));
        treeFacts_RecyclerView.setHasFixedSize(true);
        treeFacts_RecyclerView.setAdapter(adapter);
    }

}
