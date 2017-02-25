package com.java.demers_j.epicture.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.java.demers_j.epicture.R;
import com.java.demers_j.epicture.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

class ProfileFlickrFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_flickr, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_image_create);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager gaggeredGridLayoutManager;
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        List<String> tmp = new ArrayList<>();
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");
        tmp.add("10");
        tmp.add("11");
        tmp.add("12");
        tmp.add("13");
        tmp.add("14");
        tmp.add("15");
        tmp.add("16");
        tmp.add("17");
        tmp.add("18");
        tmp.add("19");
        tmp.add("20");
        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getContext(), tmp);
        recyclerView.setAdapter(rcAdapter);
        return rootView;
    }

}